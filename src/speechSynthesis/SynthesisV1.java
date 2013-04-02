package speechSynthesis;

import java.io.IOException;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import com.sun.speech.freetts.audio.AudioPlayer;
import com.sun.speech.freetts.audio.SingleFileAudioPlayer;

public class SynthesisV1 {

	/**
	 * @author yeshwanth
	 * @param args
	 */

	public void speak(String input) {
		AudioPlayer player = null;
		String voiceName = "kevin16";
		VoiceManager voiceManager = VoiceManager.getInstance();
		Voice voice = voiceManager.getVoice(voiceName);
		voice.allocate();
		voice.setRate(150);
		voice.speak(input);
//		player = new SingleFileAudioPlayer("C://output",Type.WAVE);
		voice.deallocate(); 
	}

	public static void main(String[] args) throws IOException {
SynthesisV1 sv1= new SynthesisV1();
sv1.speak("hey hi how are u , hope u are doing great bro");

	}

}
