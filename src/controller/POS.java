package controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;

import documentParsing.Partofspeech;

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
		frmPartsOfSpeech.getContentPane().setComponentOrientation(
				ComponentOrientation.LEFT_TO_RIGHT);
		frmPartsOfSpeech.getContentPane().setSize(new Dimension(620, 380));
		frmPartsOfSpeech.getContentPane().setLayout(null);

		final JTextPane textPane = new JTextPane();
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
				String text = textPane.getText();
				if (!text.equals("")) {
					Partofspeech partos = new Partofspeech();

					String outputText = "Text is Processed, following are the obtained Results: \n\n";
					String taggedText = partos.textTagger(text);
					String[] array1 = taggedText.split(" ");
					for (String s : array1) {
						String[] array2 = s.split("_");
						outputText += array2[0] + "\t" + array2[1] + "\n";
					}
					PartsOfSpeech partsos = new PartsOfSpeech(outputText);
					partsos.frmPartsOfSpeech.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(frmPartsOfSpeech,
							"Enter some Text to TAG",
							"Enter Some Text", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnTagTheText.setBounds(423, 312, 120, 23);
		frmPartsOfSpeech.getContentPane().add(btnTagTheText);
		frmPartsOfSpeech.setFont(new Font("Arial", Font.PLAIN, 12));
		frmPartsOfSpeech.setLocation(new Point(200, 150));
		frmPartsOfSpeech.setSize(new Dimension(620, 380));
		frmPartsOfSpeech
				.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		frmPartsOfSpeech.setTitle("Parts of Speech");
		frmPartsOfSpeech.setBounds(100, 100, 620, 380);
		frmPartsOfSpeech.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
