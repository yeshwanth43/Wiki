package speechSynthesis;

import javax.speech.synthesis.Synthesizer;

public class SynthesizerLoader extends Thread {
	private Synthesizer synthesizer;
	private MySynthesizerModeDesc modeDesc;
	private PlayerModel playerModel;

	public SynthesizerLoader(Synthesizer synthesizer,
			MySynthesizerModeDesc modeDesc) {
		this.synthesizer = synthesizer;
		this.modeDesc = modeDesc;
		this.playerModel = modeDesc.getPlayerModel();
	}

	public void run() {
		try {
			System.out.println("allocating...");
			this.synthesizer.allocate();
			System.out.println("...allocated");
			this.synthesizer.resume();
			System.out.println("...resume");
			this.playerModel.setVoiceList(this.modeDesc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}