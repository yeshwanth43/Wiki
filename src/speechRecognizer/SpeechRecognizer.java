package speechRecognizer;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;

public class SpeechRecognizer {

	public String recognizeSpeech() {
		ConfigurationManager cm = new ConfigurationManager(
				SpeechRecognizer.class.getResource("helloworld.config.xml"));
		Recognizer recognizer = (Recognizer) cm.lookup("recognizer");
		recognizer.allocate();
		Microphone microphone = (Microphone) cm.lookup("microphone");
		if (!microphone.startRecording()) {
			System.out.println("Cannot start microphone.");
			recognizer.deallocate();
			System.exit(1);
		}

		System.out.println("Start speaking. Press Ctrl-C to quit.\n");
		Result result = recognizer.recognize();
		if (result != null) {
			String resultText = result.getBestFinalResultNoFiller();
			System.out.println("You said: " + resultText + '\n');
		} else {
			System.out.println("I can't hear what you said.\n");
		}

		return null;
	}

	public static void main(String[] args) {

		SpeechRecognizer asr = new SpeechRecognizer();
		asr.recognizeSpeech();
	}
}
