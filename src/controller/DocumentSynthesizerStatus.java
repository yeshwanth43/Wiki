package controller;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;

import speechSynthesis.Player;
import speechSynthesis.SynthesisV1;
import documentParsing.ReadJson;

public class DocumentSynthesizerStatus {

	public JFrame frameDSS;
	private ArrayList<String> arrayList = new ArrayList<String>();
	private Player player = new Player();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DocumentSynthesizerStatus window = new DocumentSynthesizerStatus();
					window.frameDSS.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DocumentSynthesizerStatus() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameDSS = new JFrame();

		frameDSS.getContentPane().setBackground(Color.WHITE);
		frameDSS.setBackground(Color.WHITE);
		frameDSS.getContentPane().setSize(new Dimension(920, 460));
		frameDSS.getContentPane().setComponentOrientation(
				ComponentOrientation.LEFT_TO_RIGHT);
		frameDSS.getContentPane().setLayout(null);

		final JTextPane textPane = new JTextPane();
		textPane.setToolTipText("Document Content");
		textPane.setSize(new Dimension(704, 375));
		textPane.setBounds(190, 11, 704, 375);
		frameDSS.getContentPane().add(textPane);
		
		final JScrollPane scrollPane = new JScrollPane(textPane);
		scrollPane.setViewportBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 11));
		scrollPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		scrollPane.setAutoscrolls(true);
		scrollPane.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		scrollPane.setBounds(190, 11, 704, 375);
		frameDSS.getContentPane().add(scrollPane);

		frameDSS.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				ReadJson rj = new ReadJson();
				arrayList = rj.readJsonFile();
				String setText = "";
				for (String s : arrayList) {
					setText += s + "\n";
				}
				textPane.setText(setText);
			}
		});


		JButton btnNewButton = new JButton("Synthesize");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				float pitch = 0f, pitchRange = 0f, wrpm = 0f, durationStretch = 0f;
				try {
					pitch = Float.parseFloat(textField.getText());
					pitchRange = Float.parseFloat(textField_1.getText());
					durationStretch = Float.parseFloat(textField_2.getText());
					wrpm = Float.parseFloat(textField_3.getText());
					SynthesisV1 sv1 = new SynthesisV1();
					sv1.speak(arrayList.toString(), pitch, pitchRange,
							durationStretch, wrpm);
					player.start();
				} catch (NumberFormatException e) {
					e.printStackTrace();
					JOptionPane
							.showMessageDialog(frameDSS,
									"Give Numerical Data for Attributes",
									"Enter numerical Data",
									JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(250, 388, 120, 23);
		frameDSS.getContentPane().add(btnNewButton);

		JButton btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				player.stop();
			}
		});
		btnStop.setBounds(423, 388, 120, 23);
		frameDSS.getContentPane().add(btnStop);

		JButton btnPause = new JButton("Pause");
		btnPause.setBounds(774, 388, 120, 23);
		frameDSS.getContentPane().add(btnPause);

		JButton btnResume = new JButton("Resume");
		btnResume.setBounds(601, 388, 120, 23);
		frameDSS.getContentPane().add(btnResume);

		JLabel lblSynthesizerAttributes = new JLabel("Synthesizer Attributes");
		lblSynthesizerAttributes.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSynthesizerAttributes.setBounds(30, 11, 150, 23);
		frameDSS.getContentPane().add(lblSynthesizerAttributes);

		JLabel lblPitch = new JLabel("Pitch:");
		lblPitch.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPitch.setBounds(10, 45, 104, 14);
		frameDSS.getContentPane().add(lblPitch);

		textField = new JTextField();
		textField.setText("100");
		textField.setEditable(false);
		textField.setName("pitch");
		textField.setToolTipText("set pitch");
		textField.setBounds(10, 70, 104, 20);
		frameDSS.getContentPane().add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setText("11");
		textField_1.setEditable(false);
		textField_1.setToolTipText("set Pitch Range");
		textField_1.setName("pitchRange");
		textField_1.setColumns(10);
		textField_1.setBounds(10, 130, 104, 20);
		frameDSS.getContentPane().add(textField_1);

		JLabel lblRange = new JLabel("Pitch Range:");
		lblRange.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRange.setBounds(10, 105, 104, 14);
		frameDSS.getContentPane().add(lblRange);

		textField_2 = new JTextField();
		textField_2.setText("1");
		textField_2.setEditable(false);
		textField_2.setName("durationStretch");
		textField_2.setToolTipText("set Duration Stretch");
		textField_2.setColumns(10);
		textField_2.setBounds(10, 186, 104, 20);
		frameDSS.getContentPane().add(textField_2);

		JLabel lblIntensity = new JLabel("Duration Stretch:");
		lblIntensity.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblIntensity.setBounds(10, 161, 104, 14);
		frameDSS.getContentPane().add(lblIntensity);

		textField_3 = new JTextField();
		textField_3.setText("150");
		textField_3.setEditable(false);
		textField_3.setToolTipText("set Words per Minute");
		textField_3.setName("rate");
		textField_3.setColumns(10);
		textField_3.setBounds(10, 251, 104, 20);
		frameDSS.getContentPane().add(textField_3);

		JLabel lblWordsminute = new JLabel("Words/Min :");
		lblWordsminute.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblWordsminute.setBounds(10, 226, 104, 14);
		frameDSS.getContentPane().add(lblWordsminute);

		JRadioButton rdbtnSetDefault = new JRadioButton("Set Default");
		rdbtnSetDefault.setBackground(Color.WHITE);
		rdbtnSetDefault.setEnabled(true);
		rdbtnSetDefault.setBounds(10, 278, 109, 23);
		frameDSS.getContentPane().add(rdbtnSetDefault);

		JRadioButton rdbtnSetAttributes = new JRadioButton("Set Attributes");
		rdbtnSetAttributes.setBackground(Color.WHITE);
		rdbtnSetAttributes.setBounds(10, 314, 109, 23);
		frameDSS.getContentPane().add(rdbtnSetAttributes);
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnSetAttributes);
		bg.add(rdbtnSetDefault);
		rdbtnSetDefault.setSelected(true);
		ActionListener sliceActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				AbstractButton aButton = (AbstractButton) actionEvent
						.getSource();
				String text = aButton.getText();
				if (text.equals("Set Attributes")) {
					textField.setEditable(true);
					textField_1.setEditable(true);
					textField_2.setEditable(true);
					textField_3.setEditable(true);
				} else {
					textField.setEditable(false);
					textField_1.setEditable(false);
					textField_2.setEditable(false);
					textField_3.setEditable(false);
					textField.setText("100");
					textField_1.setText("11");
					textField_2.setText("1");
					textField_3.setText("150");
				}
			}
		};
		rdbtnSetAttributes.addActionListener(sliceActionListener);
		rdbtnSetDefault.addActionListener(sliceActionListener);
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DocumentSynthesizerStatus.this.frameDSS.dispose();
				WikiSynthesizer ws = new WikiSynthesizer();
				ws.frmWikiSynthesizer.setVisible(true);
			}
		});
		btnBack.setBounds(10, 344, 104, 23);
		frameDSS.getContentPane().add(btnBack);

		JButton btnWiki = new JButton("Wiki");
		btnWiki.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DocumentSynthesizerStatus.this.frameDSS.dispose();
				Wiki wi = new Wiki();
				wi.frmWiki.setVisible(true);
			}
		});
		btnWiki.setBounds(10, 388, 104, 23);
		frameDSS.getContentPane().add(btnWiki);

		frameDSS.setSize(new Dimension(920, 460));
		frameDSS.setLocation(new Point(300, 200));
		frameDSS.setTitle("Synthesizer");
		frameDSS.setFont(new Font("Arial", Font.PLAIN, 12));
		frameDSS.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameDSS.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	}
}