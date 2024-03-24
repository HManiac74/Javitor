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
		
		menuFile = new JMenu("File");
		menuFind = new JMenu("Find");
		menuAbout = new JMenu("About");
		
		openFile = new JMenuItem("Open");
		saveFile = new JMenuItem("Save");
		close = new JMenuItem("Close");
		find = new JMenuItem("Find");
		aboutMe = new JMenuItem("About the author");
		aboutApp = new JMenuItem("About the software");
		
		menuFile.add(openFile);
		menuFile.add(saveFile);
		menuFile.add(close);
		menuFind.add(find);
		menuAbout.add(aboutMe);
		menuAbout.add(aboutApp);
		
		menuBar = new JMenuBar();
		menuBar.add(menuFile);
		menuBar.add(menuFind);
		menuBar.add(menuAbout);
		
		container.add(menuBar, BorderLayout.NORTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
