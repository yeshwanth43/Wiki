package speechSynthesis;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class Synthesizer {

	/**
	 * @author yeshwanth
	 * @param args
	 */

	public ArrayList<String> arrayList = new ArrayList<String>();

	public void speakSentence(String input) {
		String voiceName = "kevin16";
		VoiceManager voiceManager = VoiceManager.getInstance();
		Voice voice = voiceManager.getVoice(voiceName);
		voice.allocate();
		voice.speak(input);
		voice.deallocate();
	}

	@SuppressWarnings("unchecked")
	private ArrayList<String> readJsonFile() {
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader(
					"parsed-contents/parsedContext.json"));
			JSONObject jsonObject = (JSONObject) obj;
			arrayList = (ArrayList<String>) jsonObject.get("SENTENCES");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return arrayList;
	}

	public void initSynthesizer() {
		readJsonFile();
	}

	public void instructions(){
		
	}
	public static void main(String[] args) {

		Synthesizer syn = new Synthesizer();
		syn.readJsonFile();
		// syn.speakSentence("");
	}

}
