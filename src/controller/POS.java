package controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class POS {

	public JFrame frmPartsOfSpeech;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					POS window = new POS();
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
	public POS() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPartsOfSpeech = new JFrame();
		frmPartsOfSpeech.getContentPane().setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		frmPartsOfSpeech.getContentPane().setSize(new Dimension(620, 380));
		frmPartsOfSpeech.getContentPane().setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setToolTipText("Enter some text to tag it");
		textPane.setSelectionColor(Color.LIGHT_GRAY);
		textPane.setBorder(new LineBorder(Color.GRAY, 1, true));
		textPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		textPane.setBounds(10, 11, 584, 290);
		frmPartsOfSpeech.getContentPane().add(textPane);
		
		JButton btnBackToWiki = new JButton("Back to Wiki");
		btnBackToWiki.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Wiki wiki = new Wiki();
				wiki.frmWiki.setVisible(true);
				POS.this.frmPartsOfSpeech.dispose();
			}
		});
		btnBackToWiki.setBounds(42, 312, 120, 23);
		frmPartsOfSpeech.getContentPane().add(btnBackToWiki);
		
		JButton btnTagTheText = new JButton("Tag the TEXT");
		btnTagTheText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PartsOfSpeech partsos = new PartsOfSpeech();
				partsos.frmPartsOfSpeech.setVisible(true);
			}
		});
		btnTagTheText.setBounds(423, 312, 120, 23);
		frmPartsOfSpeech.getContentPane().add(btnTagTheText);
		frmPartsOfSpeech.setFont(new Font("Arial", Font.PLAIN, 12));
		frmPartsOfSpeech.setLocation(new Point(200, 150));
		frmPartsOfSpeech.setSize(new Dimension(620, 380));
		frmPartsOfSpeech.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		frmPartsOfSpeech.setTitle("Parts of Speech");
		frmPartsOfSpeech.setBounds(100, 100, 620, 380);
		frmPartsOfSpeech.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
