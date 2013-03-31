package controller;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JFrame;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import speechSynthesis.Synthesizer;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Introduction {

	public JFrame frmTutorial;

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
	
	private void giveIntro(){
		Synthesizer synthesize = new Synthesizer();
		synthesize.speakSentence("Welcome to Wiki Speech Synthesizer Tool");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTutorial = new JFrame();
		frmTutorial.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				giveIntro();
			}
		});
		frmTutorial.getContentPane().setSize(new Dimension(620, 420));
		frmTutorial.getContentPane().setPreferredSize(new Dimension(620, 420));
		frmTutorial.getContentPane().setName("Introduction to wiki");
		frmTutorial.getContentPane().setFont(new Font("Arial", Font.PLAIN, 10));
		frmTutorial.getContentPane().setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		frmTutorial.getContentPane().setLayout(null);
		
		Canvas canvas = new Canvas();
		canvas.setBackground(Color.WHITE);
		canvas.setName("Wiki Image");
		canvas.setSize(new Dimension(620, 420));
		canvas.setBounds(10, 10, 414, 212);
		frmTutorial.getContentPane().add(canvas);
		
		JButton btnTakeATutorial = new JButton("Take a Tutorial");
		btnTakeATutorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Introduction.this.frmTutorial.dispose();
				Tutorial tutorialWindow = new Tutorial();
				tutorialWindow.frame.setVisible(true);
			}
		});
		btnTakeATutorial.setFont(new Font("Arial", Font.PLAIN, 11));
		btnTakeATutorial.setBounds(20, 228, 132, 23);
		frmTutorial.getContentPane().add(btnTakeATutorial);
		
		JButton btnGoToSynthesizer = new JButton("Go to Synthesizer");
		btnGoToSynthesizer.setFont(new Font("Arial", Font.PLAIN, 11));
		btnGoToSynthesizer.setBounds(268, 226, 139, 23);
		frmTutorial.getContentPane().add(btnGoToSynthesizer);
		frmTutorial.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{canvas, btnTakeATutorial, btnGoToSynthesizer}));
		frmTutorial.setTitle("Wiki Introduction");
		frmTutorial.setSize(new Dimension(620, 420));
		frmTutorial.setPreferredSize(new Dimension(620, 420));
		frmTutorial.setName("Introduction");
		frmTutorial.setFont(new Font("Arial", Font.PLAIN, 12));
		frmTutorial.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		frmTutorial.setBounds(new Rectangle(0, 0, 620, 420));
		frmTutorial.setBackground(Color.WHITE);
		frmTutorial.setBounds(100, 100, 450, 300);
		frmTutorial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTutorial.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{frmTutorial.getContentPane()}));
	}

}
