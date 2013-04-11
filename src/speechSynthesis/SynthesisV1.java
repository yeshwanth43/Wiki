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

	public void speak(String input,float hertz, float range,float stretch, float wpm ) {
		AudioPlayer audioplayer = null;
		String voiceName = "kevin16";
		VoiceManager voiceManager = VoiceManager.getInstance();
		Voice voice = voiceManager.getVoice(voiceName);
		voice.allocate();
		audioplayer = new SingleFileAudioPlayer("././assets/synthesized", Type.WAVE);
		voice.setAudioPlayer(audioplayer);
		voice.setPitch(hertz);
		voice.setPitchRange(range);
		voice.setRate(wpm);
		voice.setDurationStretch(stretch);
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
		sv1.speak("Wiki is an interactive learning application, which makes reading a fun and more interesting. WIKI synthesizes the given document or text to speech. It equips with the facilities to play,pause,resume, and save the audio file. WIKI also renders a facility to calculate the Readability Index, which is a scale, for the understandability of text. Further, It also gives a scale that tells wether given document or text is in understandable way to different age groups. The beauty of  WIKI, is in the fact, it allows voice commands to interact with the application. Automatic Speech Recognizer in WIKI recognizes the speech, and consequent action is performed accordingly. So, One just  need to say what WIKI has to do, and WIKI gets it done.",90f,11f,100f,1f);
	}
}
