package speechSynthesis;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.PrintStream;
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.speech.synthesis.Voice;
import javax.swing.JFrame;
import javax.swing.ListModel;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Player extends JFrame {
	private PlayerModel playerModel;
	private PlayerPanel playerPanel;
	private PlayerMenuBar playerMenuBar;
	private Font globalFont;
	private String globalFontFace = "Arial";

	public Player(String title) {
		super(title);

		setDefaultLookAndFeelDecorated(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Player.this.playerModel.close();
				System.exit(0);
			}

		});
		this.playerModel = new PlayerModelImpl();
		this.playerPanel = new PlayerPanel(this.playerModel);
		setSize(this.playerPanel.getSize());
		getContentPane().add(this.playerPanel, "Center");
	}

	public PlayerPanel getView() {
		return this.playerPanel;
	}

	public PlayerModel getModel() {
		return this.playerModel;
	}

	public void setMenuBar(PlayerMenuBar menubar) {
		this.playerMenuBar = menubar;
		setJMenuBar(this.playerMenuBar);
	}

	public void showMonitor(boolean show) {
		Monitor monitor = this.playerModel.getMonitor();
		int newHeight = getSize().height;
		int monitorHeight = monitor.getSize().height;

		if (show) {
			this.playerPanel.add(monitor, "South");
			newHeight += monitorHeight;
		} else {
			this.playerPanel.remove(monitor);
			newHeight -= monitorHeight;
		}

		Dimension newSize = new Dimension(getSize().width, newHeight);
		this.playerPanel.setSize(newSize);
		setSize(newSize);

		this.playerModel.setMonitorVisible(show);
		repaint();
	}

	public void setGlobalFontSize(int fontSize) {
		if (this.globalFont == null) {
			this.globalFont = getFont();
		}
		this.globalFont = new Font(this.globalFontFace, 1, fontSize);

		UIManager.put("Button.font", this.globalFont);
		UIManager.put("ComboBox.font", this.globalFont);
		UIManager.put("Label.font", this.globalFont);
		UIManager.put("List.font", this.globalFont);
		UIManager.put("Menu.font", this.globalFont);
		UIManager.put("MenuItem.font", this.globalFont);
		UIManager.put("TextArea.font", this.globalFont);
		UIManager.put("ToggleButton.font", this.globalFont);
		UIManager.put("ToolTip.font", this.globalFont);

		setFont(this.globalFont);

		SwingUtilities.updateComponentTreeUI(this);
		repaint();
	}

	public void setLookAndFeel(Object lookAndFeel) {
		try {
			if ((lookAndFeel instanceof String)) {
				UIManager.setLookAndFeel((String) lookAndFeel);
			} else if ((lookAndFeel instanceof LookAndFeel)) {
				UIManager.setLookAndFeel((LookAndFeel) lookAndFeel);
			}

			SwingUtilities.updateComponentTreeUI(this);
			repaint();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void setVoice(String voiceName) {
		PlayerModel model = getModel();
		ListModel descList = model.getSynthesizerList();
		for (int i = 0; i < descList.getSize(); i++) {
			SynthesizerModeDesc desc = (SynthesizerModeDesc) descList
					.getElementAt(i);

			Voice[] voices = desc.getVoices();
			for (int j = 0; j < voices.length; j++) {
				if (voices[j].getName().equals(voiceName)) {
					model.setSynthesizer(i);
					model.setVoice(j);
					break;
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		boolean showMonitor = false;
		String firstVoice = "kevin16";

		Player player = new Player("FreeTTS Player");

		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-voice")) {
				i++;
				if (i < args.length) {
					firstVoice = args[i];
				}
			} else if (args[i].equals("-fontsize")) {
				i++;
				if (i < args.length) {
					player.setGlobalFontSize(Integer.parseInt(args[i]));
				}
			} else if (args[i].equals("-monitor")) {
				showMonitor = true;
			} else if (args[i].equals("-input")) {
				PlayerModel model = player.getModel();

				for (i++; i < args.length; i++) {
					int start = args[i].indexOf(':');
					if (start > -1) {
						String content = args[i].substring(start + 1);
						System.out.println(content);
						if (args[i].startsWith("plaintext:")) {
							model.addPlayable(Playable
									.createTextPlayable(content));
						} else if (args[i].startsWith("textfile:")) {
							model.addPlayable(Playable
									.createTextFilePlayable(new File(content)));
						} else if (args[i].startsWith("jsmltext:")) {
							model.addPlayable(Playable
									.createJSMLPlayable(content));
						} else if (args[i].startsWith("jsmlfile:")) {
							model.addPlayable(Playable
									.createJSMLFilePlayable(new File(content)));
						}
					}
				}
			}
		}

		player.setMenuBar(new PlayerMenuBar(player));
		player.getModel().createSynthesizers();
		player.setVoice(firstVoice);


		if (showMonitor) {
			player.showMonitor(showMonitor);
		}
	}
}
