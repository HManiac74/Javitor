package jv;

import javax.swing.SwingUtilities;

/**
 * Main entry point for the Javitor text editor application.
 * 
 * @author hmaniac28
 * @version 1.0
 */
public class SimpleJavaTextEditor {

	public static void main(String[] args) {
		// Set application name for macOS
		System.setProperty("apple.awt.application.name", "Javitor");
		
		// Run UI creation on the Event Dispatch Thread for thread safety
		SwingUtilities.invokeLater(() -> {
			UI ui = new UI();
			ui.setVisible(true);
		});
	}
}
