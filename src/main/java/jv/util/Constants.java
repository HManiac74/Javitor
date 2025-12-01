package jv.util;

/**
 * Application-wide constants for Javitor text editor.
 * Centralizes all configuration values and magic strings.
 */
public class Constants {

    // Application metadata
    public static final String APP_NAME = "Javitor (Java Editor)";
    public static final float APP_VERSION = 1.0f;
    public static final String AUTHOR_EMAIL = "hmaniac28@yahoo.de";
    public static final String AUTHOR_NAME = "hmaniac28";

    // UI Configuration
    public static final String DEFAULT_FONT_NAME = "Century Gothic";
    public static final int DEFAULT_FONT_STYLE = java.awt.Font.BOLD;
    public static final int DEFAULT_FONT_SIZE = 12;

    public static final int DEFAULT_WINDOW_WIDTH = 500;
    public static final int DEFAULT_WINDOW_HEIGHT = 300;

    // Window titles
    public static final String TITLE_UNTITLED = "Untitled - " + APP_NAME;
    public static final String TITLE_MODIFIED_MARKER = "*";

    // Icon paths (relative to classpath)
    public static final String ICON_NEW = "icons/new.png";
    public static final String ICON_OPEN = "icons/open.png";
    public static final String ICON_SAVE = "icons/save.png";
    public static final String ICON_CLOSE = "icons/close.png";
    public static final String ICON_CLEAR = "icons/clear.png";
    public static final String ICON_FIND = "icons/find.png";
    public static final String ICON_ABOUT_ME = "icons/about_me.png";
    public static final String ICON_ABOUT = "icons/about.png";

    // Menu labels
    public static final String MENU_FILE = "File";
    public static final String MENU_EDIT = "Edit";
    public static final String MENU_FIND = "Find";
    public static final String MENU_ABOUT = "About";

    public static final String MENU_ITEM_NEW = "New";
    public static final String MENU_ITEM_OPEN = "Open";
    public static final String MENU_ITEM_SAVE = "Save";
    public static final String MENU_ITEM_CLOSE = "Close";
    public static final String MENU_ITEM_CLEAR = "Clear";
    public static final String MENU_ITEM_FIND = "Find";
    public static final String MENU_ITEM_ABOUT_AUTHOR = "About the author";
    public static final String MENU_ITEM_ABOUT_SOFTWARE = "About the software";

    // Tooltip texts
    public static final String TOOLTIP_NEW = "New";
    public static final String TOOLTIP_OPEN = "Open";
    public static final String TOOLTIP_SAVE = "Save";
    public static final String TOOLTIP_CLEAR = "Clear All";
    public static final String TOOLTIP_FIND = "Find";
    public static final String TOOLTIP_ABOUT_ME = "About Me";
    public static final String TOOLTIP_ABOUT_SOFTWARE = "About The Software";
    public static final String TOOLTIP_CLOSE = "Close";

    // Dialog messages
    public static final String MSG_UNSAVED_CHANGES_TITLE = "Unsaved Changes";
    public static final String MSG_UNSAVED_CHANGES = "You have unsaved changes. Do you want to save before continuing?";
    public static final String MSG_FILE_NOT_FOUND_TITLE = "File Not Found";
    public static final String MSG_FILE_NOT_FOUND = "The selected file could not be found.";
    public static final String MSG_FILE_READ_ERROR_TITLE = "Error Reading File";
    public static final String MSG_FILE_READ_ERROR = "An error occurred while reading the file: ";
    public static final String MSG_FILE_WRITE_ERROR_TITLE = "Error Saving File";
    public static final String MSG_FILE_WRITE_ERROR = "An error occurred while saving the file: ";
    public static final String MSG_SEARCH_NOT_FOUND = "Could not find: ";

    // File operations
    public static final String DEFAULT_CHARSET = "UTF-8";

    // Action keys (for ActionManager)
    public static final String ACTION_NEW = "new";
    public static final String ACTION_OPEN = "open";
    public static final String ACTION_SAVE = "save";
    public static final String ACTION_CLOSE = "close";
    public static final String ACTION_CLEAR = "clear";
    public static final String ACTION_FIND = "find";
    public static final String ACTION_ABOUT_AUTHOR = "about-author";
    public static final String ACTION_ABOUT_SOFTWARE = "about-software";

    // Private constructor to prevent instantiation
    private Constants() {
        throw new AssertionError("Constants class should not be instantiated");
    }
}
