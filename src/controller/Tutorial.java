package controller;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;

import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Tutorial {

	public JFrame frmWikiTutorial;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tutorial window = new Tutorial();
					window.frmWikiTutorial.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Tutorial() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmWikiTutorial = new JFrame();
		frmWikiTutorial.getContentPane().setSize(new Dimension(620, 420));
		frmWikiTutorial.getContentPane().setPreferredSize(new Dimension(620, 420));
		frmWikiTutorial.getContentPane().setName("Tuttorial");
		frmWikiTutorial.getContentPane().setFont(new Font("Arial", Font.PLAIN, 11));
		frmWikiTutorial.getContentPane().setLayout(null);
		
		Canvas canvas = new Canvas();
		canvas.setFont(new Font("Arial", Font.PLAIN, 10));
		canvas.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		canvas.setBackground(Color.WHITE);
		canvas.setName("tutorial");
		canvas.setBounds(10, 10, 594, 347);
		frmWikiTutorial.getContentPane().add(canvas);
		
		JButton btnNextSlide = new JButton("Next Slide");
		btnNextSlide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNextSlide.setOpaque(false);
		btnNextSlide.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		btnNextSlide.setForeground(Color.BLACK);
		btnNextSlide.setBounds(40, 363, 119, 23);
		frmWikiTutorial.getContentPane().add(btnNextSlide);
		
		JButton btnSkipThisTutorial = new JButton("Skip this tutorial");
		btnSkipThisTutorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Tutorial.this.frmWikiTutorial.dispose();
				WikiSynthesizer synthWindow = new WikiSynthesizer();
				synthWindow.frmWikiSynthesizer.setVisible(true);
			}
		});
		btnSkipThisTutorial.setBounds(436, 363, 133, 23);
		frmWikiTutorial.getContentPane().add(btnSkipThisTutorial);
		frmWikiTutorial.setTitle("Wiki Tutorial");
		frmWikiTutorial.setResizable(false);
		frmWikiTutorial.setFont(new Font("Arial", Font.PLAIN, 12));
		frmWikiTutorial.setBounds(100, 100, 620, 420);
		frmWikiTutorial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWikiTutorial.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{frmWikiTutorial.getContentPane()}));
	}
}
