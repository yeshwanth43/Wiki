package speechSynthesis;

import java.util.Locale;

import javax.speech.EngineCreate;
import javax.speech.EngineException;
import javax.speech.EngineList;
import javax.speech.EngineStateError;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import com.sun.speech.freetts.jsapi.FreeTTSEngineCentral;

import de.dfki.lt.freetts.en.us.MbrolaVoice;
import documentParsing.SampleTika;

public class SampleSynthesis {

	/**
	 * @author yeshwanth
	 * @param args
	 */
	private Synthesizer synthesizer;

	public void createSynthesizer() {

		try {
			SynthesizerModeDesc desc = new SynthesizerModeDesc(null, "time",
					Locale.US, Boolean.FALSE, null);
			FreeTTSEngineCentral central = new FreeTTSEngineCentral();
			EngineList list = central.createEngineList(desc);
			if (list.size() > 0) {
				EngineCreate creator = (EngineCreate) list.get(0);
				this.synthesizer = ((Synthesizer) creator.createEngine());
			}
			if (this.synthesizer == null) {
				System.err.println("Cannot create synthesizer");
				System.exit(1);
			}
			this.synthesizer.allocate();
			this.synthesizer.resume();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void speak(String input) {
//		 String voiceName = "kevin16";
//		 VoiceManager voiceManager = VoiceManager.getInstance();
//		 Voice voice = voiceManager.getVoice(voiceName);
//		 voice.allocate();
//		 voice.setRate(150);
//		 voice.speak(input);
//		 voice.deallocate();
//		
		
		this.synthesizer.speakPlainText(input, null);
		try {
			this.synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
			this.synthesizer.deallocate();
		} catch (EngineException | EngineStateError | IllegalArgumentException
				| InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// System.setProperty("mbrola.base",
		// "C:\\Users\\yeshwanth\\Downloads\\rar's\\speech\\freetts-1.2.2-bin\\freetts-1.2\\mbrola");
		SampleSynthesis sample = new SampleSynthesis();
		sample.createSynthesizer();
		System.out.println("1");
		SampleTika s = new SampleTika();
		sample.speak(s.clean());

	}
}
