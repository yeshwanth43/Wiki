package controller;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import speechSynthesis.Player;


public class Introduction {

	public JFrame frmTutorial;
	private Player player = new Player();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Introduction window = new Introduction();
					window.frmTutorial.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Introduction() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTutorial = new JFrame();
		frmTutorial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTutorial.setLocation(new Point(150, 150));
		frmTutorial.getContentPane().setLocation(new Point(150, 150));
		frmTutorial.getContentPane().setSize(new Dimension(760, 460));
		frmTutorial.setSize(new Dimension(650, 420));
		frmTutorial.getContentPane().setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		frmTutorial.getContentPane().setFont(new Font("Arial", Font.PLAIN, 11));
		frmTutorial.getContentPane().setLayout(null);
		JButton button = new JButton("");
		button.setBorderPainted(false);
		button.setFocusable(false);
		button.setFocusTraversalKeysEnabled(false);
		button.setFocusPainted(false);
		button.setIcon(new ImageIcon("././assets/photos/Wlogo.jpg"));
		button.setForeground(Color.WHITE);
		button.setBackground(Color.WHITE);
		button.setBounds(10, 11, 624, 314);
		frmTutorial.getContentPane().add(button);
		
		final JButton btnStopIntroduction = new JButton("Stop Introduction");
		btnStopIntroduction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				player.stop();
				btnStopIntroduction.setVisible(false);
			}
		});
		btnStopIntroduction.setVisible(false);
		btnStopIntroduction.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		btnStopIntroduction.setBounds(158, 351, 150, 23);
		frmTutorial.getContentPane().add(btnStopIntroduction);
		
		JButton btnIntroduction = new JButton("Introduction");
		btnIntroduction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				player.giveIntroduction();
				btnStopIntroduction.setVisible(true);
			}
		});
		btnIntroduction.setBounds(10, 351, 119, 23);
		frmTutorial.getContentPane().add(btnIntroduction);
		
		JButton btnWiki = new JButton("Wiki");
		btnWiki.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Wiki wiki = new Wiki();
				Introduction.this.frmTutorial.dispose();
				wiki.frmWiki.setVisible(true);
			}
		});
		btnWiki.setBounds(537, 351, 89, 23);
		frmTutorial.getContentPane().add(btnWiki);
		frmTutorial.setTitle("Introduction");
		frmTutorial.setResizable(false);
		frmTutorial.setFont(new Font("Arial", Font.PLAIN, 12));
		frmTutorial.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	}
}
