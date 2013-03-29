package speechRecognizer;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;

public class RecognizeCommands {

	public static void main(String[] args) {
		ConfigurationManager cm = new ConfigurationManager();
		if (args.length > 0)
			cm = new ConfigurationManager(args[0]);
		else {
			cm = new ConfigurationManager("assets/command.config.xml");
			// }
			System.out.println("Loading...");
			Recognizer recognizer = (Recognizer) cm.lookup("recognizer");
			recognizer.allocate();

			Microphone microphone = (Microphone) cm.lookup("microphone");
			if (!(microphone.startRecording())) {
				System.out.println("Cannot start microphone.");
				recognizer.deallocate();
				System.exit(1);
			}

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
	}
}
