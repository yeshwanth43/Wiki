package speechSynthesis;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Player implements Runnable {
	static final int BUF_SIZE = 16384;
	private AudioInputStream audioInputStream;
	private AudioFormat format;
	SourceDataLine line;
	private Thread thread;

	public Player() {
		// do nothing just for intialising
	}

	public Player(AudioInputStream audioInputStream) {
		this.audioInputStream = audioInputStream;
		format = audioInputStream.getFormat();
	}

	public Player(File wavFile) throws UnsupportedAudioFileException,
			IOException {
		this.audioInputStream = AudioSystem.getAudioInputStream(wavFile);
		format = audioInputStream.getFormat();
	}

	public void start() {
		try {
			this.audioInputStream = AudioSystem.getAudioInputStream(new File(
					"././assets/synthesized.wav"));
		} catch (UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		}
		format = audioInputStream.getFormat();
		thread = new Thread(this);
		thread.setName("Playback");
		thread.start();
	}

	public void stop() {
		System.out.println("************************stopping thread******************************");
		thread = null;
	}

	@SuppressWarnings("static-access")
	public void pausePlayer() {
		System.out.println("*******************************IN PAUSE ******************************");
		try {
			System.out.println(thread.getName());
			thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void giveIntroduction(){
		try {
			this.audioInputStream = AudioSystem.getAudioInputStream(new File(
					"././assets/Introduction.wav"));
		} catch (UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		}
		format = audioInputStream.getFormat();
		thread = new Thread(this);
		thread.setName("Playback");
		thread.start();
	}

	public void resumePlayer() {
		System.out.println("**************************IN RESUME************************************");
		thread.notify();
	}

	private void shutDown(final String message) {
		if (thread != null) {
			thread = null;
		}
		System.out.println(message);
	}

	@Override
	public void run() {
		if (audioInputStream == null) {
			shutDown("No loaded audio to play back");
			return;
		}
		final AudioInputStream playbackInputStream = AudioSystem
				.getAudioInputStream(format, audioInputStream);
		if (playbackInputStream == null) {
			shutDown("Unable to convert stream of format " + audioInputStream
					+ " to format " + format);
			return;
		}
		line = getSourceDataLineForPlayback();
		final int frameSizeInBytes = format.getFrameSize();
		final int bufferLengthInFrames = line.getBufferSize() / 8;
		final int bufferLengthInBytes = bufferLengthInFrames * frameSizeInBytes;
		final byte[] audioBuffer = new byte[bufferLengthInBytes];
		int numBytesRead = 0;
		line.start();
		while (thread != null) {
			try {
				if ((numBytesRead = playbackInputStream.read(audioBuffer)) == -1) {
					break;
				}
				int numBytesRemaining = numBytesRead;
				while (numBytesRemaining > 0) {
					numBytesRemaining -= line.write(audioBuffer, 0,
							numBytesRemaining);
				}
			} catch (final Exception e) {
				shutDown("Error during playback: " + e);
				break;
			}
		}
		if (thread != null) {
			line.drain();
		}
		line.stop();
		line.close();
		line = null;
		thread = null;
	}

	private SourceDataLine getSourceDataLineForPlayback() {
		SourceDataLine line;
		final DataLine.Info info = new DataLine.Info(SourceDataLine.class,
				format);
		if (!AudioSystem.isLineSupported(info)) {
			return null;
		}
		try {
			line = (SourceDataLine) AudioSystem.getLine(info);
			line.open(format, BUF_SIZE);
		} catch (final LineUnavailableException ex) {
			return null;
		}
		return line;
	}

	public static void main(String[] args) throws Exception {
		Player pl = new Player(new File("././assets/synthesize.wav"));
		pl.start();
		Thread.sleep(1000);

	}
}
