package controller;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;
import speechRecognizer.SpeechRecognizer;

public class Controller extends Thread {

	public String command = new String();

	public static void main(String[] args) {

		Controller control = new Controller();
		control.start();
		// try {
		// Controller.sleep(5000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
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
		LicenseAgreement lAG = new LicenseAgreement();
		lAG.frmLicenseAgreement.setVisible(true);
		Introduction tutorial = new Introduction();
		WikiSynthesizer synthWindow = new WikiSynthesizer();
		boolean licenseAgreement = true, introduction = true;
		while (true) {
			System.out.println("****************COMMAND*********************"
					+ "\n" + command);
			if (command.equalsIgnoreCase("I Accept Agreement")
					&& licenseAgreement) {
				licenseAgreement = false;
				lAG.frmLicenseAgreement.dispose();
				tutorial.frmTutorial.setVisible(true);
			} else if (command.equalsIgnoreCase("Go To Synthesizer")
					&& introduction) {
				introduction = false;
				tutorial.frmTutorial.dispose();
				synthWindow.frmWikiSynthesizer.setVisible(true);
			} 

			try {
				Controller.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
