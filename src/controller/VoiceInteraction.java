package controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.ComponentOrientation;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Point;

import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VoiceInteraction {

	public JFrame frmMicroPhone;
	private boolean flag = true;
	private Controller cont;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VoiceInteraction window = new VoiceInteraction();
					window.frmMicroPhone.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VoiceInteraction() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		cont = new Controller();
		frmMicroPhone = new JFrame();
		final JTextPane textPane = new JTextPane();
		textPane.setBounds(10, 97, 560, 154);
		textPane.setEditable(false);
		frmMicroPhone.getContentPane().add(textPane);
		
		frmMicroPhone.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				textPane.setText(" Following are the Commands for Interacting with Wiki:\n\nCOMMAND NAME\t COMMAND ACTION\n1. Application start:\t Starts Application");
			}
		});

		frmMicroPhone.getContentPane().setLocation(new Point(400, 300));
		frmMicroPhone.setLocation(new Point(400, 300));
		frmMicroPhone.getContentPane().setBackground(Color.LIGHT_GRAY);
		frmMicroPhone
				.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		frmMicroPhone.getContentPane().setComponentOrientation(
				ComponentOrientation.LEFT_TO_RIGHT);
		frmMicroPhone.getContentPane().setSize(new Dimension(580, 360));
		frmMicroPhone.getContentPane().setLayout(null);

		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VoiceInteraction.this.frmMicroPhone.dispose();
			}
		});
		btnClose.setBounds(232, 326, 89, 23);
		frmMicroPhone.getContentPane().add(btnClose);

		JLabel lblYouSaid = new JLabel("You Said:");
		lblYouSaid.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblYouSaid.setBounds(10, 63, 89, 23);
		frmMicroPhone.getContentPane().add(lblYouSaid);
		
		final JButton button = new JButton("Stop Recognizer");
		button.setEnabled(false);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.setBounds(416, 276, 154, 23);
		button.setVisible(false);
		frmMicroPhone.getContentPane().add(button);

		JButton btnStopRecognizer = new JButton("Start Recognizer");
		btnStopRecognizer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (flag) {
					try {
						flag = false;
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
				} else {
					JOptionPane.showMessageDialog(frmMicroPhone,
							"Recognizer Already Started", "Recognizer Running",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnStopRecognizer.setBounds(10, 276, 154, 23);
		frmMicroPhone.getContentPane().add(btnStopRecognizer);


		frmMicroPhone.setUndecorated(true);
		frmMicroPhone.setResizable(false);
		frmMicroPhone.setTitle("Micro Phone");
		frmMicroPhone.setSize(new Dimension(580, 360));
		frmMicroPhone.setBounds(100, 100, 580, 360);
		frmMicroPhone.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
