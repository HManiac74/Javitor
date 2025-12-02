package jv;

import jv.actions.EditActions;
import jv.actions.FileActions;
import jv.actions.FindActions;
import jv.controller.FileManager;
import jv.model.DocumentModel;
import jv.util.Constants;
import jv.util.ResourceManager;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Main UI frame for the Javitor text editor.
 * Refactored to use MVC pattern with separated concerns.
 */
public class UI extends JFrame {

    private static final long serialVersionUID = 1L;

    // Model and Controllers
    private final DocumentModel documentModel;
    private final FileManager fileManager;

    // UI Components
    private final JTextArea textArea;
    private final JToolBar mainToolbar;

    // Actions
    private FileActions.NewFileAction newFileAction;
    private FileActions.OpenFileAction openFileAction;
    private FileActions.SaveFileAction saveFileAction;
    private FileActions.CloseAction closeAction;
    private EditActions.ClearAction clearAction;
    private EditActions.UndoAction undoAction;
    private EditActions.RedoAction redoAction;
    private FindActions.FindAction findAction;

    // Undo Manager
    private final UndoManager undoManager;

    public UI() {
        // Initialize model and controllers
        documentModel = new DocumentModel();
        fileManager = new FileManager();

        // Initialize undo manager
        undoManager = new UndoManager();

        // Initialize UI components
        textArea = new JTextArea("", 0, 0);
        textArea.setFont(
                new Font(Constants.DEFAULT_FONT_NAME, Constants.DEFAULT_FONT_STYLE, Constants.DEFAULT_FONT_SIZE));

        mainToolbar = new JToolBar();

        // Setup the UI
        initializeFrame();
        initializeActions();
        setupUndoManager();
        setupMenuBar();
        setupToolBar();
        setupTextArea();
        setupListeners();
    }

