package controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;

import org.eclipse.wb.swing.FocusTraversalOnArray;

public class LicenseAgreement extends Controller {

	public JFrame frmLicenseAgreement;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LicenseAgreement window = new LicenseAgreement();
					window.frmLicenseAgreement.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LicenseAgreement() {
		initialize();
	}

	private void initialize() {
		frmLicenseAgreement = new JFrame();
//		frmLicenseAgreement.addWindowListener(new WindowAdapter() {
//			@Override
//			public void windowOpened(WindowEvent arg0) {
//				LicenseAgreement lag = new LicenseAgreement();
//				lag.start();
//				String s = lag.command;
//				System.out.println(s+"License agreement");
//				if (lag.command.equalsIgnoreCase("I Accept Agreement")) {
//					LicenseAgreement.this.frmLicenseAgreement.dispose();
//					Introduction tutorial = new Introduction();
//					tutorial.frmTutorial.setVisible(true);
//				}
//			}
//		});
		frmLicenseAgreement.setResizable(false);
		frmLicenseAgreement.getContentPane().setFont(
				new Font("Arial", Font.PLAIN, 8));
		frmLicenseAgreement.getContentPane().setComponentOrientation(
				ComponentOrientation.LEFT_TO_RIGHT);
		frmLicenseAgreement.getContentPane().setForeground(Color.LIGHT_GRAY);
		frmLicenseAgreement.setPreferredSize(new Dimension(720, 640));
		frmLicenseAgreement.setFont(new Font("Arial", Font.PLAIN, 12));
		frmLicenseAgreement.setForeground(Color.LIGHT_GRAY);
		frmLicenseAgreement.setCursor(Cursor
				.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		frmLicenseAgreement
				.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		frmLicenseAgreement.setBackground(Color.WHITE);
		frmLicenseAgreement.setSize(new Dimension(620, 420));
		frmLicenseAgreement.getContentPane().setSize(new Dimension(620, 420));
		frmLicenseAgreement.getContentPane().setMaximumSize(
				new Dimension(720, 640));
		frmLicenseAgreement.getContentPane().setLayout(null);

		JButton btnIAcceptAgreement = new JButton("I Accept Agreement");
		btnIAcceptAgreement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LicenseAgreement.this.frmLicenseAgreement.dispose();
				Introduction tutorial = new Introduction();
				tutorial.frmTutorial.setVisible(true);

			}
		});
		btnIAcceptAgreement.setFont(new Font("Arial", Font.PLAIN, 11));
		btnIAcceptAgreement.setBounds(135, 228, 192, 23);
		frmLicenseAgreement.getContentPane().add(btnIAcceptAgreement);

		JTextPane txtpnUseOfWiki = new JTextPane();
		txtpnUseOfWiki.setEditable(false);
		txtpnUseOfWiki.setFont(new Font("Arial", Font.PLAIN, 12));
		txtpnUseOfWiki
				.setText("Use of WIKI is subject to following license terms,cited as below  \r\n\r\nIt is an open source application and can be used by any one, provided that the following conditions  are met:\r\n1. Commercial use of this product is strictly prohibited as some building blocks used in the product insist so.\r\n2. Redistribution and use in source and binary forms, with or without\r\nmodification, are permitted.");
		txtpnUseOfWiki.setBounds(10, 75, 414, 142);
		frmLicenseAgreement.getContentPane().add(txtpnUseOfWiki);

		JLabel lblGraphicsG = new JLabel(
				"Please go through the License Agreement");
		lblGraphicsG.setFont(new Font("Arial", Font.PLAIN, 11));
		lblGraphicsG.setBounds(10, 41, 346, 36);
		frmLicenseAgreement.getContentPane().add(lblGraphicsG);

		JLabel lblNewLabel = new JLabel("Wiki Speech Synthesizer");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 18));
		lblNewLabel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		lblNewLabel.setBackground(new Color(47, 79, 79));
		lblNewLabel.setBounds(94, 11, 276, 29);
		frmLicenseAgreement.getContentPane().add(lblNewLabel);
		frmLicenseAgreement.setName("LicensAgreement");
		frmLicenseAgreement.setMaximumSize(new Dimension(1024, 1024));
		frmLicenseAgreement.setTitle("License Agreement");
		frmLicenseAgreement.setBounds(100, 100, 450, 300);
		frmLicenseAgreement.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLicenseAgreement.setFocusTraversalPolicy(new FocusTraversalOnArray(
				new Component[] { frmLicenseAgreement.getContentPane() }));
	}
}
