package speechSynthesis;

import java.io.IOException;

import javax.speech.AudioException;
import javax.speech.EngineStateError;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import com.sun.speech.freetts.jsapi.FreeTTSSynthesizer;

public class SynthesisV1 {

	/**
	 * @author yeshwanth
	 * @param args
	 */

	private FreeTTSSynthesizer synth;
	public void speak(String input) {
		String voiceName = "kevin16";
		VoiceManager voiceManager = VoiceManager.getInstance();
		Voice voice = voiceManager.getVoice(voiceName);
		voice.allocate();
		voice.setRate(150);
		voice.speak(input);
//		player = new SingleFileAudioPlayer("C://output",Type.WAVE);
		voice.deallocate(); 
	}
	
	
	public void synth() throws AudioException, EngineStateError{
		
		synth = new FreeTTSSynthesizer(null);
		this.synth.speakPlainText("hi how are u", null);
		synth.pause();
		synth.resume();
		synth.cancel();
	}

	public static void main(String[] args) throws IOException, AudioException, EngineStateError {
SynthesisV1 sv1= new SynthesisV1();
sv1.synth();

	}

}
