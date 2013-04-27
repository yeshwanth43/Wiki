package controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.ComponentOrientation;
import java.awt.Point;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import javax.swing.JTextPane;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;

public class MainEntry implements Runnable {

	public JFrame frmWelcome;
	private JTextPane textPane;
	private boolean flag = true, flag1 = true;
	public String command = new String();
	private Thread threadRecognizer;
	private Thread threadActions;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainEntry window = new MainEntry();
					window.frmWelcome.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainEntry() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmWelcome = new JFrame();
		frmWelcome.getContentPane().setBackground(Color.WHITE);
		frmWelcome.getContentPane().setForeground(Color.WHITE);
		frmWelcome.getContentPane().setSize(new Dimension(780, 460));
		frmWelcome.getContentPane().setLayout(null);

		final JLabel lblWelcome = new JLabel("Welcome");
		lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblWelcome.setBounds(357, 11, 124, 38);
		frmWelcome.getContentPane().add(lblWelcome);

		final JLabel lblTestRecognizer = new JLabel("Test Recognizer");
		lblTestRecognizer.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblTestRecognizer.setBounds(314, 383, 197, 38);
		frmWelcome.getContentPane().add(lblTestRecognizer);

		final JButton btnStartWiki = new JButton("Start Wiki");
		btnStartWiki.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Introduction intr = new Introduction();
				intr.frmTutorial.setVisible(true);
				MainEntry.this.frmWelcome.dispose();
			}
		});
		btnStartWiki.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		btnStartWiki.setBackground(Color.LIGHT_GRAY);
		btnStartWiki.setBounds(606, 327, 124, 23);
		frmWelcome.getContentPane().add(btnStartWiki);

		textPane = new JTextPane();
		textPane.setSelectionColor(Color.LIGHT_GRAY);
		textPane.setFont(new Font("Tahoma", Font.BOLD, 16));
		textPane.setEditable(false);
		textPane.setBorder(new LineBorder(new Color(192, 192, 192), 0, true));
		textPane.setBounds(100, 11, 600, 78);
		textPane.setVisible(false);
		frmWelcome.getContentPane().add(textPane);

		JButton btnNewButton = new JButton("");
		btnNewButton.setFocusPainted(false);
		btnNewButton.setFocusTraversalKeysEnabled(false);
		btnNewButton.setFocusable(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton
				.setBorder(new LineBorder(new Color(192, 192, 192), 0, true));
		btnNewButton.setIcon(new ImageIcon(
				"././assets/photos/Microphone-icon.jpg"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (flag) {
					flag = false;
					lblWelcome.setVisible(false);
					lblTestRecognizer.setVisible(false);
					btnStartWiki.setVisible(false);
					textPane.setVisible(true);
					threadInitialize();
					startRecognizer();
				} else {
					JOptionPane.showMessageDialog(frmWelcome,
							"Recognizer already started", "Recognizer Started",
							JOptionPane.WARNING_MESSAGE);
				}

			}
		});
		btnNewButton.setBounds(271, 100, 250, 250);
		frmWelcome.getContentPane().add(btnNewButton);

		frmWelcome.setLocation(new Point(450, 450));
		frmWelcome.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		frmWelcome.setFont(new Font("Arial", Font.PLAIN, 12));
		frmWelcome.setTitle("Welcome");
		frmWelcome.setResizable(false);
		frmWelcome.setSize(new Dimension(780, 460));
		frmWelcome.setBounds(100, 100, 780, 460);
		frmWelcome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void threadInitialize() {
		threadRecognizer = new Thread(this, "Recognizer");
		threadActions = new Thread(this, "Actions");
	}

	public void startRecognizer() {
		try {
			threadRecognizer.start();
			threadActions.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void stopRecognizer() {
		try {
			threadActions = null;
			threadRecognizer = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {

		if (flag1) {
			flag1 = false;
			System.out.println("**************Thread Started*************");
			System.out.println("**************Recognizer Started*********");

			ConfigurationManager cm = new ConfigurationManager(
					"conf/commands.config.xml");
			Recognizer recognizer = (Recognizer) cm.lookup("recognizer");
			recognizer.allocate();
			Microphone microphone = (Microphone) cm.lookup("microphone");
			if (!microphone.startRecording()) {
				flag = true;
				System.out.println("Cannot start microphone.");
				recognizer.deallocate();
				threadRecognizer = null;
				JOptionPane.showMessageDialog(frmWelcome,
						"Cannot hear on your Microphone, caught an Exception",
						"Recognizer Exception", JOptionPane.WARNING_MESSAGE);
			}
			while (true) {
				System.out.println("Start speaking. Press Ctrl-C to quit.\n");
				Result result = recognizer.recognize();
				if (result != null) {
					command = result.getBestFinalResultNoFiller();
					textPane.setText("\n\t\tYou said: " + command);
					System.out.println("You said: " + command + '\n');
				} else {
					System.out.println("I can't hear what you said.\n");
				}
			}
		} else {
			System.out.println("in run ");
			init();
		}

	}

	@SuppressWarnings("static-access")
	private void init() {
		Introduction intro = new Introduction();
		Wiki wi = new Wiki();
		WikiSynthesizer wSynth = new WikiSynthesizer();
		Readability readability = new Readability();
		POS pos = new POS();
		AboutUs abs = new AboutUs();

		boolean introduction = true, wikiWindow = false, introductionMenu = false, readabilityWindow = false, synthWindow = false, synthWindowMenu = false, readabilityMenu = false, posWindow = false, posMenu = false, absWindow = false, synthWindowSubmenu = false;
		while (true) {
			System.out.println("****************COMMAND*********************"
					+ "\n" + command);
			if (command.equalsIgnoreCase("Application start") && introduction) {
				intro.frmTutorial.setVisible(true);
				introduction = false;
				introductionMenu = true;
			} else if ((command.equalsIgnoreCase("leave Introduction"))) {
				wi.frmWiki.setVisible(true);
				intro.frmTutorial.dispose();
				wikiWindow = true;
				synthWindow = true;
				readabilityWindow = true;
				posWindow = true;
				absWindow = true;
			} else if (command.equalsIgnoreCase("switch to home") && wikiWindow) {
				if (wSynth.frmWikiSynthesizer.isActive()) {
					wSynth.frmWikiSynthesizer.dispose();
				} else if (readability.frmReadabilityStatistics.isActive()) {
					readability.frmReadabilityStatistics.dispose();
				} else if (pos.frmPartsOfSpeech.isActive()) {
					pos.frmPartsOfSpeech.dispose();
				} else if (abs.frmAboutUs.isActive()) {
					abs.frmAboutUs.dispose();
				}
				wi.frmWiki.setVisible(true);
				synthWindow = true;
				readabilityWindow = true;
				posWindow = true;
				absWindow = true;
				wikiWindow = false;
			} else if (command.equalsIgnoreCase("synthesizer start")
					&& synthWindow) {
				if (wi.frmWiki.isActive()) {
					wi.frmWiki.dispose();
				}
				wSynth.frmWikiSynthesizer.setVisible(true);
				synthWindow = false;
				readabilityWindow = false;
				posWindow = false;
				absWindow = false;
				synthWindowMenu = true;
				wikiWindow = true;
			} else if (command
					.equalsIgnoreCase("begin readability calculations")
					&& readabilityWindow) {

				if (wi.frmWiki.isActive()) {
					wi.frmWiki.dispose();
				}
				readability.frmReadabilityStatistics.setVisible(true);
				synthWindow = false;
				readabilityWindow = false;
				posWindow = false;
				absWindow = false;
				readabilityMenu = true;
				wikiWindow = true;
			} else if (command.equalsIgnoreCase("Parts of speech enhancement")
					&& posWindow) {

				if (wi.frmWiki.isActive()) {
					wi.frmWiki.dispose();
				}
				pos.frmPartsOfSpeech.setVisible(true);
				synthWindow = false;
				readabilityWindow = false;
				posWindow = false;
				absWindow = false;
				posMenu = true;
				wikiWindow = true;
			} else if ((command.equalsIgnoreCase("About us") || command
					.equalsIgnoreCase("About Developers")) && absWindow) {

				if (wi.frmWiki.isActive()) {
					wi.frmWiki.dispose();
				}
				abs.frmAboutUs.setVisible(true);
				synthWindow = false;
				readabilityWindow = false;
				posWindow = false;
				absWindow = false;
				wikiWindow = true;
			} else if (command.equalsIgnoreCase("shutdown Application")) {
				System.exit(0);
			}
			try {
				threadActions.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
