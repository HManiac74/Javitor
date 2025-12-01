package jv.actions;

import jv.Find;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Swing Actions for find/search operations.
 */
public class FindActions {

    /**
     * Action to open the find dialog.
     */
    public static class FindAction extends AbstractAction {
        private final JTextArea textArea;
        private Find findDialog;

        public FindAction(JFrame parent, JTextArea textArea) {
            super("Find");
            this.textArea = textArea;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (findDialog == null || !findDialog.isVisible()) {
                findDialog = new Find(textArea);
            } else {
                findDialog.toFront();
                findDialog.requestFocus();
            }
        }
    }
}
