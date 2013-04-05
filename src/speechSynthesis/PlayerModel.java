package speechSynthesis;

import javax.speech.synthesis.SynthesizerModeDesc;
import javax.swing.ListModel;

public abstract interface PlayerModel {
	public abstract void play(Playable paramPlayable);

	public abstract void play(int paramInt);

	public abstract boolean isPaused();

	public abstract void pause();

	public abstract void resume();

	public abstract void stop();

	public abstract void cancel();

	public abstract void setMonitorVisible(boolean paramBoolean);

	public abstract boolean isMonitorVisible();

	public abstract void createSynthesizers();

	public abstract Monitor getMonitor(int paramInt);

	public abstract Monitor getMonitor();

	public abstract void setMonitor(Monitor paramMonitor);

	public abstract void setSynthesizer(int paramInt);

	public abstract void setVoice(int paramInt);

	public abstract void setVoiceList(
			SynthesizerModeDesc paramSynthesizerModeDesc);

	public abstract float getVolume();

	public abstract boolean setVolume(float paramFloat);

	public abstract float getSpeakingRate();

	public abstract boolean setSpeakingRate(float paramFloat);

	public abstract float getPitch();

	public abstract boolean setPitch(float paramFloat);

	public abstract float getRange();

	public abstract boolean setRange(float paramFloat);

	public abstract ListModel getPlayList();

	public abstract ListModel getVoiceList();

	public abstract ListModel getSynthesizerList();

	public abstract Object getPlayableAt(int paramInt);

	public abstract void addPlayable(Playable paramPlayable);

	public abstract void removePlayableAt(int paramInt);

	public abstract void close();
}
