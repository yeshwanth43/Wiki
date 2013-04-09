package controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Font;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Color;

public class Wiki {

	public JFrame frmWiki;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Wiki window = new Wiki();
					window.frmWiki.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Wiki() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmWiki = new JFrame();
		frmWiki.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWiki.getContentPane().setBackground(Color.WHITE);
		frmWiki.getContentPane().setForeground(Color.WHITE);
		frmWiki.setForeground(Color.WHITE);
		frmWiki.getContentPane().setSize(new Dimension(720, 400));
		frmWiki.setLocation(new Point(200, 150));
		frmWiki.setSize(new Dimension(620, 420));
		frmWiki.getContentPane().setComponentOrientation(
				ComponentOrientation.LEFT_TO_RIGHT);
		frmWiki.getContentPane().setLayout(null);

		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Readability readability = new Readability();
				Wiki.this.frmWiki.dispose();
				readability.frmReadabilityStatistics.setVisible(true);
			}
		});
		button.setIcon(new ImageIcon("././assets/photos/readabilitylogo.jpg"));
		button.setBounds(413, 11, 150, 150);
		frmWiki.getContentPane().add(button);

		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AboutUs aboutus = new AboutUs();
				Wiki.this.frmWiki.dispose();
				aboutus.frmAboutUs.setVisible(true);
			}
		});
		button_1.setIcon(new ImageIcon("././assets/photos/aboutus.jpg"));
		button_1.setBounds(413, 204, 150, 150);
		frmWiki.getContentPane().add(button_1);

		JButton button_2 = new JButton("");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WikiSynthesizer wikiSynth = new WikiSynthesizer();
				Wiki.this.frmWiki.dispose();
				wikiSynth.frmWikiSynthesizer.setVisible(true);
			}
		});
		button_2.setIcon(new ImageIcon("././assets/photos/synth.jpg"));
		button_2.setBounds(59, 11, 150, 150);
		frmWiki.getContentPane().add(button_2);

		JButton button_3 = new JButton("");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				POS pos = new POS();
				Wiki.this.frmWiki.dispose();
				pos.frmPartsOfSpeech.setVisible(true);
			}
		});
		button_3.setIcon(new ImageIcon(
				"././assets/photos/EnhanceMyVocabulary-logo.jpg"));
		button_3.setBounds(59, 204, 150, 150);
		frmWiki.getContentPane().add(button_3);

		JLabel lblSynthesizer = new JLabel("Synthesizer");
		lblSynthesizer.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSynthesizer.setBounds(211, 21, 86, 29);
		frmWiki.getContentPane().add(lblSynthesizer);

		JLabel lblReadabilityIndex = new JLabel("Readability Index");
		lblReadabilityIndex.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblReadabilityIndex.setBounds(291, 119, 139, 29);
		frmWiki.getContentPane().add(lblReadabilityIndex);

		JLabel lblPos = new JLabel("POS");
		lblPos.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPos.setBounds(211, 215, 86, 29);
		frmWiki.getContentPane().add(lblPos);

		JLabel lblAboutUs = new JLabel("About Us");
		lblAboutUs.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAboutUs.setBounds(339, 311, 79, 29);
		frmWiki.getContentPane().add(lblAboutUs);
		frmWiki.setFont(new Font("Arial", Font.PLAIN, 12));
		frmWiki.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		frmWiki.setResizable(false);
		frmWiki.setTitle("Wiki");
	}

}
