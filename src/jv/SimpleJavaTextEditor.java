package jv;

import javax.swing.JTextPane;

public class SimpleJavaTextEditor extends JTextPane{

	private static final long serialVersionUID = 1L;
	protected final static String EMAIL = "hmaniac28@yahoo.de";
	protected final static String NAME = "Javitor (Java Editor)";
	protected final static float VERSION = 1.0f;

	public static void main(String[] args) {
		new UI().setVisible(true);

	}
}
