package jv.controller;

import jv.model.DocumentModel;
import jv.util.Constants;
import jv.util.DialogUtils;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Manages file I/O operations for the text editor.
 * Handles opening, saving, and file chooser dialogs with proper error handling.
 */
public class FileManager {

    private JFileChooser fileChooser;

    /**
     * Creates a new FileManager instance.
     */
    public FileManager() {
        this.fileChooser = new JFileChooser();
    }

    /**
     * Opens a file using a file chooser dialog and loads its content into the
     * document model.
     * 
     * @param parent The parent component for dialogs
     * @param model  The document model to update
     * @return true if a file was successfully opened, false otherwise
     */
    public boolean openFile(Component parent, DocumentModel model) {
        int result = fileChooser.showOpenDialog(parent);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                String content = readFileContent(file);
                model.setText(content, false); // Don't mark as modified when loading
                model.setCurrentFile(file);
                return true;
            } catch (IOException e) {
                DialogUtils.showError(
                        parent,
                        Constants.MSG_FILE_READ_ERROR_TITLE,
                        Constants.MSG_FILE_READ_ERROR + e.getMessage());
                return false;
            }
        }

        return false;
    }

    /**
     * Saves the document to its current file, or shows a save dialog if no file is
     * set.
     * 
     * @param parent The parent component for dialogs
     * @param model  The document model to save
     * @return true if the file was successfully saved, false otherwise
     */
    public boolean saveFile(Component parent, DocumentModel model) {
        if (model.getCurrentFile() == null) {
            return saveFileAs(parent, model);
        }

        try {
            writeFileContent(model.getCurrentFile(), model.getText());
            model.setModified(false);
            return true;
        } catch (IOException e) {
            DialogUtils.showError(
                    parent,
                    Constants.MSG_FILE_WRITE_ERROR_TITLE,
                    Constants.MSG_FILE_WRITE_ERROR + e.getMessage());
            return false;
        }
    }

    /**
     * Shows a save dialog and saves the document to the selected file.
     * 
     * @param parent The parent component for dialogs
     * @param model  The document model to save
     * @return true if the file was successfully saved, false otherwise
     */
    public boolean saveFileAs(Component parent, DocumentModel model) {
        int result = fileChooser.showSaveDialog(parent);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                writeFileContent(file, model.getText());
                model.setCurrentFile(file);
                model.setModified(false);
                return true;
            } catch (IOException e) {
                DialogUtils.showError(
                        parent,
                        Constants.MSG_FILE_WRITE_ERROR_TITLE,
                        Constants.MSG_FILE_WRITE_ERROR + e.getMessage());
                return false;
            }
        }

        return false;
    }

    /**
     * Reads the content of a file as a string using UTF-8 encoding.
     * 
     * @param file The file to read
     * @return The file content as a string
     * @throws IOException if an I/O error occurs
     */
    private String readFileContent(File file) throws IOException {
        Path path = file.toPath();
        return Files.readString(path, StandardCharsets.UTF_8);
    }

    /**
     * Writes content to a file using UTF-8 encoding.
     * 
     * @param file    The file to write to
     * @param content The content to write
     * @throws IOException if an I/O error occurs
     */
    private void writeFileContent(File file, String content) throws IOException {
        Path path = file.toPath();
        Files.writeString(path, content, StandardCharsets.UTF_8);
    }

    /**
     * Checks if the user wants to save unsaved changes before proceeding.
     * 
     * @param parent The parent component for dialogs
     * @param model  The document model to check
     * @return true if it's safe to proceed (saved, discarded, or no changes), false
     *         if cancelled
     */
    public boolean checkUnsavedChanges(Component parent, DocumentModel model) {
        if (!model.isModified()) {
            return true; // No unsaved changes
        }

        int result = DialogUtils.confirmUnsavedChanges(parent);

        if (result == JOptionPane.YES_OPTION) {
            return saveFile(parent, model);
        } else if (result == JOptionPane.NO_OPTION) {
            return true; // Discard changes
        } else {
            return false; // Cancel
        }
    }
}
