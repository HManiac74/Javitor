import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class Find extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JButton fndButton, findNext, replace, replaceAll, cancel;

    public Find (JTextArea text) {

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

    }

    private void replace() {

    }

    private void findNext() {

    }

    private void find() {

    }

} 

