package jv;

import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Find extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JButton fndButton, findNext, replace, replaceAll, cancel;
    private JTextArea txt;
    JTextField textF, textR;
    JLabel lab1, lab2;
    int startIndex;

    public Find (JTextArea text) {
        this.txt = text;

        lab1 = new JLabel("Find");
        lab2 = new JLabel("Replace");
        textF = new JTextField(30);
        textR = new JTextField(30);
        fndButton = new JButton("Find");
        findNext = new JButton("Find Next");
        replace = new JButton("Replace");
        replaceAll = new JButton("Replace All");
        cancel = new JButton("Cancel");

        setLayout(null);

        int labWidth = 80;
        int labHeight = 20;

        //Add labels
        lab1.setBounds(10,10, labWidth, labHeight);
        add(lab1);
        textF.setBounds(10 + labWidth, 10, 120, 20);
        add(textF);
        lab2.setBounds(10, 20 + labHeight, labWidth, labHeight);
        add(lab2);
        textR.setBounds(10 + labWidth, 20 + labHeight, 120, 20);
        add(textR);

        //Add buttons
        fndButton.setBounds(225, 6, 115, 20);
        add(fndButton);
        fndButton.addActionListener(this);

        findNext.setBounds(225, 28, 115, 20);
        add(findNext);
        findNext.addActionListener(this);

        replace.setBounds(225, 50, 115, 20);
        add(replace);
        replace.addActionListener(this);

        replaceAll.setBounds(225, 72, 115, 20);
        add(replaceAll);
        replaceAll.addActionListener(this);

        cancel.setBounds(225, 94, 115, 20);
        add(cancel);
        cancel.addActionListener(this);


        //set window size
        int width = 360;
        int height = 160; 
        setSize(width, height);

        Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        setLocation(center.x-width/2, center.y-height/2);
        setVisible(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == fndButton) {
            find();
        }
        else if (e.getSource() == findNext) {
            findNext();
        }
        else if (e.getSource() == replace) {
            replace();
        }
        else if (e.getSource() == replaceAll) {
            replaceAll();
        }
        else if (e.getSource() == cancel) {
            this.setVisible(false);
        }
    }
     

    private void replaceAll() {
        txt.setText(txt.getText().replaceAll(textF.getText(), textR.getText()));
    }

    private void replace() {
        try {
            find();
            txt.replaceSelection(textR.getText());
        }
        catch (NullPointerException e) {
            System.out.println("Null Pointer Exception: " + e);
        }
    }

    private void findNext() {
        String selection = txt.getSelectedText();

        try {
            selection.equals("");
        }
        catch (NullPointerException e) {
            selection = textF.getText();
            try {
                selection.equals("");
            }
            catch (NullPointerException e2) {
                selection = JOptionPane.showInputDialog("Find: ");
                textF.setText(selection);
            }
        }
        try {
            int select_start = txt.getText().indexOf(selection, startIndex);
            int select_end = select_start + selection.length();
            txt.select(select_start, select_end);
            startIndex = select_end + 1;

            if (select_start == txt.getText().lastIndexOf(selection)) {
                startIndex = 0;
            }
        }
        catch (NullPointerException e) {

        }
    }

    private void find() {
        int select_start = txt.getText().indexOf(textF.getText());
        if (select_start == -1) {
            startIndex = 0;
            JOptionPane.showMessageDialog(null, "Could not find " + textF.getText());
            return;
        }
        if (select_start == txt.getText().lastIndexOf(textF.getText())) {
            startIndex = 0;
        }
        int select_end = select_start + textF.getText().length();
        txt.select(select_start, select_end);
    }

} 

