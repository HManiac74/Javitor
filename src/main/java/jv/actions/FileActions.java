package jv.actions;

import jv.controller.FileManager;
import jv.model.DocumentModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Swing Actions for file operations (New, Open, Save, Close).
 */
public class FileActions {

    /**
     * Action to create a new document.
     */
    public static class NewFileAction extends AbstractAction {
        private final Component parent;
        private final DocumentModel model;
        private final FileManager fileManager;
        private final JTextArea textArea;

        public NewFileAction(Component parent, DocumentModel model, FileManager fileManager, JTextArea textArea) {
            super("New");
            this.parent = parent;
            this.model = model;
            this.fileManager = fileManager;
            this.textArea = textArea;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (fileManager.checkUnsavedChanges(parent, model)) {
                model.clear();
                textArea.setText("");
                if (parent instanceof JFrame) {
                    ((JFrame) parent).setTitle(model.getWindowTitle());
                }
            }
        }
    }

    /**
     * Action to open an existing file.
     */
    public static class OpenFileAction extends AbstractAction {
        private final Component parent;
        private final DocumentModel model;
        private final FileManager fileManager;
        private final JTextArea textArea;

        public OpenFileAction(Component parent, DocumentModel model, FileManager fileManager, JTextArea textArea) {
            super("Open");
            this.parent = parent;
            this.model = model;
            this.fileManager = fileManager;
            this.textArea = textArea;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (fileManager.checkUnsavedChanges(parent, model)) {
                if (fileManager.openFile(parent, model)) {
                    textArea.setText(model.getText());
                    if (parent instanceof JFrame) {
                        ((JFrame) parent).setTitle(model.getWindowTitle());
                    }
                }
            }
        }
    }

    /**
     * Action to save the current document.
     */
    public static class SaveFileAction extends AbstractAction {
        private final Component parent;
        private final DocumentModel model;
        private final FileManager fileManager;

        public SaveFileAction(Component parent, DocumentModel model, FileManager fileManager) {
            super("Save");
            this.parent = parent;
            this.model = model;
            this.fileManager = fileManager;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (fileManager.saveFile(parent, model)) {
                if (parent instanceof JFrame) {
                    ((JFrame) parent).setTitle(model.getWindowTitle());
                }
            }
        }
    }

    /**
     * Action to close the application.
     */
    public static class CloseAction extends AbstractAction {
        private final Component parent;
        private final DocumentModel model;
        private final FileManager fileManager;

        public CloseAction(Component parent, DocumentModel model, FileManager fileManager) {
            super("Close");
            this.parent = parent;
            this.model = model;
            this.fileManager = fileManager;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (fileManager.checkUnsavedChanges(parent, model)) {
                if (parent instanceof Window) {
                    ((Window) parent).dispose();
                }
                System.exit(0);
            }
        }
    }
}
