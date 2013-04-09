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
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

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
		frmAboutUs.setResizable(false);
		frmAboutUs.setLocation(new Point(200, 150));
		frmAboutUs.getContentPane().setBackground(Color.WHITE);
		frmAboutUs.getContentPane().setForeground(Color.WHITE);
		frmAboutUs.getContentPane().setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		frmAboutUs.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon("././assets/photos/yesh.jpg"));
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBorder(new LineBorder(Color.LIGHT_GRAY));
		btnNewButton.setFocusable(false);
		btnNewButton.setFocusTraversalKeysEnabled(false);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		btnNewButton.setBounds(10, 11, 120, 120);
		frmAboutUs.getContentPane().add(btnNewButton);
		
		JButton button = new JButton("");
		button.setIcon(new ImageIcon("././assets/photos/rahul.jpg"));
		button.setFocusable(false);
		button.setFocusTraversalKeysEnabled(false);
		button.setFocusPainted(false);
		button.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		button.setBorderPainted(false);
		button.setBorder(new LineBorder(Color.LIGHT_GRAY));
		button.setBackground(Color.WHITE);
		button.setBounds(574, 11, 120, 120);
		frmAboutUs.getContentPane().add(button);
		
		JButton btnBackToWiki = new JButton("Back to Wiki");
		btnBackToWiki.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Wiki wi = new Wiki();
				wi.frmWiki.setVisible(true);
				AboutUs.this.frmAboutUs.dispose();
			}
		});
		btnBackToWiki.setBounds(10, 348, 120, 23);
		frmAboutUs.getContentPane().add(btnBackToWiki);
		
		JLabel lblYeshwanthRoles = new JLabel("Yeshwanth Kumar Jagini\r\n");
		lblYeshwanthRoles.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblYeshwanthRoles.setBounds(140, 11, 201, 40);
		frmAboutUs.getContentPane().add(lblYeshwanthRoles);
		
		JLabel lblDeveloperAndDesigner = new JLabel("Developer and Designer");
		lblDeveloperAndDesigner.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDeveloperAndDesigner.setBounds(140, 36, 201, 40);
		frmAboutUs.getContentPane().add(lblDeveloperAndDesigner);
		
		JLabel lblKondaRahul = new JLabel("Konda Rahul");
		lblKondaRahul.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblKondaRahul.setBounds(468, 11, 201, 40);
		frmAboutUs.getContentPane().add(lblKondaRahul);
		
		JLabel label_1 = new JLabel("Developer and Designer");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_1.setBounds(394, 36, 201, 40);
		frmAboutUs.getContentPane().add(label_1);
		
		JButton button_1 = new JButton("");
		button_1.setIcon(new ImageIcon("././assets/photos/thanks.jpg"));
		button_1.setFocusable(false);
		button_1.setFocusTraversalKeysEnabled(false);
		button_1.setFocusPainted(false);
		button_1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		button_1.setBorderPainted(false);
		button_1.setBorder(new LineBorder(Color.LIGHT_GRAY));
		button_1.setBackground(Color.WHITE);
		button_1.setBounds(140, 130, 424, 211);
		frmAboutUs.getContentPane().add(button_1);
		frmAboutUs.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		frmAboutUs.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAboutUs.setFont(new Font("Arial", Font.PLAIN, 12));
		frmAboutUs.setTitle("About Us");
		frmAboutUs.setSize(new Dimension(720, 420));
	}
}
