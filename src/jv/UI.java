package jv;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.Scanner;

import javax.swing.*;

public class UI extends JFrame implements ActionListener {

	@Serial
	private static final long serialVersionUID = 1L;
    private final JTextArea textArea;
    private final JMenuItem openFile, saveFile, close, find, aboutMe, aboutApp, clearFile, newFile;

    public UI() {
        Container container = getContentPane();
		
		setSize(500, 300);
		setTitle("Untitled " + Main.NAME);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		textArea = new JTextArea("", 0, 0);
		textArea.setFont(new Font("Century Gothic", Font.BOLD, 12));
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(textArea);
		
		//menus
        JMenu menuFile = new JMenu("File");
        JMenu menuFind = new JMenu("Find");
		JMenu menuEdit = new JMenu("Edit");
        JMenu menuAbout = new JMenu("About");
		
		//menu items
		openFile = new JMenuItem("Open");
		saveFile = new JMenuItem("Save");
		close = new JMenuItem("Close");
		clearFile = new JMenuItem("Clear");
		find = new JMenuItem("Find");
		aboutMe = new JMenuItem("About the author");
		aboutApp = new JMenuItem("About the software");
		newFile = new JMenuItem("New");
		
		//add menu items to menus
		menuFile.add(newFile);
		menuFile.add(openFile);
		menuFile.add(saveFile);
		menuFile.add(close);
		menuFind.add(find);
		menuAbout.add(aboutMe);
		menuAbout.add(aboutApp);
		menuEdit.add(clearFile);
		
		//add menus to menu bar
        JMenuBar menuBar = new JMenuBar();
		menuBar.add(menuFile);
		menuBar.add(menuEdit);
		menuBar.add(menuFind);
		menuBar.add(menuAbout);
		
		//bind action listener
		aboutMe.addActionListener(this);
		aboutApp.addActionListener(this);
		close.addActionListener(this);
		openFile.addActionListener(this);
		saveFile.addActionListener(this);
		find.addActionListener(this);
		clearFile.addActionListener(this);
		newFile.addActionListener(this);

		//set keyoard shortcuts
		openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
		saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
		close.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.CTRL_DOWN_MASK));
		find.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_DOWN_MASK));
		newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
		clearFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, InputEvent.CTRL_DOWN_MASK));
		
		container.add(menuBar, BorderLayout.NORTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == openFile) {
			JFileChooser open = new JFileChooser();
			int option = open.showOpenDialog(this);
			if (option == JFileChooser.APPROVE_OPTION) {
				textArea.setText(" ");
				try {
					Scanner scan = new Scanner(new FileReader(open.getSelectedFile().getPath()));
					while (scan.hasNext())
						textArea.append(scan.nextLine() + "\n");
					scan.close();
				}
				catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
			}	
		}
		else if (e.getSource() == saveFile) {
			JFileChooser save = new JFileChooser();
			int option = save.showSaveDialog(this);
			if (option == JFileChooser.APPROVE_OPTION) {
				try {
					File file = save.getSelectedFile();
					setTitle(file.getName() + " | " + Main.NAME);
					BufferedWriter out = new BufferedWriter(new FileWriter(file.getPath()));
					out.write(textArea.getText());
					out.close();
				}
				catch (Exception ex){
					System.out.println(ex.getMessage());
				}
			}
		}
		else if (e.getSource() == find) {
			new Find(textArea);
		}
		else if (e.getSource() == close) {
			this.dispose();
		}
		else if (e.getSource() == aboutMe) {
			new About().me();
		}
		else if (e.getSource() == aboutApp) {
			new About().software();
		}
		else if (e.getSource() == clearFile) {
			FEdit.clear(textArea);
		}
		else if (e.getSource() == newFile) {
			FEdit.clear(textArea);
		}

	}

}
