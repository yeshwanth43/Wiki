package speechSynthesis;

import java.io.IOException;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import documentParsing.DocParseV1;

public class SynthesisV1 {

	/**
	 * @param args
	 */
	
	public void speak(String input) {
		 String voiceName = "kevin16";
		 VoiceManager voiceManager = VoiceManager.getInstance();
		 Voice voice = voiceManager.getVoice(voiceName);
		 voice.allocate();
		 voice.setRate(150);
		 voice.speak(input);
		 voice.deallocate();
	}
	
	public static void main(String[] args) throws IOException {

		SynthesisV1 sv1= new SynthesisV1();
		DocParseV1 dv = new DocParseV1();
		String input=dv.parse();
		sv1.speak(input);
	}

}
