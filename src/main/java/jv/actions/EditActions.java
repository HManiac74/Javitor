package jv.actions;

import jv.model.DocumentModel;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Swing Actions for edit operations (Clear, Undo, Redo, etc.).
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

    /**
     * Action to undo the last change.
     */
    public static class UndoAction extends AbstractAction {
        private final UndoManager undoManager;

        public UndoAction(UndoManager undoManager) {
            super("Undo");
            this.undoManager = undoManager;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (undoManager.canUndo()) {
                undoManager.undo();
            }
        }
    }

    /**
     * Action to redo the last undone change.
     */
    public static class RedoAction extends AbstractAction {
        private final UndoManager undoManager;

        public RedoAction(UndoManager undoManager) {
            super("Redo");
            this.undoManager = undoManager;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (undoManager.canRedo()) {
                undoManager.redo();
            }
        }
    }
}

