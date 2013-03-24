package speechRecognizer;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;

public class HelloNGram {
	public static void main(String[] args) {
		ConfigurationManager cm = new ConfigurationManager("assets/hellongram.config.xml");
//		if (args.length > 0)
//			cm = new ConfigurationManager(args[0]);
//		else {
//			cm = new ConfigurationManager(HelloNGram.class.getResource("hellongram.config.xml"));
//		}
		System.out.println("Loading...");
		Recognizer recognizer = (Recognizer) cm.lookup("recognizer");
		recognizer.allocate();

		Microphone microphone = (Microphone) cm.lookup("microphone");
		if (!(microphone.startRecording())) {
			System.out.println("Cannot start microphone.");
			recognizer.deallocate();
			System.exit(1);
		}

		printInstructions();
		while (true) {
			System.out.println("Start speaking. Press Ctrl-C to quit.\n");

			Result result = recognizer.recognize();

			if (result != null) {
				String resultText = result.getBestResultNoFiller();
				System.out.println("You said: " + resultText + '\n');
			} else {
				System.out.println("I can't hear what you said.\n");
			}
		}
	}

	private static void printInstructions() {
		System.out
				.println("Sample sentences:\nthe green one right in the middle\nthe purple one on the lower right side\nthe closest purple one on the far left side\nthe only one left on the left\n\nRefer to the file hellongram.test for a complete list.\n");
	}
}
