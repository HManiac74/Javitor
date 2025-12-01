package jv;

import jv.util.Constants;

import javax.swing.*;
import java.awt.*;

/**
 * Dialog for displaying information about the application and author.
 * Uses JDialog instead of JFrame to prevent memory leaks.
 */
public class About extends JDialog {

	private static final long serialVersionUID = 1L;

	/**
	 * Creates an About dialog.
	 * 
	 * @param parent       The parent frame
	 * @param isAuthorInfo true to show author info, false to show software info
	 */
	private About(Frame parent, boolean isAuthorInfo) {
		super(parent, isAuthorInfo ? "About the Author - " + Constants.APP_NAME
				: "About the Software - " + Constants.APP_NAME, true);

		JPanel panel = new JPanel(new FlowLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		String contentText;
		if (isAuthorInfo) {
			contentText = "<html><body><p>" +
					"Author: " + Constants.AUTHOR_NAME + "<br />" +
					"Contact me at: " +
					"<a href='mailto:" + Constants.AUTHOR_EMAIL + "?subject=About Javitor'>" + Constants.AUTHOR_EMAIL
					+ "</a>" +
					"</p></body></html>";
		} else {
			contentText = "<html><body><p>" +
					"Name: " + Constants.APP_NAME + "<br />" +
					"Version: " + Constants.APP_VERSION +
					"</p></body></html>";
		}

		JLabel text = new JLabel(contentText);
		panel.add(text);
		add(panel);

		pack();
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	/**
	 * Shows the author information dialog.
	 * 
	 * @param parent The parent frame
	 */
	public static void showAuthorInfo(Frame parent) {
		About dialog = new About(parent, true);
		dialog.setVisible(true);
	}

	/**
	 * Shows the software information dialog.
	 * 
	 * @param parent The parent frame
	 */
	public static void showSoftwareInfo(Frame parent) {
		About dialog = new About(parent, false);
		dialog.setVisible(true);
	}
}
