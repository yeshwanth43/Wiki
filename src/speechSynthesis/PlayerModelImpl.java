package speechSynthesis;

import java.beans.PropertyVetoException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.speech.AudioException;
import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.EngineList;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.speech.synthesis.SynthesizerProperties;
import javax.speech.synthesis.Voice;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;

public class PlayerModelImpl implements PlayerModel {
	private Synthesizer synthesizer;
	private Monitor monitor;
	private boolean monitorVisible = false;
	private boolean paused = false;
	private boolean stopped = false;
	private boolean playingFile = false;
	private DefaultListModel playList;
	private DefaultComboBoxModel synthesizerList;
	private DefaultComboBoxModel voiceList;
	private float volume = -1.0F;
	private static boolean debug = false;

	private Set loadedSynthesizers;

	public PlayerModelImpl() {
		this.playList = new DefaultListModel();
		this.synthesizerList = new DefaultComboBoxModel();
		this.voiceList = new DefaultComboBoxModel();
		this.loadedSynthesizers = new HashSet();
	}

	public void createSynthesizers() {
		try {
			EngineList list = Central.availableSynthesizers(null);
			Enumeration e = list.elements();

			while (e.hasMoreElements()) {
				MySynthesizerModeDesc myModeDesc = new MySynthesizerModeDesc(
						(SynthesizerModeDesc) e.nextElement(), this);

				debugPrint(myModeDesc.getEngineName() + " "
						+ myModeDesc.getLocale() + " "
						+ myModeDesc.getModeName() + " "
						+ myModeDesc.getRunning());

				this.synthesizerList.addElement(myModeDesc);
			}

			if (this.synthesizerList.getSize() > 0) {
				setSynthesizer(0);
			} else {
				System.err.println(noSynthesizerMessage());
			}
			if (this.synthesizer == null) {
				System.err.println("PlayerModelImpl: Can't find synthesizer");
				System.exit(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String noSynthesizerMessage() {
		String message = "No synthesizer created.  This may be the result of any\nnumber of problems.  It's typically due to a missing\n\"speech.properties\" file that should be at either of\nthese locations: \n\n";

		message = message + "user.home    : " + System.getProperty("user.home")
				+ "\n";
		message = message
				+ "java.home/lib: "
				+ System.getProperty("java.home")
				+ File.separator
				+ "lib\n\n"
				+ "Another cause of this problem might be corrupt or missing\n"
				+ "voice jar files in the freetts lib directory.  This problem\n"
				+ "also sometimes arises when the freetts.jar file is corrupt\n"
				+ "or missing.  Sorry about that.  Please check for these\n"
				+ "various conditions and then try again.\n";

		return message;
	}

	public void play(Playable playable) {
		if (playable != null) {
			if (playable.getType() == PlayableType.TEXT) {
				play(playable.getText());
			} else if (playable.getType() == PlayableType.JSML) {
				playJSML(playable.getText());
			} else if ((playable.getType() == PlayableType.TEXT_FILE)
					|| (playable.getType() == PlayableType.JSML_FILE)) {
				playFile(playable.getFile(), playable.getType());
			} else if (playable.getType() == PlayableType.URL) {
				try {
					playURL(new URL(playable.getName()));
				} catch (MalformedURLException mue) {
					mue.printStackTrace();
				}
			}
		}
	}

	public void play(int index) {
		if ((0 <= index) && (index < this.playList.getSize())) {
			Playable playable = (Playable) this.playList.getElementAt(index);
			if (playable != null) {
				play(playable);
			}
		}
	}

	private void play(String text) {
		this.synthesizer.speakPlainText(text, null);
	}

	private void playJSML(String jsmlText) {
		try {
			this.synthesizer.speak(jsmlText, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void playFile(File file, PlayableType type) {
		try {
			FileInputStream fileStream = new FileInputStream(file);
			playInputStream(fileStream, type);
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}
	}

	private void playInputStream(InputStream inStream, PlayableType type) {
		this.playingFile = true;
		if (inStream != null) {
			try {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(inStream));

				String line = "";
				if (type == PlayableType.TEXT_FILE) {
					while ((!isStopped())
							&& ((line = reader.readLine()) != null)) {
						if (line.length() > 0) {
							play(line);
						}
					}
				}
				if (type == PlayableType.JSML_FILE) {
					String fileText = "";
					while ((line = reader.readLine()) != null) {
						fileText = fileText + line;
					}
					if ((fileText != null) && (fileText.length() > 0)) {
						playJSML(fileText);
					}
				}
				this.stopped = false;
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
		this.playingFile = false;
	}

	private void playURL(URL url) {
		try {
			this.synthesizer.speak(url, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized boolean isPaused() {
		return this.paused;
	}

	public synchronized void pause() {
		this.paused = true;
		this.synthesizer.pause();
	}

	public synchronized void resume() {
		this.paused = false;
		try {
			this.synthesizer.resume();
		} catch (AudioException ae) {
			ae.printStackTrace();
		}
	}

	public synchronized void stop() {
		if (this.playingFile) {
			this.stopped = true;
		}
		this.synthesizer.cancelAll();
	}

	public void cancel() {
		this.synthesizer.cancel();
	}

	public void close() {
		for (Iterator i = this.loadedSynthesizers.iterator(); i.hasNext();) {
			Synthesizer synth = (Synthesizer) i.next();
			try {
				synth.deallocate();
			} catch (EngineException ee) {
				System.out.println("Trouble closing the synthesizer: " + ee);
			}
		}
	}

	private synchronized boolean isStopped() {
		return this.stopped;
	}

	public void setMonitorVisible(boolean visible) {
		this.monitorVisible = visible;
		if (this.monitor != null) {
			this.monitor.setVisible(this.monitorVisible);
		}
	}

	public boolean isMonitorVisible() {
		return this.monitorVisible;
	}

	public Monitor getMonitor(int index) {
		MySynthesizerModeDesc myModeDesc = (MySynthesizerModeDesc) this.synthesizerList
				.getElementAt(index);

		Monitor monitor = null;

		if (myModeDesc != null) {
			monitor = myModeDesc.getMonitor();
		}
		return monitor;
	}

	public Monitor getMonitor() {
		return this.monitor;
	}

	public void setMonitor(Monitor monitor) {
		this.monitor = monitor;
	}

	public void setSynthesizer(int index) {
		MySynthesizerModeDesc myModeDesc = (MySynthesizerModeDesc) this.synthesizerList
				.getElementAt(index);

		if (myModeDesc != null) {
			if ((isMonitorVisible()) && (this.monitor != null)) {
				this.monitor.setVisible(false);
			}

			this.synthesizer = myModeDesc.getSynthesizer();
			if (this.synthesizer == null) {
				this.synthesizer = myModeDesc.createSynthesizer();
				if (this.synthesizer == null) {
					debugPrint("still null");
				} else {
					debugPrint("created");
				}
			} else {
				debugPrint("not null");
			}
			this.monitor = myModeDesc.getMonitor();
			if (myModeDesc.isSynthesizerLoaded()) {
				setVoiceList(myModeDesc);
			} else {
				myModeDesc.loadSynthesizer();
			}

			this.loadedSynthesizers.add(this.synthesizer);
			this.synthesizerList.setSelectedItem(myModeDesc);
		}
	}

	public void setVoice(int index) {
		try {
			Voice voice = (Voice) this.voiceList.getElementAt(index);
			if (voice != null) {
				float oldVolume = getVolume();
				float oldSpeakingRate = getSpeakingRate();
				this.synthesizer.waitEngineState(65536L);
				this.synthesizer.getSynthesizerProperties().setVoice(voice);
				setVolume(oldVolume);
				setSpeakingRate(oldSpeakingRate);
				this.voiceList.setSelectedItem(voice);
			}
		} catch (PropertyVetoException pve) {
			pve.printStackTrace();
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}

	public float getVolume() {
		try {
			float adjustedVolume = this.synthesizer.getSynthesizerProperties()
					.getVolume();

			if (adjustedVolume < 0.5D) {
				this.volume = 0F;
			} else {
				this.volume = ((float) ((adjustedVolume - 0.5D) * 20.0D));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.volume;
	}

	public boolean setVolume(float volume) {
		try {
			float adjustedVolume = (float) (volume / 20.0F + 0.5D);
			if (this.synthesizer != null) {
				this.synthesizer.getSynthesizerProperties().setVolume(
						adjustedVolume);

				this.volume = volume;
				return true;
			}
			this.volume = volume;
			return false;
		} catch (PropertyVetoException pve) {
			try {
				this.synthesizer.getSynthesizerProperties().setVolume(
						this.volume);
			} catch (PropertyVetoException pe) {
				pe.printStackTrace();
			}
		}
		return false;
	}

	public float getSpeakingRate() {
		if (this.synthesizer != null) {
			return this.synthesizer.getSynthesizerProperties()
					.getSpeakingRate();
		}
		return -1.0F;
	}

	public boolean setSpeakingRate(float wordsPerMin) {
		float oldSpeed = getSpeakingRate();
		SynthesizerProperties properties = this.synthesizer
				.getSynthesizerProperties();
		try {
			properties.setSpeakingRate(wordsPerMin);
			return true;
		} catch (PropertyVetoException pve) {
			try {
				properties.setSpeakingRate(oldSpeed);
			} catch (PropertyVetoException pe) {
				pe.printStackTrace();
			}
		}
		return false;
	}

	public float getPitch() {
		return this.synthesizer.getSynthesizerProperties().getPitch();
	}

	public boolean setPitch(float pitch) {
		float oldPitch = getPitch();
		try {
			this.synthesizer.getSynthesizerProperties().setPitch(pitch);
			return true;
		} catch (PropertyVetoException pve) {
			try {
				this.synthesizer.getSynthesizerProperties().setPitch(oldPitch);
			} catch (PropertyVetoException pe) {
				pe.printStackTrace();
			}
		}
		return false;
	}

	public float getRange() {
		return this.synthesizer.getSynthesizerProperties().getPitchRange();
	}

	public boolean setRange(float range) {
		float oldRange = getRange();
		try {
			this.synthesizer.getSynthesizerProperties().setPitchRange(range);
			return true;
		} catch (PropertyVetoException pve) {
			try {
				this.synthesizer.getSynthesizerProperties().setPitchRange(
						oldRange);
			} catch (PropertyVetoException pe) {
				pe.printStackTrace();
			}
		}
		return false;
	}

	public void setVoiceList(SynthesizerModeDesc modeDesc) {
		Voice[] voices = modeDesc.getVoices();
		this.voiceList.removeAllElements();
		for (int i = 0; i < voices.length; i++) {
			this.voiceList.addElement(new MyVoice(voices[i].getName(),
					voices[i].getGender(), voices[i].getAge(), voices[i]
							.getStyle()));
		}
	}

	public ListModel getPlayList() {
		return this.playList;
	}

	public ListModel getVoiceList() {
		return this.voiceList;
	}

	public ListModel getSynthesizerList() {
		return this.synthesizerList;
	}

	public Object getPlayableAt(int index) {
		return null;
	}

	public void addPlayable(Playable playable) {
		this.playList.addElement(playable);
	}

	public void removePlayableAt(int index) {
		if (index < this.playList.getSize()) {
			this.playList.removeElementAt(index);
		}
	}

	public static void debugPrint(String statement) {
		if (debug) {
			System.out.println(statement);
		}
	}
}
