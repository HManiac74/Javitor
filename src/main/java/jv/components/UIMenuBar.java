package jv.components;

import jv.About;
import jv.util.Constants;
import jv.util.ResourceManager;

import javax.swing.*;
import java.awt.event.InputEvent;

public class UIMenuBar extends JMenuBar {

    public UIMenuBar(JFrame parent,
                     Action newFileAction,
                     Action openFileAction,
                     Action saveFileAction,
                     Action closeAction,
                     Action undoAction,
                     Action redoAction,
                     Action clearAction,
                     Action findAction) {

        // File menu
        JMenu menuFile = new JMenu(Constants.MENU_FILE);
        menuFile.add(createMenuItem(Constants.MENU_ITEM_NEW, newFileAction, Constants.ICON_NEW, java.awt.event.KeyEvent.VK_N));
        menuFile.add(createMenuItem(Constants.MENU_ITEM_OPEN, openFileAction, Constants.ICON_OPEN, java.awt.event.KeyEvent.VK_O));
        menuFile.add(createMenuItem(Constants.MENU_ITEM_SAVE, saveFileAction, Constants.ICON_SAVE, java.awt.event.KeyEvent.VK_S));
        menuFile.add(createMenuItem(Constants.MENU_ITEM_CLOSE, closeAction, Constants.ICON_CLOSE, java.awt.event.KeyEvent.VK_F4));

        // Edit menu
        JMenu menuEdit = new JMenu(Constants.MENU_EDIT);
        menuEdit.add(createMenuItem(Constants.MENU_ITEM_UNDO, undoAction, Constants.ICON_UNDO, java.awt.event.KeyEvent.VK_Z));
        menuEdit.add(createMenuItem(Constants.MENU_ITEM_REDO, redoAction, Constants.ICON_REDO, java.awt.event.KeyEvent.VK_Y));
        menuEdit.addSeparator();
        menuEdit.add(createMenuItem(Constants.MENU_ITEM_CLEAR, clearAction, Constants.ICON_CLEAR, java.awt.event.KeyEvent.VK_K));

        // Find menu
        JMenu menuFind = new JMenu(Constants.MENU_FIND);
        menuFind.add(createMenuItem(Constants.MENU_ITEM_FIND, findAction, Constants.ICON_FIND, java.awt.event.KeyEvent.VK_F));

        // About menu
        JMenu menuAbout = new JMenu(Constants.MENU_ABOUT);
        JMenuItem aboutMe = new JMenuItem(Constants.MENU_ITEM_ABOUT_AUTHOR, ResourceManager.getIcon(Constants.ICON_ABOUT_ME));
        aboutMe.addActionListener(e -> About.showAuthorInfo(parent));

        JMenuItem aboutApp = new JMenuItem(Constants.MENU_ITEM_ABOUT_SOFTWARE, ResourceManager.getIcon(Constants.ICON_ABOUT));
        aboutApp.addActionListener(e -> About.showSoftwareInfo(parent));

        menuAbout.add(aboutMe);
        menuAbout.add(aboutApp);

        add(menuFile);
        add(menuEdit);
        add(menuFind);
        add(menuAbout);
    }

    private JMenuItem createMenuItem(String text, Action action, String iconPath, int keyCode) {
        JMenuItem item = new JMenuItem(action);
        item.setText(text);
        if (iconPath != null) {
            item.setIcon(ResourceManager.getIcon(iconPath));
        }
        item.setAccelerator(KeyStroke.getKeyStroke(keyCode, InputEvent.CTRL_DOWN_MASK));
        return item;
    }
}
