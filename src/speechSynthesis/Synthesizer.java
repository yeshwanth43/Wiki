package speechSynthesis;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class Synthesizer {

	/**
	 * @author yeshwanth
	 * @param args
	 */

	

	public void speakSentence(String input) {
		String voiceName = "kevin16";
		VoiceManager voiceManager = VoiceManager.getInstance();
		Voice voice = voiceManager.getVoice(voiceName);
		voice.allocate();
		voice.speak(input);
		voice.deallocate();
	}

//
//
//	public void initSynthesizer() {
//		readJsonFile();
//	}
//
//	public void instructions(){
//		
//	}
//	public static void main(String[] args) {
//
//		Synthesizer syn = new Synthesizer();
//		syn.readJsonFile();
//		// syn.speakSentence("");
//	}

}
