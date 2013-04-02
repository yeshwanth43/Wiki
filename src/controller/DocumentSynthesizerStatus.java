package controller;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JLabel;

import documentParsing.ReadJson;
import javax.swing.JButton;

import speechSynthesis.Synthesizer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

public class DocumentSynthesizerStatus {

	public JFrame frameDSS;
	private ArrayList<String> arrayList = new ArrayList<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DocumentSynthesizerStatus window = new DocumentSynthesizerStatus();
					window.frameDSS.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DocumentSynthesizerStatus() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		int sentenceCount = 0;
		frameDSS = new JFrame();
		frameDSS.setResizable(false);
		final JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		
		textPane.setToolTipText("this will show all the sentences and statistics of the document");
		textPane.setName("SentenceDisplayer");
		textPane.setFont(new Font("Arial", Font.PLAIN, 11));
		textPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		textPane.setBounds(219, 11, 685, 277);
		frameDSS.getContentPane().add(textPane);
		frameDSS.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				ReadJson rj = new ReadJson();
				String sentences = new String();
				arrayList = rj.readJsonFile();
				for (String s : arrayList) {
					sentences += s;
				}
				textPane.setText(sentences);
			}
		});
//		textPane.addComponentListener(new ComponentAdapter() {
//			@Override
//			public void componentShown(ComponentEvent arg0) {
				
//			}
//		});
		frameDSS.getContentPane().setFont(new Font("Arial", Font.PLAIN, 11));
		frameDSS.getContentPane().setComponentOrientation(
				ComponentOrientation.LEFT_TO_RIGHT);
		frameDSS.getContentPane().setLayout(null);

		JLabel lblDocumentStatistics = new JLabel("Document Statistics");
		lblDocumentStatistics.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDocumentStatistics.setBounds(10, 11, 149, 23);
		frameDSS.getContentPane().add(lblDocumentStatistics);

		JTextPane textPane_1 = new JTextPane();
		textPane_1.setBackground(Color.LIGHT_GRAY);
		textPane_1.setEditable(false);
		textPane_1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		textPane_1.setBounds(10, 35, 199, 253);
		frameDSS.getContentPane().add(textPane_1);
		
		JScrollPane scrollPane = new JScrollPane(textPane);
		scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(219, 11, 685, 277);
		frameDSS.getContentPane().add(scrollPane);
		
		JButton btnNewButton = new JButton("Synthesize");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Synthesizer synth = new Synthesizer();
				synth.speakSentence(arrayList.toString());
			}
		});
		btnNewButton.setBounds(458, 299, 176, 23);
		frameDSS.getContentPane().add(btnNewButton);
		frameDSS.setBackground(Color.WHITE);
		frameDSS.setVisible(true);
		frameDSS.setTitle("Synthesizer");
		frameDSS.setName("Synthesizer");
		frameDSS.setFont(new Font("Arial", Font.PLAIN, 12));
		frameDSS.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		frameDSS.setBounds(100, 100, 920, 360);
		frameDSS.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
