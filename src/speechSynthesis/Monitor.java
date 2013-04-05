package speechSynthesis;

import com.sun.speech.engine.EngineEventPanel;
import com.sun.speech.engine.synthesis.SynthesizerMonitor;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.speech.synthesis.Synthesizer;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class Monitor extends JPanel {
	private SynthesizerMonitor monitor;
	private Synthesizer synthesizer;
	private int width = 600;
	private int height = 300;

	public Monitor(Synthesizer synthesizer, String synthesizerName) {
		TitledBorder titledBorder = new TitledBorder(synthesizerName);
		setBorder(titledBorder);
		setPreferredSize(new Dimension(this.width, this.height));
		setSize(this.width, this.height);
		this.synthesizer = synthesizer;
		this.monitor = new SynthesizerMonitor(synthesizer);
		createMonitorPanel();
	}

	private void createMonitorPanel() {
		GridBagLayout gridbag = new GridBagLayout();
		setLayout(gridbag);

		GridBagConstraints c = new GridBagConstraints();
		c.anchor = 17;
		c.insets = new Insets(4, 4, 4, 4);
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1D;
		c.fill = 1;

		Component sp = this.monitor.getStatePanel();
		gridbag.setConstraints(sp, c);
		add(sp);

		EngineEventPanel eventPanel = (EngineEventPanel) this.monitor
				.getEventPanel();

		c.gridy = 1;
		c.weighty = 1D;
		gridbag.setConstraints(eventPanel, c);
		add(eventPanel);
		validate();
	}
}
