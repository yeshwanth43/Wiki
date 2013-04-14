package controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.Point;

import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import java.io.IOException;

import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import documentParsing.DocParseV1;

public class Readability {

	public JFrame frmReadabilityStatistics;
	private JTextField textField;
	private boolean flag = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Readability window = new Readability();
					window.frmReadabilityStatistics.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Readability() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmReadabilityStatistics = new JFrame();
		frmReadabilityStatistics.getContentPane().setSize(
				new Dimension(720, 420));
		frmReadabilityStatistics.getContentPane().setLayout(null);

		final JTextPane textPane = new JTextPane();
		textPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
				null, null));
		textPane.setMargin(new Insets(5, 5, 5, 5));
		textPane.setBounds(10, 11, 530, 271);
		frmReadabilityStatistics.getContentPane().add(textPane);
		textField = new JTextField();
		textField.setText("Enter File Path");
		textField.setColumns(10);
		textField.setBounds(66, 310, 474, 23);
		frmReadabilityStatistics.getContentPane().add(textField);
		final JLabel label = new JLabel("File Path:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(10, 308, 66, 22);
		frmReadabilityStatistics.getContentPane().add(label);

		JButton btnReadability = new JButton("Readability");
		btnReadability.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String text = textPane.getText();
				if (!text.equals("")) {
					flag = true;
					DocParseV1 dv1 = new DocParseV1();
					dv1.processText(text);
					Statistics stats = new Statistics();
					stats.frmStatistics.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(frmReadabilityStatistics,
							"Enter some TEXT and then Click Readability ",
							"Enter Some Text", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnReadability.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnReadability.setBounds(550, 21, 154, 23);
		frmReadabilityStatistics.getContentPane().add(btnReadability);

		JButton btnBackToWiki = new JButton("Back to Wiki");
		btnBackToWiki.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Wiki wiki = new Wiki();
				Readability.this.frmReadabilityStatistics.dispose();
				wiki.frmWiki.setVisible(true);
			}
		});
		btnBackToWiki.setBounds(10, 358, 140, 23);
		frmReadabilityStatistics.getContentPane().add(btnBackToWiki);

		JButton btnNewButton = new JButton("Enhance Readability");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (flag) {
					EnhanceStatistics ehs = new EnhanceStatistics();
					ehs.frmEnhanceReadability.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(frmReadabilityStatistics,
							"you cannot Enhace, without giving any content ",
							"Give Text/Document", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(550, 80, 154, 23);
		frmReadabilityStatistics.getContentPane().add(btnNewButton);

		JButton btnProcessDocument = new JButton("Process Document");
		btnProcessDocument.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String resourceLocation = textField.getText();
				DocParseV1 dv1 = new DocParseV1();
				try {
					if (!dv1.parse(resourceLocation)) {
						JOptionPane.showMessageDialog(frmReadabilityStatistics,
								"Make sure you entered a valid file path",
								"File not Found", JOptionPane.WARNING_MESSAGE);
					} else {
						Statistics stats = new Statistics();
						flag = true;
						stats.frmStatistics.setVisible(true);
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnProcessDocument.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnProcessDocument.setBounds(550, 310, 154, 23);
		frmReadabilityStatistics.getContentPane().add(btnProcessDocument);
		frmReadabilityStatistics.setLocation(new Point(200, 150));
		frmReadabilityStatistics.setFont(new Font("Arial", Font.PLAIN, 12));
		frmReadabilityStatistics
				.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		frmReadabilityStatistics.setResizable(false);
		frmReadabilityStatistics.setTitle("Readability Statistics");
		frmReadabilityStatistics.setSize(new Dimension(720, 420));
		frmReadabilityStatistics.setBounds(100, 100, 720, 420);
		frmReadabilityStatistics.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}