package jv.components;

import jv.About;
import jv.util.Constants;
import jv.util.ResourceManager;

import javax.swing.*;
import java.awt.*;

public class UIToolBar extends JToolBar {

    public UIToolBar(JFrame parent,
                     Action newFileAction,
                     Action openFileAction,
                     Action saveFileAction,
                     Action closeAction,
                     Action undoAction,
                     Action redoAction,
                     Action clearAction,
                     Action findAction) {

        addToolbarButton(Constants.TOOLTIP_NEW, Constants.ICON_NEW, newFileAction);
        addSeparator();

        addToolbarButton(Constants.TOOLTIP_OPEN, Constants.ICON_OPEN, openFileAction);
        addSeparator();

        addToolbarButton(Constants.TOOLTIP_SAVE, Constants.ICON_SAVE, saveFileAction);
        addSeparator();

        addToolbarButton(Constants.TOOLTIP_UNDO, Constants.ICON_UNDO, undoAction);
        addSeparator();

        addToolbarButton(Constants.TOOLTIP_REDO, Constants.ICON_REDO, redoAction);
        addSeparator();

        addToolbarButton(Constants.TOOLTIP_CLEAR, Constants.ICON_CLEAR, clearAction);
        addSeparator();

        addToolbarButton(Constants.TOOLTIP_FIND, Constants.ICON_FIND, findAction);
        addSeparator();

        add(Box.createHorizontalGlue());

        addToolbarButton(Constants.TOOLTIP_ABOUT_ME, Constants.ICON_ABOUT_ME, e -> About.showAuthorInfo(parent));
        addSeparator();

        addToolbarButton(Constants.TOOLTIP_ABOUT_SOFTWARE, Constants.ICON_ABOUT, e -> About.showSoftwareInfo(parent));
        addSeparator();

        add(Box.createHorizontalGlue());

        addToolbarButton(Constants.TOOLTIP_CLOSE, Constants.ICON_CLOSE, closeAction);
    }

    private void addToolbarButton(String tooltip, String iconPath, Action action) {
        JButton button = new JButton(action);
        button.setText(""); // Remove text, show only icon
        if (iconPath != null) {
            button.setIcon(ResourceManager.getIcon(iconPath));
        }
        button.setToolTipText(tooltip);
        configureButton(button);
        add(button);
    }

    private void addToolbarButton(String tooltip, String iconPath, java.awt.event.ActionListener listener) {
        JButton button = new JButton();
        if (iconPath != null) {
            button.setIcon(ResourceManager.getIcon(iconPath));
        }
        button.setToolTipText(tooltip);
        button.addActionListener(listener);
        configureButton(button);
        add(button);
    }

    private void configureButton(JButton button) {
        Dimension size = button.getPreferredSize();
        button.setMaximumSize(size);
    }
}
