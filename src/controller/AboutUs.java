package controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AboutUs {

	public JFrame frmAboutUs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AboutUs window = new AboutUs();
					window.frmAboutUs.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AboutUs() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAboutUs = new JFrame();
		frmAboutUs.getContentPane().setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		frmAboutUs.getContentPane().setSize(new Dimension(620, 320));
		frmAboutUs.getContentPane().setBackground(Color.WHITE);
		frmAboutUs.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setFocusPainted(false);
		btnNewButton.setFocusTraversalKeysEnabled(false);
		btnNewButton.setFocusable(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBounds(70, 11, 120, 120);
		frmAboutUs.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setFocusable(false);
		btnNewButton_1.setFocusTraversalKeysEnabled(false);
		btnNewButton_1.setFocusPainted(false);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setBounds(422, 11, 120, 120);
		frmAboutUs.getContentPane().add(btnNewButton_1);
		
		JButton btnBackToWiki = new JButton("Back to Wiki");
		btnBackToWiki.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Wiki wiki = new Wiki();
				AboutUs.this.frmAboutUs.dispose();
				wiki.frmWiki.setVisible(true);
			}
		});
		btnBackToWiki.setBounds(10, 238, 137, 23);
		frmAboutUs.getContentPane().add(btnBackToWiki);
		frmAboutUs.setBackground(Color.WHITE);
		frmAboutUs.setForeground(Color.WHITE);
		frmAboutUs.setFont(new Font("Arial", Font.PLAIN, 14));
		frmAboutUs.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		frmAboutUs.setLocation(new Point(200, 150));
		frmAboutUs.setSize(new Dimension(620, 320));
		frmAboutUs.setResizable(false);
		frmAboutUs.setTitle("About Us");
		frmAboutUs.setBounds(100, 100, 623, 300);
		frmAboutUs.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
