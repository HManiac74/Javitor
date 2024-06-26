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
	private String contentText;

	public About() {
		panel = new JPanel(new FlowLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
		frame = new JFrame();
		frame.setVisible(true);
		frame.setSize(300, 300);
		text = new JLabel();
	}

	protected void me() {
		frame.setTitle("About the author - " + SimpleJavaTextEditor.NAME);
		contentText = 
				"<html><body><p>"
						+ "Author: hmaniac28 <br />"
						+ "Contact me at: "
						+ "<a href='mailto:" + SimpleJavaTextEditor.EMAIL + "?subject=About Javitor'>" + SimpleJavaTextEditor.EMAIL + "</a>"
						+ "</p></body></html>";
		text.setText(contentText);
		panel.add(text);
		frame.add(panel);
	}

	protected void software() {
		frame.setTitle("About the software - " + SimpleJavaTextEditor.NAME);

		contentText = 
				"<html><body><p>" +		
						"Name: " + SimpleJavaTextEditor.NAME + "<br />" +
						"Version: " + SimpleJavaTextEditor.VERSION + 
						"</p></body></html>";

		text.setText(contentText);
		panel.add(text);
		frame.add(panel);
	}

}
