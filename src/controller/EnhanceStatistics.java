package controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JTextPane;

import documentParsing.ReadJson;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.Point;
import java.awt.ComponentOrientation;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EnhanceStatistics {

	public JFrame frmEnhanceReadability;
	private ArrayList<String> child = new ArrayList<String>();
	private ArrayList<String> elder = new ArrayList<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EnhanceStatistics window = new EnhanceStatistics();
					window.frmEnhanceReadability.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EnhanceStatistics() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEnhanceReadability = new JFrame();
		frmEnhanceReadability.setUndecorated(true);
		frmEnhanceReadability.setResizable(false);
		final JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		textPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		textPane.setBounds(10, 11, 524, 300);
		frmEnhanceReadability.getContentPane().add(textPane);

		frmEnhanceReadability.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		frmEnhanceReadability.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmEnhanceReadability.setLocation(new Point(400, 300));
		frmEnhanceReadability.getContentPane().setSize(new Dimension(560, 360));
		frmEnhanceReadability.getContentPane().setLayout(null);
		
		final JScrollPane scrollPane = new JScrollPane(textPane);
		scrollPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		scrollPane.setBorder(new LineBorder(new Color(130, 135, 144), 1, true));
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(10, 11, 524, 300);
		scrollPane.getVerticalScrollBar().setValue(0);
		scrollPane.getViewport().setViewPosition(new Point(0,0));
		frmEnhanceReadability.getContentPane().add(scrollPane);
		
		JButton btnNewButton = new JButton("Close");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EnhanceStatistics.this.frmEnhanceReadability.dispose();
			}
		});
		btnNewButton.setBounds(227, 322, 89, 23);
		frmEnhanceReadability.getContentPane().add(btnNewButton);
		frmEnhanceReadability.setSize(new Dimension(560, 360));
		frmEnhanceReadability.setTitle("Enhance Statistics");
		frmEnhanceReadability.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				String setTextPane = "";
				ReadJson rj = new ReadJson();
				rj.readJsonFile();
				child = rj.childReadability;
				elder = rj.elderReadability;
				if(!child.isEmpty()){
					setTextPane = "To make your Document/Text understable to children, we suggest you to replace following words, with shorter words of same meaning.";
					setTextPane +="\n*****************************************************************************************************\n";
					setTextPane +="\n*****************************************************************************************************\n";
					for(String s : child){
						setTextPane += "   "+s+"\n";
					}
				}else if(!elder.isEmpty()){
					setTextPane +="\n*****************************************************************************************************\n";
					setTextPane +="\n*****************************************************************************************************\n";
					setTextPane += "We suggest you to replace these complex words";
					for(String s : elder){
						setTextPane += "   "+s+"\n";
					}
					
				} else{
					setTextPane += "Your Document/Text  Doesn't have much complex words to trouble you.";
				}
				textPane.setText(setTextPane);
				textPane.setEditable(false);
			}
		});
		
		
	}
}
