package jv.components;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.io.File;

/**
 * Status bar component displaying file information.
 */
public class StatusBar extends JPanel {

    private final JLabel fileNameLabel;
    private final JLabel fileTypeLabel;
    private final JLabel lineCountLabel;

    public StatusBar() {
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 2));
        setBorder(new BevelBorder(BevelBorder.LOWERED));

        fileNameLabel = new JLabel("File: Untitled");
        fileTypeLabel = new JLabel("Type: Text");
        lineCountLabel = new JLabel("Lines: 0");

        add(fileNameLabel);
        add(new JSeparator(SwingConstants.VERTICAL));
        add(fileTypeLabel);
        add(new JSeparator(SwingConstants.VERTICAL));
        add(lineCountLabel);
    }

    /**
     * Updates the file name display.
     * 
     * @param name The file name to display
     * @param isDirty Whether the file has unsaved changes
     */
    public void setFileName(String name, boolean isDirty) {
        String prefix = isDirty ? "*" : "";
        fileNameLabel.setText("File: " + prefix + name);
    }

    /**
     * Updates the file type display.
     * 
     * @param type The file type/extension
     */
    public void setFileType(String type) {
        fileTypeLabel.setText("Type: " + type);
    }

    /**
     * Updates the line count display.
     * 
     * @param count The number of lines
     */
    public void setLineCount(int count) {
        lineCountLabel.setText("Lines: " + count);
    }

    /**
     * Updates all status information at once.
     * 
     * @param file The current file (or null for untitled)
     * @param isDirty Whether the file has unsaved changes
     * @param lineCount The number of lines in the document
     */
    public void updateStatus(File file, boolean isDirty, int lineCount) {
        if (file == null) {
            setFileName("Untitled", isDirty);
            setFileType("Text");
        } else {
            setFileName(file.getName(), isDirty);
            String extension = getFileExtension(file);
            setFileType(extension.isEmpty() ? "Text" : extension);
        }
        setLineCount(lineCount);
    }

    /**
     * Extracts the file extension from a file.
     * 
     * @param file The file
     * @return The extension (without dot) or empty string
     */
    private String getFileExtension(File file) {
        String name = file.getName();
        int lastDot = name.lastIndexOf('.');
        if (lastDot > 0 && lastDot < name.length() - 1) {
            return name.substring(lastDot + 1);
        }
        return "";
    }
}
