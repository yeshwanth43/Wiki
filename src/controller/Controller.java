package controller;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;

public class Controller implements Runnable {

	public String command = new String();
	private Thread threadRecognizer;
	private Thread threadActions;
	private boolean flag = true;

	public Controller() {
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

		if (flag) {
			flag = false;
			System.out.println("**************Thread Started*************");
			System.out.println("**************Recognizer Started*********");

			ConfigurationManager cm = new ConfigurationManager(
					"conf/commands.config.xml");
			Recognizer recognizer = (Recognizer) cm.lookup("recognizer");
			recognizer.allocate();
			Microphone microphone = (Microphone) cm.lookup("microphone");
			if (!microphone.startRecording()) {
				System.out.println("Cannot start microphone.");
				recognizer.deallocate();
				System.exit(1);
			}
			while (true) {
				System.out.println("Start speaking. Press Ctrl-C to quit.\n");
				Result result = recognizer.recognize();
				if (result != null) {
					command = result.getBestFinalResultNoFiller();
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

	public static void main(String[] args) {

		Controller control = new Controller();
		control.startRecognizer();
		System.out.println("in main method");

	}

}