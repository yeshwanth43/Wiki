package speechSynthesis;

import java.io.PrintStream;
import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;

public class MySynthesizerModeDesc extends SynthesizerModeDesc {
	private PlayerModel playerModel = null;
	private Synthesizer synthesizer = null;
	private Monitor monitor = null;
	private boolean synthesizerLoaded = false;

	public MySynthesizerModeDesc(SynthesizerModeDesc modeDesc,
			PlayerModel playerModel) {
		super(modeDesc.getEngineName(), modeDesc.getModeName(), modeDesc
				.getLocale(), modeDesc.getRunning(), modeDesc.getVoices());

		this.playerModel = playerModel;
	}

	public synchronized boolean isSynthesizerLoaded() {
		if (this.synthesizer == null) {
			return false;
		}
		return (this.synthesizer.getEngineState() & 0x4) != 0L;
	}

	public synchronized Synthesizer getSynthesizer() {
		debugPrint("MyModeDesc.getSynthesizer(): " + getEngineName());
		return this.synthesizer;
	}

	public Synthesizer createSynthesizer() {
		try {
			debugPrint("Creating " + getEngineName() + "...");
			this.synthesizer = Central.createSynthesizer(this);

			if (this.synthesizer == null) {
				System.out.println("Central created null synthesizer");
			} else {
				this.synthesizer.allocate();
				this.synthesizer.resume();
				this.monitor = new Monitor(this.synthesizer, getEngineName());
				debugPrint("...created monitor");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.synthesizer;
	}

	public Synthesizer loadSynthesizer() {
		try {
			if (!this.synthesizerLoaded) {
				debugPrint("Loading " + getEngineName() + "...");
				this.synthesizerLoaded = true;
				SynthesizerLoader loader = new SynthesizerLoader(
						this.synthesizer, this);

				loader.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.synthesizer;
	}

	public synchronized Monitor getMonitor() {
		if (this.monitor == null) {
			createSynthesizer();
		}
		return this.monitor;
	}

	public PlayerModel getPlayerModel() {
		return this.playerModel;
	}

	public String toString() {
		return getEngineName();
	}

	private void debugPrint(String statement) {
		PlayerModelImpl.debugPrint(statement);
	}
}
