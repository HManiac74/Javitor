package jv;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

public class UI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private Container container;
	private JTextArea textArea;
	private JMenu menuFile, menuFind, menuAbout;
	private JMenuItem openFile, saveFile, close, find, aboutMe, aboutApp;
	private JMenuBar menuBar;
	
	public UI() {
		container = getContentPane();
		
		setSize(500, 300);
		setTitle("Untitled");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		textArea = new JTextArea("", 0, 0);
		textArea.setFont(new Font("Century Gothic", Font.BOLD, 12));
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(textArea);
		
		//menus
		menuFile = new JMenu("File");
		menuFind = new JMenu("Find");
		menuAbout = new JMenu("About");
		
		//menu items
		openFile = new JMenuItem("Open");
		saveFile = new JMenuItem("Save");
		close = new JMenuItem("Close");
		find = new JMenuItem("Find");
		aboutMe = new JMenuItem("About the author");
		aboutApp = new JMenuItem("About the software");
		
		//add menu items to menus
		menuFile.add(openFile);
		menuFile.add(saveFile);
		menuFile.add(close);
		menuFind.add(find);
		menuAbout.add(aboutMe);
		menuAbout.add(aboutApp);
		
		//add menus to menu bar
		menuBar = new JMenuBar();
		menuBar.add(menuFile);
		menuBar.add(menuFind);
		menuBar.add(menuAbout);
		
		//bind action listener
		aboutMe.addActionListener(this);
		aboutApp.addActionListener(this);
		
		container.add(menuBar, BorderLayout.NORTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == aboutMe) {
			new About().me();
		}
		else if (e.getSource() == aboutApp) {
			new About().software();
		}

	}

}
