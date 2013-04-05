package speechSynthesis;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.ListModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PlayerPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PlayerModel playerModel;
	private int width = 600;
	private int height = 450;
	private int border = 30;

	private Color backgroundColor = Color.LIGHT_GRAY;
	private Color foregroundColor = Color.BLACK;
	private Color controlColor = new Color(250, 250, 250);

	private JComboBox<String> synthesizerComboBox;

	private JComboBox waveSynthesisComboBox;
	private JComboBox voiceComboBox;
	private JTextArea speakingTextArea;
	private JList speakablesList;
	private int speakablesListVisibleRows = 5;

	private JToggleButton pauseButton;

	private JButton playButton;
	private JButton cancelButton;
	private JButton stopButton;
	private JButton deleteButton;
	private int initialVolume = 10;

	private JSlider volumeSlider;
	private JSlider speedSlider;
	private JSlider pitchSlider;
	private JSlider rangeSlider;
	private JButton fileButton;
	private JTextArea textArea;
	private int textAreaRows = 2;
	private int textAreaColumns = 20;

	private JButton clearTextButton;
	private JButton speakTextButton;
	private JButton speakJSMLButton;
	private static char cancelMnemonic = 'A';
	private static char clearMnemonic = 'C';
	private static char deleteMnemonic = 'D';
	private static char pauseMnemonic = 'U';
	private static char pitchMnemonic = 'H';
	private static char playMnemonic = 'P';
	private static char playListMnemonic = 'L';
	private static char rangeMnemonic = 'R';
	private static char resumeMnemonic = 'E';
	private static char stopMnemonic = 'T';
	private static char speakMnemonic = 'S';
	private static char speakJSMLMnemonic = 'J';
	private static char synthesizerMnemonic = 'Y';
	private static char textMnemonic = 'X';
	private static char voiceMnemonic = 'O';
	private static char volumeMnemonic = 'V';
	private static char wordsPerMinMnemonic = 'W';

	public PlayerPanel(PlayerModel playerModel) {
		this.playerModel = playerModel;

		setSize(this.width, this.height);
		setAlignmentY(0.5F);
		setAlignmentX(0.5F);

		setLayout(new BorderLayout());
		add(createMainPanel(), "North");
		add(createTextPanel(), "Center");
	}

	private JPanel createMainPanel() {
		JPanel centerPanel = new JPanel();

		centerPanel.setLayout(new BorderLayout());
		centerPanel.add(createLeftSliderPanel(), "West");
		centerPanel.add(createSpeakablesPanel(), "Center");
		centerPanel.add(createRightSliderPanel(), "East");

		return centerPanel;
	}

	private JPanel createSpeakablesPanel() {
		ListModel playList = this.playerModel.getPlayList();
		this.speakablesList = new JList(playList);
		this.speakablesList.setVisibleRowCount(this.speakablesListVisibleRows);
		this.speakablesList.setSelectionMode(2);

		this.speakablesList.setSelectedIndex(0);
		this.speakablesList.setDragEnabled(true);

		JLabel listTitle = new JLabel("Play List");
		listTitle.setDisplayedMnemonic(playListMnemonic);
		listTitle.setLabelFor(this.speakablesList);

		JScrollPane scrollPane = new JScrollPane(this.speakablesList);
		scrollPane.add(listTitle);

		JPanel centerPanel = new JPanel(new BorderLayout());
		centerPanel.add(listTitle, "North");
		centerPanel.add(scrollPane, "Center");
		centerPanel.add(createControlsPanel(), "South");

		TitledBorder titledBorder = new TitledBorder("");
		titledBorder.setTitleColor(this.foregroundColor);
		titledBorder.setTitleJustification(2);
		titledBorder.setBorder(new EtchedBorder(Color.WHITE, Color.BLACK));
		centerPanel.setBorder(titledBorder);

		JPanel speakablesPanel = new JPanel(new BorderLayout());
		speakablesPanel.add(createSettingsPanel(), "North");
		speakablesPanel.add(centerPanel, "Center");

		return speakablesPanel;
	}

	private JPanel createSettingsPanel() {
		this.synthesizerComboBox = createComboBox(
				(ComboBoxModel) this.playerModel.getSynthesizerList(),
				"Synthesizer", "FreeTTS Synthesizer");

		this.voiceComboBox = createComboBox(
				(ComboBoxModel) this.playerModel.getVoiceList(), "Voice",
				"Voice");

		JLabel synthesizerLabel = new JLabel("Synthesizer:");
		synthesizerLabel.setDisplayedMnemonic(synthesizerMnemonic);
		synthesizerLabel.setLabelFor(this.synthesizerComboBox);

		JLabel voiceLabel = new JLabel("Voice:");
		voiceLabel.setDisplayedMnemonic(voiceMnemonic);
		voiceLabel.setLabelFor(this.voiceComboBox);

		JPanel leftPanel = new JPanel(new BorderLayout());
		leftPanel.add(synthesizerLabel, "North");
		leftPanel.add(this.synthesizerComboBox, "Center");

		JPanel rightPanel = new JPanel(new BorderLayout());
		rightPanel.add(voiceLabel, "North");
		rightPanel.add(this.voiceComboBox, "Center");

		JPanel settingsPanel = new JPanel();

		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setAlignment(1);
		settingsPanel.setLayout(flowLayout);
		settingsPanel.add(leftPanel);
		settingsPanel.add(rightPanel);

		addComboBoxListeners();

		return settingsPanel;
	}

	public JComboBox createComboBox(ComboBoxModel model, String toolTipText,
			String prototypeDisplayValue) {
		JComboBox comboBox = new JComboBox(model);
		comboBox.setToolTipText(toolTipText);
		comboBox.setPrototypeDisplayValue(prototypeDisplayValue);
		comboBox.setEditable(false);
		return comboBox;
	}

	private void addComboBoxListeners() {
		this.synthesizerComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = PlayerPanel.this.synthesizerComboBox
						.getSelectedIndex();
				Monitor monitor = PlayerPanel.this.playerModel.getMonitor();
				if (monitor != PlayerPanel.this.playerModel
						.getMonitor(selectedIndex)) {
					if (monitor != null) {
						monitor.setVisible(false);
					}
					if (PlayerPanel.this.playerModel.isMonitorVisible()) {
						monitor = PlayerPanel.this.playerModel
								.getMonitor(selectedIndex);
						monitor.setVisible(true);
						PlayerPanel.this.add(monitor, "South");
					}
					PlayerPanel.this.playerModel.setSynthesizer(selectedIndex);
				}
			}
		});
		this.voiceComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cursor oldCursor = PlayerPanel.this.getCursor();
				PlayerPanel.this.setCursor(Cursor.getPredefinedCursor(3));
				PlayerPanel.this.playerModel
						.setVoice(PlayerPanel.this.voiceComboBox
								.getSelectedIndex());
				PlayerPanel.this.setCursor(oldCursor);
				PlayerPanel.this.updateSliders();
			}
		});
	}

	private JPanel createControlsPanel() {
		this.playButton = createJButton("Play", playMnemonic);
		this.cancelButton = createJButton("Cancel", cancelMnemonic);
		this.stopButton = createJButton("Stop", stopMnemonic);

		this.pauseButton = new JToggleButton("Pause");
		this.pauseButton.setToolTipText("Pause");
		this.pauseButton.setMnemonic(pauseMnemonic);
		setControlColors(this.pauseButton);

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.add(this.pauseButton);
		buttonsPanel.add(this.playButton);
		buttonsPanel.add(this.cancelButton);
		buttonsPanel.add(this.stopButton);

		JPanel controlsPanel = new JPanel(new BorderLayout());
		controlsPanel.add(buttonsPanel, "Center");

		addControlsPanelActionListeners();

		return controlsPanel;
	}

	private JButton createJButton(String label, int mnemonic) {
		JButton button = new JButton(label);
		button.setToolTipText(label);
		button.setMnemonic(mnemonic);
		setControlColors(button);
		return button;
	}

	private JPanel createLeftSliderPanel() {
		this.playerModel.setVolume(this.initialVolume);

		this.volumeSlider = new JSlider(1, 0, 10, this.initialVolume);
		int speakingRate = (int) this.playerModel.getSpeakingRate();
		if (speakingRate == -1) {
			speakingRate = 0;
		}
		this.speedSlider = new JSlider(1, 0, 400, 0);

		JPanel volumePanel = createSliderPanel(this.volumeSlider,
				"Volume Control", 1, 5, "Volume", volumeMnemonic);

		JPanel speedPanel = createSliderPanel(this.speedSlider,
				"Speed Control", 50, 100, "Words/min", wordsPerMinMnemonic);

		JPanel sliderPanel = new JPanel(new FlowLayout());
		sliderPanel.add(volumePanel);
		sliderPanel.add(speedPanel);
		addLeftSliderPanelListeners();

		return sliderPanel;
	}

	private JPanel createRightSliderPanel() {
		this.pitchSlider = new JSlider(1, 50, 200, 50);
		this.rangeSlider = new JSlider(1, 0, 50, 0);

		JPanel pitchPanel = createSliderPanel(this.pitchSlider,
				"Pitch Control", 25, 50, "Pitch/Hz", pitchMnemonic);

		JPanel rangePanel = createSliderPanel(this.rangeSlider,
				"Range Control", 5, 10, "Range", rangeMnemonic);

		JPanel sliderPanel = new JPanel();
		sliderPanel.setLayout(new FlowLayout());
		sliderPanel.add(pitchPanel);
		sliderPanel.add(rangePanel);

		addRightSliderPanelListeners();

		return sliderPanel;
	}

	private void addLeftSliderPanelListeners() {
		this.volumeSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent ce) {
				if (!PlayerPanel.this.playerModel
						.setVolume(PlayerPanel.this.volumeSlider.getValue())) {
					PlayerPanel.this.volumeSlider
							.setValue((int) PlayerPanel.this.playerModel
									.getVolume());
				}
			}
		});
		this.speedSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent ce) {
				if (!PlayerPanel.this.playerModel
						.setSpeakingRate(PlayerPanel.this.speedSlider
								.getValue())) {
					PlayerPanel.this.speedSlider
							.setValue((int) PlayerPanel.this.playerModel
									.getSpeakingRate());
				}
			}
		});
	}

	private void addRightSliderPanelListeners() {
		this.pitchSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!PlayerPanel.this.playerModel
						.setPitch(PlayerPanel.this.pitchSlider.getValue())) {
					PlayerPanel.this.pitchSlider
							.setValue((int) PlayerPanel.this.playerModel
									.getPitch());
				}
			}
		});
		this.rangeSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!PlayerPanel.this.playerModel
						.setRange(PlayerPanel.this.rangeSlider.getValue())) {
					PlayerPanel.this.rangeSlider
							.setValue((int) PlayerPanel.this.playerModel
									.getRange());
				}
			}
		});
	}

	private void updateSliders() {
		int volume = (int) this.playerModel.getVolume();
		if (volume > -1) {
			this.volumeSlider.setValue(volume);
		}
		int rate = (int) this.playerModel.getSpeakingRate();
		if (rate > -1) {
			this.speedSlider.setValue(rate);
		}
		int pitch = (int) this.playerModel.getPitch();
		if (pitch > -1) {
			this.pitchSlider.setValue(pitch);
		}
		int range = (int) this.playerModel.getRange();
		if (range > -1) {
			this.rangeSlider.setValue(range);
		}
	}

	private JPanel createSliderPanel(JSlider slider, String toolTipText,
			int minorTickSpacing, int majorTickSpacing, String title,
			char mnemonic) {
		JPanel sliderPanel = new JPanel(new BorderLayout());

		slider.setSize(getSize().width / 2 - this.border,
				slider.getSize().height);

		slider.putClientProperty("JSlider.isFilled", Boolean.TRUE);
		slider.setMinorTickSpacing(minorTickSpacing);
		slider.setMajorTickSpacing(majorTickSpacing);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setToolTipText(toolTipText);

		JLabel leftLabel = new JLabel(title);
		leftLabel.setForeground(this.foregroundColor);
		leftLabel.setDisplayedMnemonic(mnemonic);
		leftLabel.setLabelFor(slider);

		sliderPanel.add(leftLabel, "North");
		sliderPanel.add(slider, "Center");

		return sliderPanel;
	}

	private void addControlsPanelActionListeners() {
		this.playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] selectedIndices = PlayerPanel.this.speakablesList
						.getSelectedIndices();

				for (int i = 0; i < selectedIndices.length; i++) {
					if (selectedIndices[i] != -1) {
						PlayerPanel.this.playerModel.play(selectedIndices[i]);
					}
				}
			}
		});
		this.pauseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (PlayerPanel.this.playerModel.isPaused()) {
					PlayerPanel.this.playerModel.resume();
				} else {
					PlayerPanel.this.playerModel.pause();
				}
			}
		});
		this.cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlayerPanel.this.playerModel.cancel();
			}
		});
		this.stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlayerPanel.this.playerModel.stop();
				PlayerPanel.this.pauseButton.setEnabled(true);
			}
		});
	}

	private JPanel createTextPanel() {
		this.textArea = new JTextArea();
		this.textArea.requestFocusInWindow();
		this.textArea.setLineWrap(true);
		this.textArea.setWrapStyleWord(true);

		JScrollPane textScrollPane = new JScrollPane(this.textArea);

		this.speakTextButton = createJButton("Speak Text", speakMnemonic);
		this.speakJSMLButton = createJButton("Speak JSML", speakJSMLMnemonic);
		this.clearTextButton = createJButton("Clear", clearMnemonic);

		BorderLayout borderLayout = new BorderLayout();
		JPanel textPanel = new JPanel(borderLayout);
		textPanel.setSize(this.width - this.border * 2,
				textPanel.getSize().height);

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.add(this.speakTextButton);
		buttonsPanel.add(this.speakJSMLButton);
		buttonsPanel.add(this.clearTextButton);

		TitledBorder titledBorder = new TitledBorder("Enter text:");
		JLabel titleLabel = new JLabel("Enter text:");
		titleLabel.setDisplayedMnemonic(textMnemonic);
		titleLabel.setLabelFor(this.textArea);

		EtchedBorder border = new EtchedBorder(Color.WHITE, Color.BLACK);
		textPanel.setBorder(border);
		textPanel.add(titleLabel, "North");
		textPanel.add(textScrollPane, "Center");
		textPanel.add(buttonsPanel, "South");

		addTextPanelActionListeners();

		return textPanel;
	}

	private void addTextPanelActionListeners() {
		this.clearTextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlayerPanel.this.textArea.setText("");
			}
		});
		this.speakTextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String inputText = PlayerPanel.this.textArea.getText();
				if (inputText.length() > 0) {
					Playable textPlayable = Playable
							.createTextPlayable(inputText);

					PlayerPanel.this.playerModel.addPlayable(textPlayable);
					PlayerPanel.this.speakablesList.setSelectedValue(
							textPlayable, true);
					PlayerPanel.this.playerModel.play(textPlayable);
				}
			}
		});
		this.speakJSMLButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String inputText = PlayerPanel.this.textArea.getText();
				if (inputText.length() > 0) {
					Playable jsmlPlayable = Playable
							.createJSMLPlayable(inputText);

					PlayerPanel.this.playerModel.addPlayable(jsmlPlayable);
					PlayerPanel.this.speakablesList.setSelectedValue(
							jsmlPlayable, true);
					PlayerPanel.this.playerModel.play(jsmlPlayable);
				}
			}
		});
	}

	private void setControlColors(JComponent component) {
		component.setBackground(this.controlColor);
	}

	public JList getPlayList() {
		return this.speakablesList;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
}
