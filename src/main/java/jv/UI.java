package jv;

import jv.actions.EditActions;
import jv.actions.FileActions;
import jv.actions.FindActions;
import jv.controller.FileManager;
import jv.model.DocumentModel;
import jv.util.Constants;
import jv.util.ResourceManager;
import jv.components.LineNumberComponent;
import jv.components.StatusBar;
import jv.components.UIMenuBar;
import jv.components.UIToolBar;

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
    private final StatusBar statusBar;

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
    
    // State flags
    private boolean isLoading = false;

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
        statusBar = new StatusBar();

        // Setup the UI
        initializeFrame();
        initializeActions();
        setupUndoManager();
        setupUndoManager();
        
        setJMenuBar(new UIMenuBar(this, newFileAction, openFileAction, saveFileAction, closeAction, undoAction, redoAction, clearAction, findAction));
        add(new UIToolBar(this, newFileAction, openFileAction, saveFileAction, closeAction, undoAction, redoAction, clearAction, findAction), BorderLayout.NORTH);
        add(statusBar, BorderLayout.SOUTH);
        
        setupTextArea();
        setupListeners();
        updateStatusBar();
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
     * Sets up the text area with scroll pane.
     */
    private void setupTextArea() {
        // CRITICAL FIX: Add scroll pane!
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        // Add line numbers
        LineNumberComponent lineNumberComponent = new LineNumberComponent(textArea);
        scrollPane.setRowHeaderView(lineNumberComponent);
        
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
                if (!isLoading) updateDocumentState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (!isLoading) updateDocumentState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (!isLoading) updateDocumentState();
            }

            private void updateDocumentState() {
                documentModel.setText(textArea.getText(), true);
                setTitle(documentModel.getWindowTitle());
                updateStatusBar();
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

    /**
     * Loads content into the text area without triggering the modification listener.
     * 
     * @param text The text to load
     */
    public void loadContent(String text) {
        isLoading = true;
        try {
            textArea.setText(text);
            textArea.setCaretPosition(0);
        } finally {
            isLoading = false;
        }
    }

    /**
     * Updates the status bar with current file and document information.
     */
    public void updateStatusBar() {
        int lineCount = textArea.getLineCount();
        statusBar.updateStatus(documentModel.getCurrentFile(), documentModel.isModified(), lineCount);
    }
}
