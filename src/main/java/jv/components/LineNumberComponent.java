package jv.components;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;
import javax.swing.text.Utilities;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * A component that displays line numbers for a JTextComponent.
 * This class is designed to be used as a row header in a JScrollPane.
 */
public class LineNumberComponent extends JComponent
        implements DocumentListener, CaretListener, PropertyChangeListener, ComponentListener {

    private static final long serialVersionUID = 1L;

    private static final int LEFT_PADDING = 5;
    private static final int RIGHT_PADDING = 5;

    private final JTextComponent component;
    private final FontMetrics fontMetrics;
    private final int lineHeight;
    private final int charWidth;
    private final int maxDigits;
    private int lastDigits;
    private int lastLine;

    /**
     * Constructs a new LineNumberComponent for the given JTextComponent.
     *
     * @param component the text component to display line numbers for
     */
    public LineNumberComponent(JTextComponent component) {
        this.component = component;
        this.fontMetrics = component.getFontMetrics(component.getFont());
        this.lineHeight = fontMetrics.getHeight();
        this.charWidth = fontMetrics.charWidth('0');
        this.maxDigits = 3;
        this.lastDigits = 0;
        this.lastLine = -1;

        component.getDocument().addDocumentListener(this);
        component.addCaretListener(this);
        component.addPropertyChangeListener("font", this);
        component.addComponentListener(this);
        
        setBorder(new CompoundBorder(
                new MatteBorder(0, 0, 0, 1, Color.GRAY),
                new EmptyBorder(0, LEFT_PADDING, 0, RIGHT_PADDING)));
        
        setForeground(Color.GRAY);
        setBackground(Color.WHITE);
        setOpaque(true);
    }

    /**
     * Updates the preferred size of the component based on the number of lines.
     */
    private void updateSize() {
        int digits = Math.max(String.valueOf(component.getDocument().getDefaultRootElement().getElementCount()).length(), 3);
        
        if (digits != lastDigits || component.getHeight() != getHeight()) {
            lastDigits = digits;
            FontMetrics fm = getFontMetrics(getFont());
            int width = fm.charWidth('0') * digits + LEFT_PADDING + RIGHT_PADDING;
            setPreferredSize(new Dimension(width, component.getHeight()));
            revalidate();
            repaint();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Font font = component.getFont();
        g.setFont(font);
        
        FontMetrics fm = getFontMetrics(font);
        int fontHeight = fm.getHeight();
        int fontAscent = fm.getAscent();
        
        Rectangle clip = g.getClipBounds();
        int startOffset = component.viewToModel(new Point(0, clip.y));
        int endOffset = component.viewToModel(new Point(0, clip.y + clip.height));
        
        while (startOffset <= endOffset) {
            try {
                String text = component.getText(startOffset, endOffset - startOffset);
                if (text.indexOf('\n') != -1) {
                    // If we have newlines, we might need to be careful, but viewToModel usually handles this.
                    // Actually, simpler approach: iterate through lines.
                    break; 
                }
            } catch (BadLocationException e) {
                break;
            }
            startOffset++;
        }

        // Better approach: Iterate over root elements (lines)
        Element root = component.getDocument().getDefaultRootElement();
        int startLine = root.getElementIndex(startOffset);
        int endLine = root.getElementIndex(endOffset);
        
        for (int i = startLine; i <= endLine; i++) {
            Element line = root.getElement(i);
            int lineStartOffset = line.getStartOffset();
            
            try {
                Rectangle r = component.modelToView(lineStartOffset);
                if (r != null) {
                    String lineNumber = String.valueOf(i + 1);
                    int stringWidth = fm.stringWidth(lineNumber);
                    int x = getWidth() - stringWidth - RIGHT_PADDING - 2; // -2 for border
                    int y = r.y + fontAscent;
                    
                    g.drawString(lineNumber, x, y);
                }
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        updateSize();
        repaint();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        updateSize();
        repaint();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        updateSize();
        repaint();
    }

    @Override
    public void caretUpdate(CaretEvent e) {
        // Optional: Highlight current line number
        // For now, just repaint to ensure sync
        // repaint();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("font")) {
            if (evt.getNewValue() instanceof Font) {
                setFont((Font) evt.getNewValue());
                updateSize();
                repaint();
            }
        }
    }

    @Override
    public void componentResized(ComponentEvent e) {
        updateSize();
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }
}
