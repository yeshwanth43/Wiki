package controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.JTextPane;

import documentParsing.ReadJson;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

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
		final JTextPane textPane = new JTextPane();
		textPane.setBounds(10, 11, 484, 260);
		frmEnhanceReadability.getContentPane().add(textPane);
		frmEnhanceReadability.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				String setTextPane = "";
				ReadJson rj = new ReadJson();
				rj.readJsonFile();
				child = rj.childReadability;
				elder = rj.elderReadability;
				setTextPane = "To make your Document/Text understable to children, we suggest you to replace following words, with shorter words of same meaning.";
				setTextPane +="\n****************************************************************************************************************\n";
				setTextPane +="\n****************************************************************************************************************\n";
				for(String s : child){
					setTextPane += s+"\t\n";
					System.out.println(s+"   \t tags");
				}
				textPane.setText(setTextPane);
				textPane.setEditable(false);
			}
		});
		frmEnhanceReadability.getContentPane().setSize(new Dimension(520, 320));
		frmEnhanceReadability.getContentPane().setLayout(null);
		
		frmEnhanceReadability.setMinimumSize(new Dimension(400, 300));
		frmEnhanceReadability.setSize(new Dimension(520, 320));
		frmEnhanceReadability.setFont(new Font("Arial", Font.PLAIN, 12));
		frmEnhanceReadability.setTitle("Enhance Readability");
		frmEnhanceReadability.setBounds(100, 100, 520, 320);
		frmEnhanceReadability.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

}
