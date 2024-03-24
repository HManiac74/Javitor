package jv;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class About {
	private JPanel panel;
	private JFrame frame;
	private JLabel text;

	public About() {
		panel = new JPanel(new FlowLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
		frame = new JFrame();
		frame.setVisible(true);
		frame.setSize(300, 300);
		text = new JLabel();
	}

}
