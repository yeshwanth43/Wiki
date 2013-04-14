package controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Font;
import java.awt.ComponentOrientation;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class PartsOfSpeech {

	public JFrame frmPartsOfSpeech;
	private String text = new String();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PartsOfSpeech window = new PartsOfSpeech();
					window.frmPartsOfSpeech.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PartsOfSpeech() {
		initialize();
	}

	public PartsOfSpeech(String text) {
		this.text = text;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPartsOfSpeech = new JFrame();
		frmPartsOfSpeech.getContentPane().setSize(new Dimension(560, 360));
		frmPartsOfSpeech.getContentPane().setComponentOrientation(
				ComponentOrientation.LEFT_TO_RIGHT);
		frmPartsOfSpeech.getContentPane().setLayout(null);

		final JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBounds(10, 11, 540, 314);
		frmPartsOfSpeech.getContentPane().add(textPane);
		frmPartsOfSpeech.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				textPane.setText(text);
			}
		});

		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PartsOfSpeech.this.frmPartsOfSpeech.dispose();
			}
		});
		btnClose.setBounds(233, 336, 89, 23);
		frmPartsOfSpeech.getContentPane().add(btnClose);

		JScrollPane scrollPane = new JScrollPane(textPane);
		scrollPane.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		scrollPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		scrollPane.setFont(new Font("Tahoma", Font.BOLD, 12));
		scrollPane.setBounds(10, 11, 540, 314);
		frmPartsOfSpeech.getContentPane().add(scrollPane);
		frmPartsOfSpeech.setTitle("Tagged Text");
		frmPartsOfSpeech.setUndecorated(true);
		frmPartsOfSpeech.setSize(new Dimension(560, 360));
		frmPartsOfSpeech.setLocation(new Point(400, 300));
		frmPartsOfSpeech
				.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		frmPartsOfSpeech.setResizable(false);
		frmPartsOfSpeech.setFont(new Font("Arial", Font.PLAIN, 12));
		frmPartsOfSpeech.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
