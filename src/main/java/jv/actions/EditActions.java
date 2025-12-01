package jv.actions;

import jv.model.DocumentModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Swing Actions for edit operations (Clear, etc.).
 */
public class EditActions {

    /**
     * Action to clear the text area.
     */
    public static class ClearAction extends AbstractAction {
        private final Component parent;
        private final DocumentModel model;
        private final JTextArea textArea;

        public ClearAction(Component parent, DocumentModel model, JTextArea textArea) {
            super("Clear");
            this.parent = parent;
            this.model = model;
            this.textArea = textArea;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            textArea.setText("");
            model.setText("", true); // Mark as modified
            if (parent instanceof JFrame) {
                ((JFrame) parent).setTitle(model.getWindowTitle());
            }
        }
    }
}
