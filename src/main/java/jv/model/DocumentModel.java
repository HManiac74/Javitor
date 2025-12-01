package jv.model;

import java.io.File;

/**
 * Model class representing the document state in the text editor.
 * Manages text content, modification tracking, and file association.
 */
public class DocumentModel {

    private String text;
    private boolean modified;
    private File currentFile;

    /**
     * Creates a new empty document.
     */
    public DocumentModel() {
        this.text = "";
        this.modified = false;
        this.currentFile = null;
    }

    /**
     * Gets the current text content.
     * 
     * @return The text content
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the text content and marks the document as modified.
     * 
     * @param text The new text content
     */
    public void setText(String text) {
        this.text = text;
        this.modified = true;
    }

    /**
     * Sets the text content without marking as modified.
     * Used when loading from file.
     * 
     * @param text         The new text content
     * @param markModified Whether to mark the document as modified
     */
    public void setText(String text, boolean markModified) {
        this.text = text;
        this.modified = markModified;
    }

    /**
     * Checks if the document has unsaved changes.
     * 
     * @return true if the document has been modified since last save
     */
    public boolean isModified() {
        return modified;
    }

    /**
     * Sets the modification state.
     * 
     * @param modified The modification state
     */
    public void setModified(boolean modified) {
        this.modified = modified;
    }

    /**
     * Gets the current file associated with this document.
     * 
     * @return The current file, or null if the document hasn't been saved
     */
    public File getCurrentFile() {
        return currentFile;
    }

    /**
     * Sets the current file associated with this document.
     * 
     * @param currentFile The file to associate with this document
     */
    public void setCurrentFile(File currentFile) {
        this.currentFile = currentFile;
    }

    /**
     * Clears the document content and resets the modification state.
     */
    public void clear() {
        this.text = "";
        this.modified = false;
        this.currentFile = null;
    }

    /**
     * Gets the display name for the current document.
     * Returns the filename if a file is associated, otherwise "Untitled".
     * 
     * @return The display name
     */
    public String getDisplayName() {
        if (currentFile != null) {
            return currentFile.getName();
        }
        return "Untitled";
    }

    /**
     * Gets the full title for the window, including modification indicator.
     * 
     * @return The window title
     */
    public String getWindowTitle() {
        StringBuilder title = new StringBuilder();

        if (modified) {
            title.append("*");
        }

        title.append(getDisplayName());
        title.append(" - ");
        title.append(jv.util.Constants.APP_NAME);

        return title.toString();
    }
}
