package controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Font;
import java.awt.ComponentOrientation;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.ImageIcon;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import speechSynthesis.SynthesisV1;

import documentParsing.DocParseV1;

import java.awt.Component;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class WikiSynthesizer {

	public JFrame frmWikiSynthesizer;
	private JTextField txtEnterFilePath;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WikiSynthesizer window = new WikiSynthesizer();
					window.frmWikiSynthesizer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WikiSynthesizer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmWikiSynthesizer = new JFrame();
		frmWikiSynthesizer.getContentPane().setBackground(Color.LIGHT_GRAY);
		frmWikiSynthesizer.getContentPane().setComponentOrientation(
				ComponentOrientation.LEFT_TO_RIGHT);
		frmWikiSynthesizer.getContentPane().setFont(
				new Font("Arial", Font.PLAIN, 11));
		frmWikiSynthesizer.getContentPane().setLayout(null);

		final JTextPane txtpnEnterSomeText = new JTextPane();
		txtpnEnterSomeText.setText("Enter some text to Synthesize it");
		txtpnEnterSomeText.setBounds(10, 11, 433, 262);
		frmWikiSynthesizer.getContentPane().add(txtpnEnterSomeText);

		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input = txtpnEnterSomeText.getText();
				SynthesisV1 sv1 = new SynthesisV1();
				sv1.speakSentence(input);
			}
		});
		btnNewButton.setIcon(new ImageIcon(
				"././assets/photos/synth.jpg"));
		btnNewButton.setBounds(480, 40, 130, 130);
		frmWikiSynthesizer.getContentPane().add(btnNewButton);

		JLabel lblSynthesize = new JLabel("Synthesize");
		lblSynthesize.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSynthesize.setBounds(490, 181, 108, 22);
		frmWikiSynthesizer.getContentPane().add(lblSynthesize);

		JLabel lblNewLabel = new JLabel("File Path:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 299, 66, 22);
		frmWikiSynthesizer.getContentPane().add(lblNewLabel);

		txtEnterFilePath = new JTextField();
		txtEnterFilePath.setText("Enter File Path");
		txtEnterFilePath.setBounds(70, 301, 373, 23);
		frmWikiSynthesizer.getContentPane().add(txtEnterFilePath);
		txtEnterFilePath.setColumns(10);

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String resourceLocation = txtEnterFilePath.getText();
				DocParseV1 dv1 = new DocParseV1();
				try {
					if (!dv1.parse(resourceLocation)) {
						JOptionPane.showMessageDialog(frmWikiSynthesizer,
								"Make sure you entered a valid file path",
								"File not Found", JOptionPane.WARNING_MESSAGE);
					} else {
						DocumentSynthesizerStatus dssWindow = new DocumentSynthesizerStatus();
						WikiSynthesizer.this.frmWikiSynthesizer.dispose();
						dssWindow.frameDSS.setVisible(true);
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		});
		btnNewButton_1.setIcon(new ImageIcon("././assets/photos/document.jpg"));
		btnNewButton_1.setBounds(507, 214, 76, 79);
		frmWikiSynthesizer.getContentPane().add(btnNewButton_1);

		JLabel lblProcessDocument = new JLabel("Process Document");
		lblProcessDocument.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblProcessDocument.setBounds(480, 302, 130, 22);
		frmWikiSynthesizer.getContentPane().add(lblProcessDocument);

		JButton btnBackToWiki = new JButton("Back to Wiki");
		btnBackToWiki.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Wiki wiki = new Wiki();
				WikiSynthesizer.this.frmWikiSynthesizer.dispose();
				wiki.frmWiki.setVisible(true);
			}
		});
		btnBackToWiki.setBounds(10, 345, 108, 23);
		frmWikiSynthesizer.getContentPane().add(btnBackToWiki);
		frmWikiSynthesizer.getContentPane().setFocusTraversalPolicy(
				new FocusTraversalOnArray(new Component[] { txtpnEnterSomeText,
						btnNewButton }));
		frmWikiSynthesizer.setTitle("Wiki Synthesizer");
		frmWikiSynthesizer.setResizable(false);
		frmWikiSynthesizer.setFont(new Font("Arial", Font.PLAIN, 12));
		frmWikiSynthesizer.setBounds(100, 100, 640, 420);
		frmWikiSynthesizer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