    /**
     * Initializes the main frame properties.
     */
    private void initializeFrame() {
        setSize(Constants.DEFAULT_WINDOW_WIDTH, Constants.DEFAULT_WINDOW_HEIGHT);
        setTitle(documentModel.getWindowTitle());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); // Handle closing manually
        getContentPane().setLayout(new BorderLayout());
    }

    /**
     * Initializes all action objects.
     */
    private void initializeActions() {
        newFileAction = new FileActions.NewFileAction(this, documentModel, fileManager, textArea);
        openFileAction = new FileActions.OpenFileAction(this, documentModel, fileManager, textArea);
        saveFileAction = new FileActions.SaveFileAction(this, documentModel, fileManager);
        closeAction = new FileActions.CloseAction(this, documentModel, fileManager);
        clearAction = new EditActions.ClearAction(this, documentModel, textArea);
        undoAction = new EditActions.UndoAction(undoManager);
        redoAction = new EditActions.RedoAction(undoManager);
        findAction = new FindActions.FindAction(this, textArea);
    }

    /**
     * Sets up the menu bar with all menus and menu items.
     */
    private void setupMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // File menu
        JMenu menuFile = new JMenu(Constants.MENU_FILE);
        JMenuItem newFile = createMenuItem(Constants.MENU_ITEM_NEW, newFileAction,
                ResourceManager.getIcon(Constants.ICON_NEW), KeyEvent.VK_N);
        JMenuItem openFile = createMenuItem(Constants.MENU_ITEM_OPEN, openFileAction,
                ResourceManager.getIcon(Constants.ICON_OPEN), KeyEvent.VK_O);
        JMenuItem saveFile = createMenuItem(Constants.MENU_ITEM_SAVE, saveFileAction,
                ResourceManager.getIcon(Constants.ICON_SAVE), KeyEvent.VK_S);
        JMenuItem close = createMenuItem(Constants.MENU_ITEM_CLOSE, closeAction,
                ResourceManager.getIcon(Constants.ICON_CLOSE), KeyEvent.VK_F4);

        menuFile.add(newFile);
        menuFile.add(openFile);
        menuFile.add(saveFile);
        menuFile.add(close);

        // Edit menu
        JMenu menuEdit = new JMenu(Constants.MENU_EDIT);
        JMenuItem undo = createMenuItem(Constants.MENU_ITEM_UNDO, undoAction,
                ResourceManager.getIcon(Constants.ICON_UNDO), KeyEvent.VK_Z);
        JMenuItem redo = createMenuItem(Constants.MENU_ITEM_REDO, redoAction,
                ResourceManager.getIcon(Constants.ICON_REDO), KeyEvent.VK_Y);
        JMenuItem clearFile = createMenuItem(Constants.MENU_ITEM_CLEAR, clearAction,
                ResourceManager.getIcon(Constants.ICON_CLEAR), KeyEvent.VK_K);
        menuEdit.add(undo);
        menuEdit.add(redo);
        menuEdit.addSeparator();
        menuEdit.add(clearFile);

        // Find menu
        JMenu menuFind = new JMenu(Constants.MENU_FIND);
        JMenuItem find = createMenuItem(Constants.MENU_ITEM_FIND, findAction,
                ResourceManager.getIcon(Constants.ICON_FIND), KeyEvent.VK_F);
        menuFind.add(find);

        // About menu
        JMenu menuAbout = new JMenu(Constants.MENU_ABOUT);
        JMenuItem aboutMe = new JMenuItem(Constants.MENU_ITEM_ABOUT_AUTHOR,
                ResourceManager.getIcon(Constants.ICON_ABOUT_ME));
        aboutMe.addActionListener(e -> About.showAuthorInfo(this));

        JMenuItem aboutApp = new JMenuItem(Constants.MENU_ITEM_ABOUT_SOFTWARE,
                ResourceManager.getIcon(Constants.ICON_ABOUT));
        aboutApp.addActionListener(e -> About.showSoftwareInfo(this));

        menuAbout.add(aboutMe);
        menuAbout.add(aboutApp);

        // Add menus to menu bar
        menuBar.add(menuFile);
        menuBar.add(menuEdit);
        menuBar.add(menuFind);
        menuBar.add(menuAbout);

        setJMenuBar(menuBar);
    }

    /**
     * Creates a menu item with action and keyboard shortcut.
     */
    private JMenuItem createMenuItem(String text, Action action, ImageIcon icon, int keyCode) {
        JMenuItem item = new JMenuItem(action);
        item.setText(text);
        if (icon != null) {
            item.setIcon(icon);
        }
        item.setAccelerator(KeyStroke.getKeyStroke(keyCode, InputEvent.CTRL_DOWN_MASK));
        return item;
    }

    /**
     * Sets up the toolbar with all buttons.
     */
    private void setupToolBar() {
        add(mainToolbar, BorderLayout.NORTH);

        addToolbarButton(Constants.TOOLTIP_NEW, ResourceManager.getIcon(Constants.ICON_NEW), newFileAction);
        mainToolbar.addSeparator();

        addToolbarButton(Constants.TOOLTIP_OPEN, ResourceManager.getIcon(Constants.ICON_OPEN), openFileAction);
        mainToolbar.addSeparator();

        addToolbarButton(Constants.TOOLTIP_SAVE, ResourceManager.getIcon(Constants.ICON_SAVE), saveFileAction);
        mainToolbar.addSeparator();

        addToolbarButton(Constants.TOOLTIP_UNDO, ResourceManager.getIcon(Constants.ICON_UNDO), undoAction);
        mainToolbar.addSeparator();

        addToolbarButton(Constants.TOOLTIP_REDO, ResourceManager.getIcon(Constants.ICON_REDO), redoAction);
        mainToolbar.addSeparator();

        addToolbarButton(Constants.TOOLTIP_CLEAR, ResourceManager.getIcon(Constants.ICON_CLEAR), clearAction);
        mainToolbar.addSeparator();

        addToolbarButton(Constants.TOOLTIP_FIND, ResourceManager.getIcon(Constants.ICON_FIND), findAction);
        mainToolbar.addSeparator();

        // Spacer
        mainToolbar.add(Box.createHorizontalGlue());

        addToolbarButton(Constants.TOOLTIP_ABOUT_ME, ResourceManager.getIcon(Constants.ICON_ABOUT_ME),
                e -> About.showAuthorInfo(this));
        mainToolbar.addSeparator();

        addToolbarButton(Constants.TOOLTIP_ABOUT_SOFTWARE, ResourceManager.getIcon(Constants.ICON_ABOUT),
                e -> About.showSoftwareInfo(this));
        mainToolbar.addSeparator();

        // Spacer
        mainToolbar.add(Box.createHorizontalGlue());

        addToolbarButton(Constants.TOOLTIP_CLOSE, ResourceManager.getIcon(Constants.ICON_CLOSE), closeAction);
    }

    /**
     * Adds a button to the toolbar.
     */
    private void addToolbarButton(String tooltip, ImageIcon icon, Action action) {
        JButton button = new JButton(action);
        button.setText(""); // Remove text, show only icon
        if (icon != null) {
            button.setIcon(icon);
        }
        button.setToolTipText(tooltip);
        // Prevent button from expanding
        Dimension size = button.getPreferredSize();
        button.setMaximumSize(size);
        mainToolbar.add(button);
    }

    /**
     * Adds a button to the toolbar with custom action listener.
     */
    private void addToolbarButton(String tooltip, ImageIcon icon, java.awt.event.ActionListener listener) {
        JButton button = new JButton(icon);
        button.setToolTipText(tooltip);
        button.addActionListener(listener);
        // Prevent button from expanding
        Dimension size = button.getPreferredSize();
        button.setMaximumSize(size);
        mainToolbar.add(button);
    }

    /**
     * Sets up the text area with scroll pane.
     */
    private void setupTextArea() {
        // CRITICAL FIX: Add scroll pane!
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Sets up the undo manager and attaches it to the text area's document.
     */
    private void setupUndoManager() {
        textArea.getDocument().addUndoableEditListener(undoManager);
    }

    /**
     * Sets up event listeners for document changes and window closing.
     */
    private void setupListeners() {
        // Listen for text changes to update modification state
        textArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateDocumentState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateDocumentState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateDocumentState();
            }

            private void updateDocumentState() {
                documentModel.setText(textArea.getText(), true);
                setTitle(documentModel.getWindowTitle());
            }
        });

        // Handle window closing with unsaved changes check
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeAction.actionPerformed(null);
            }
        });
    }
}
