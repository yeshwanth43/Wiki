package speechSynthesis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class PlayerMenuBar extends JMenuBar {
	private Player player;
	private PlayerModel playerModel;
	private JFileChooser fileChooser;
	private JMenuItem fileSpeakJSMLMenuItem;
	private JMenuItem fileSpeakTextMenuItem;
	private JMenuItem fileSpeakURLMenuItem;
	private JMenuItem fileExitMenuItem;
	private JMenuItem styleLFCrossPlatformMenuItem;
	private JMenuItem styleLFSystemMenuItem;
	private JMenuItem styleFontSizeLargerMenuItem;
	private JMenuItem styleFontSizeSmallerMenuItem;
	private JMenuItem monitorHideMenuItem;
	private JMenuItem monitorShowMenuItem;
	private static char crossPlatformMnemonic = 'C';
	private static char exitMnemonic = 'X';
	private static char fileMnemonic = 'F';
	private static char hideMonitorMnemonic = 'H';
	private static char jsmlMnemonic = 'J';
	private static char monitorMnemonic = 'M';
	private static char showMonitorMnemonic = 'S';
	private static char speakMnemonic = 'S';
	private static char styleMnemonic = 'E';
	private static char styleLFMnemonic = 'L';
	private static char systemMnemonic = 'S';
	private static char textMnemonic = 'T';
	private static char urlMnemonic = 'U';

	public PlayerMenuBar(Player player) {
		this.player = player;
		this.playerModel = player.getModel();
		this.fileChooser = new JFileChooser();
		this.fileChooser.setCurrentDirectory(new File(System
				.getProperty("user.dir")));

		add(createFileMenu());
		add(createStyleMenu());
		add(createMonitorMenu());
	}

	private JMenu createFileMenu() {
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(fileMnemonic);
		add(fileMenu);

		JMenu fileSpeakMenu = new JMenu("Speak");
		fileSpeakMenu.setMnemonic(speakMnemonic);

		this.fileSpeakJSMLMenuItem = new JMenuItem("JSML File ...");
		this.fileSpeakTextMenuItem = new JMenuItem("Text File ...");
		this.fileSpeakURLMenuItem = new JMenuItem("URL ...");
		this.fileSpeakJSMLMenuItem.setMnemonic(jsmlMnemonic);
		this.fileSpeakTextMenuItem.setMnemonic(textMnemonic);
		this.fileSpeakURLMenuItem.setMnemonic(urlMnemonic);

		fileSpeakMenu.add(this.fileSpeakJSMLMenuItem);
		fileSpeakMenu.add(this.fileSpeakTextMenuItem);
		fileSpeakMenu.add(this.fileSpeakURLMenuItem);

		this.fileExitMenuItem = new JMenuItem("Exit");
		this.fileExitMenuItem.setMnemonic(exitMnemonic);

		fileMenu.add(fileSpeakMenu);
		fileMenu.addSeparator();
		fileMenu.add(this.fileExitMenuItem);

		addFileMenuListeners();

		return fileMenu;
	}

	private void playPlayable(Playable playable) {
		this.playerModel.addPlayable(playable);
		this.player.getView().getPlayList().setSelectedValue(playable, true);
		this.playerModel.play(playable);
	}

	private void addFileMenuListeners() {
		this.fileExitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlayerMenuBar.this.playerModel.close();
				System.exit(0);
			}
		});
		this.fileSpeakJSMLMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File file = PlayerMenuBar.this.chooseFile();
				if (file != null) {
					PlayerMenuBar.this.playPlayable(Playable
							.createJSMLFilePlayable(file));
				}
			}
		});
		this.fileSpeakTextMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File file = PlayerMenuBar.this.chooseFile();
				if (file != null) {
					PlayerMenuBar.this.playPlayable(Playable
							.createTextFilePlayable(file));
				}
			}
		});
		this.fileSpeakURLMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String url = JOptionPane.showInputDialog(
						PlayerMenuBar.this.getParent(), "Enter URL:",
						"Speak URL", 3);

				if ((url != null) && (url.length() > 0)) {
					PlayerMenuBar.this.playPlayable(Playable
							.createURLPlayable(url));
				}
			}
		});
	}

	private JMenu createStyleMenu() {
		JMenu styleMenu = new JMenu("Style");
		styleMenu.setMnemonic(styleMnemonic);

		JMenu styleLFMenu = new JMenu("Look & Feel");
		styleLFMenu.setMnemonic(styleLFMnemonic);

		this.styleLFCrossPlatformMenuItem = new JMenuItem("Cross Platform");
		this.styleLFCrossPlatformMenuItem.setMnemonic(crossPlatformMnemonic);

		this.styleLFSystemMenuItem = new JMenuItem("System");
		this.styleLFSystemMenuItem.setMnemonic(systemMnemonic);

		styleLFMenu.add(this.styleLFCrossPlatformMenuItem);
		styleLFMenu.add(this.styleLFSystemMenuItem);

		styleMenu.add(styleLFMenu);

		addStyleMenuListeners();

		return styleMenu;
	}

	private void addStyleMenuListeners() {
		this.styleLFCrossPlatformMenuItem
				.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						PlayerMenuBar.this.player.setLookAndFeel(UIManager
								.getCrossPlatformLookAndFeelClassName());
					}

				});
		this.styleLFSystemMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlayerMenuBar.this.player.setLookAndFeel(UIManager
						.getSystemLookAndFeelClassName());
			}
		});
	}

	private JMenu createMonitorMenu() {
		JMenu monitorMenu = new JMenu("Monitor");
		monitorMenu.setMnemonic(monitorMnemonic);

		this.monitorShowMenuItem = new JMenuItem("Show");
		this.monitorShowMenuItem.setMnemonic(showMonitorMnemonic);
		this.monitorShowMenuItem.setEnabled(!this.playerModel
				.isMonitorVisible());

		this.monitorHideMenuItem = new JMenuItem("Hide");
		this.monitorHideMenuItem.setMnemonic(hideMonitorMnemonic);
		this.monitorHideMenuItem
				.setEnabled(this.playerModel.isMonitorVisible());

		monitorMenu.add(this.monitorShowMenuItem);
		monitorMenu.add(this.monitorHideMenuItem);

		addMonitorMenuListeners();

		return monitorMenu;
	}

	private void addMonitorMenuListeners() {
		this.monitorShowMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlayerMenuBar.this.monitorShowMenuItem.setEnabled(false);
				PlayerMenuBar.this.monitorHideMenuItem.setEnabled(true);

				PlayerMenuBar.this.player.showMonitor(true);
			}
		});
		this.monitorHideMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlayerMenuBar.this.monitorShowMenuItem.setEnabled(true);
				PlayerMenuBar.this.monitorHideMenuItem.setEnabled(false);

				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						PlayerMenuBar.this.player.showMonitor(false);
					}
				});
			}
		});
	}

	private JFrame getFrame() {
		return this.player;
	}

	private File chooseFile() {
		int option = this.fileChooser.showOpenDialog(getParent());
		if (option == 0) {
			return this.fileChooser.getSelectedFile();
		}
		return null;
	}
}
