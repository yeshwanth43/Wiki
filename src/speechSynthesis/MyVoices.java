package speechSynthesis;

import javax.speech.synthesis.Voice;

class MyVoice extends Voice {
	public MyVoice(String name, int gender, int age, String style) {
		super(name, gender, age, style);
	}

	public String toString() {
		return getName();
	}
}