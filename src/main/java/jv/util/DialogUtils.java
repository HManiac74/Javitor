package jv.util;

import javax.swing.*;
import java.awt.*;

/**
 * Utility class for creating and displaying standardized dialogs.
 * Provides consistent error handling and user feedback throughout the
 * application.
 */
public class DialogUtils {

    /**
     * Shows an error dialog with a custom message.
     * 
     * @param parent  The parent component for the dialog
     * @param title   The dialog title
     * @param message The error message to display
     */
    public static void showError(Component parent, String title, String message) {
        JOptionPane.showMessageDialog(
                parent,
                message,
                title,
                JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Shows a warning dialog with a custom message.
     * 
     * @param parent  The parent component for the dialog
     * @param title   The dialog title
     * @param message The warning message to display
     */
    public static void showWarning(Component parent, String title, String message) {
        JOptionPane.showMessageDialog(
                parent,
                message,
                title,
                JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Shows an information dialog with a custom message.
     * 
     * @param parent  The parent component for the dialog
     * @param title   The dialog title
     * @param message The information message to display
     */
    public static void showInfo(Component parent, String title, String message) {
        JOptionPane.showMessageDialog(
                parent,
                message,
                title,
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Shows a confirmation dialog asking the user if they want to save unsaved
     * changes.
     * 
     * @param parent The parent component for the dialog
     * @return JOptionPane.YES_OPTION, JOptionPane.NO_OPTION, or
     *         JOptionPane.CANCEL_OPTION
     */
    public static int confirmUnsavedChanges(Component parent) {
        return JOptionPane.showConfirmDialog(
                parent,
                Constants.MSG_UNSAVED_CHANGES,
                Constants.MSG_UNSAVED_CHANGES_TITLE,
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Shows a yes/no confirmation dialog with a custom message.
     * 
     * @param parent  The parent component for the dialog
     * @param title   The dialog title
     * @param message The confirmation message
     * @return true if user clicked Yes, false otherwise
     */
    public static boolean confirmAction(Component parent, String title, String message) {
        int result = JOptionPane.showConfirmDialog(
                parent,
                message,
                title,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        return result == JOptionPane.YES_OPTION;
    }

    /**
     * Shows an input dialog requesting text from the user.
     * 
     * @param parent  The parent component for the dialog
     * @param title   The dialog title
     * @param message The prompt message
     * @return The user's input, or null if cancelled
     */
    public static String showInputDialog(Component parent, String title, String message) {
        return JOptionPane.showInputDialog(parent, message, title, JOptionPane.QUESTION_MESSAGE);
    }

    // Private constructor to prevent instantiation
    private DialogUtils() {
        throw new AssertionError("DialogUtils class should not be instantiated");
    }
}
