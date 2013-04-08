package controller;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;

public class Controller extends Thread {

	public String command = new String();

	public static void main(String[] args) {

		Controller control = new Controller();
		control.start();
		control.init();

	}

	public void startASR() {

	}

	@Override
	public void run() {
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

	}

	public void init() {
		Wiki wi = new Wiki();
		WikiSynthesizer synthWindow = new WikiSynthesizer();
		Introduction intro = new Introduction();
		boolean licenseAgreement = true, introduction = true, wikiWindow = true;;
		while (true) {
			System.out.println("****************COMMAND*********************"
					+ "\n" + command);
			if (command.equalsIgnoreCase("")
					&& licenseAgreement) {
				licenseAgreement = false;
				intro.frmTutorial.setVisible(true);
			} else if (command.contains("go to") && introduction) {
				intro.frmTutorial.dispose();
				wi.frmWiki.setVisible(true);
				introduction = false;
			} else if (command.equalsIgnoreCase("Go to synthesizer")) {
				synthWindow.frmWikiSynthesizer.setVisible(true);
				wi.frmWiki.dispose();
			} else if (command.equalsIgnoreCase("") && wikiWindow) {
				
			}

			try {
				Controller.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
