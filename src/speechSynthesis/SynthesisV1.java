package speechSynthesis;

import java.io.IOException;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import com.sun.speech.freetts.audio.AudioPlayer;
import com.sun.speech.freetts.audio.SingleFileAudioPlayer;
import javax.sound.sampled.AudioFileFormat.Type;

public class SynthesisV1 {

	/**
	 * @author yeshwanth
	 * @param args
	 */

	public void speak(String input) {
		AudioPlayer audioplayer = null;
		String voiceName = "kevin16";
		VoiceManager voiceManager = VoiceManager.getInstance();
		Voice voice = voiceManager.getVoice(voiceName);
		voice.allocate();
		audioplayer = new SingleFileAudioPlayer("D://yesh workspace/Wiki/assets/synthesize", Type.WAVE);
		voice.setAudioPlayer(audioplayer);
		voice.speak(input);
		audioplayer.close();
		voice.deallocate();
	}
	
	public void speakSentence(String input){
		String voiceName = "kevin16";
		VoiceManager voiceManager = VoiceManager.getInstance();
		Voice voice = voiceManager.getVoice(voiceName);
		voice.allocate();
		voice.speak(input);
		voice.deallocate();
	}

	public static void main(String[] args) throws IOException {
		SynthesisV1 sv1 = new SynthesisV1();
		sv1.speak("hey hi how are u , hope u are doing great bro");
	}
}
