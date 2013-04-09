package controller;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

import speechSynthesis.Player;
import speechSynthesis.SynthesisV1;
import documentParsing.ReadJson;

public class DocumentSynthesizerStatus {

	public JFrame frameDSS;
	private ArrayList<String> arrayList = new ArrayList<String>();
	private Player player = new Player();

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
		frameDSS = new JFrame();
		frameDSS.setLocation(new Point(200, 150));
		frameDSS.setResizable(false);
		final JTextPane textPane = new JTextPane();
		textPane.setEditable(false);

		textPane.setToolTipText("content of the Document");
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

		JScrollPane scrollPane = new JScrollPane(textPane);
		scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(219, 11, 685, 277);
		frameDSS.getContentPane().add(scrollPane);

		JButton btnNewButton = new JButton("Synthesize");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SynthesisV1 sv1 = new SynthesisV1();
				sv1.speak(arrayList.toString());
				player.start();
			}
		});
		btnNewButton.setBounds(219, 299, 120, 23);
		frameDSS.getContentPane().add(btnNewButton);
		
		JButton btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				player.stop();
			}
		});
		btnStop.setBounds(784, 299, 120, 23);
		frameDSS.getContentPane().add(btnStop);
		
		JButton btnPause = new JButton("pause");
		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				player.pausePlayer();
			}
		});
		btnPause.setBounds(419, 299, 120, 23);
		frameDSS.getContentPane().add(btnPause);
		
		JButton btnResume = new JButton("resume");
		btnResume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				player.resumePlayer();
			}
		});
		btnResume.setBounds(596, 299, 120, 23);
		frameDSS.getContentPane().add(btnResume);
		
		JButton btnBackToSynthesizer = new JButton("Back to Synthesizer");
		btnBackToSynthesizer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WikiSynthesizer wiks = new WikiSynthesizer();
				DocumentSynthesizerStatus.this.frameDSS.dispose();
				wiks.frmWikiSynthesizer.setVisible(true);
			}
		});
		btnBackToSynthesizer.setBounds(38, 299, 154, 23);
		frameDSS.getContentPane().add(btnBackToSynthesizer);
		
		JButton button = new JButton("Back to Wiki");
		button.setBounds(59, 144, 108, 23);
		frameDSS.getContentPane().add(button);
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