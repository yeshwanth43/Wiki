package controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.ComponentOrientation;
import javax.swing.JTextPane;
import javax.swing.JButton;

import documentParsing.ReadJson;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Statistics {

	public JFrame frmStatistics;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Statistics window = new Statistics();
					window.frmStatistics.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Statistics() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmStatistics = new JFrame();
		final JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBounds(10, 11, 494, 248);
		frmStatistics.getContentPane().add(textPane);
		frmStatistics.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {


				String setTextPane = "";
				ReadJson rj = new ReadJson();
				rj.readJsonFile();
				String [] indexes = rj.indexes;
				setTextPane =  "Gunning Fog Index:               \t"+indexes[0]+"\n";
				setTextPane += "Automated Readability Index:     \t"+indexes[1]+"\n";
				setTextPane += "Flesch Kincaid Readability Index:\t"+indexes[2]+"\n";
//				setTextPane += "SMOG Index:                      \t\t"+indexes[3]+"\n";
				setTextPane +="\n****************************************************************************************************************\n";
				setTextPane +="\n****************************************************************************************************************\n";
				setTextPane +="";//TODO suggestion message
				textPane.setText(setTextPane);
				textPane.setEditable(false);
			}
		});
		frmStatistics.setLocation(new Point(400, 300));
		frmStatistics.setSize(new Dimension(520, 334));
		frmStatistics.getContentPane().setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		frmStatistics.getContentPane().setLocation(new Point(200, 150));
		frmStatistics.getContentPane().setSize(new Dimension(520, 320));
		frmStatistics.getContentPane().setLayout(null);
		
		JButton btnClose = new JButton("close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Statistics.this.frmStatistics.dispose();
			}
		});
		btnClose.setBounds(197, 270, 89, 23);
		frmStatistics.getContentPane().add(btnClose);
		frmStatistics.setResizable(false);
		frmStatistics.setTitle("Statistics");
		frmStatistics.setFont(new Font("Arial", Font.PLAIN, 12));
		frmStatistics.setBounds(100, 100, 520, 320);
		frmStatistics.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

}
