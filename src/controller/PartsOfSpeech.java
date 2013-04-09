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

		frmPartsOfSpeech.setUndecorated(true);
		frmPartsOfSpeech.getContentPane().setComponentOrientation(
				ComponentOrientation.LEFT_TO_RIGHT);
		frmPartsOfSpeech.getContentPane().setSize(new Dimension(560, 360));
		frmPartsOfSpeech.getContentPane().setLayout(null);

		final JTextPane textPane = new JTextPane();
		textPane.setBounds(10, 11, 534, 289);
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
		btnClose.setBounds(231, 311, 89, 23);
		frmPartsOfSpeech.getContentPane().add(btnClose);
		frmPartsOfSpeech
				.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		frmPartsOfSpeech.setFont(new Font("Arial", Font.PLAIN, 12));
		frmPartsOfSpeech.setLocation(new Point(500, 400));
		frmPartsOfSpeech.setResizable(false);
		frmPartsOfSpeech.setSize(new Dimension(560, 360));
		frmPartsOfSpeech.setVisible(true);
		frmPartsOfSpeech.setTitle("Parts of Speech");
		frmPartsOfSpeech.setBounds(100, 100, 560, 360);
		frmPartsOfSpeech.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

}
