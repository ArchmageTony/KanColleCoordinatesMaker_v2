package org.at.tool;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

/**
 * @author ArchmageTony
 * @version 1.0
 */
public class MyLog {
    public final static JTextPane logEdtTxt = new JTextPane();
    static SimpleAttributeSet infoSet = new SimpleAttributeSet();
    static SimpleAttributeSet errorSet = new SimpleAttributeSet();
    static SimpleAttributeSet boldSet = new SimpleAttributeSet();

    static {
        StyleConstants.setForeground(errorSet, Color.RED);
        StyleConstants.setBold(boldSet, true);
        StyleConstants.setFontSize(boldSet, 14);
    }

    public static void log(String content) {
        StyledDocument doc = logEdtTxt.getStyledDocument();
        try {
            doc.insertString(doc.getLength(), content + "\n\n", infoSet);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    public static void log(String content, String messageType) {
        try {
            StyledDocument doc = logEdtTxt.getStyledDocument();
            switch (messageType) {
                case "INFO":
                    doc.insertString(doc.getLength(), content + "\n\n", infoSet);
                    break;
                case "ERROR":
                    doc.insertString(doc.getLength(), content + "\n\n", errorSet);
                    break;
                case "BOLD":
                    doc.insertString(doc.getLength(), content + "\n\n", boldSet);
                    break;
            }
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    public static void log(String content, String messageType, Component c) {
        try {
            StyledDocument doc = logEdtTxt.getStyledDocument();
            switch (messageType) {
                case "INFO":
                    doc.insertString(doc.getLength(), content + "\n\n", infoSet);
                    JOptionPane.showMessageDialog(c, content, "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case "ERROR":
                    doc.insertString(doc.getLength(), content + "\n\n", errorSet);
                    JOptionPane.showMessageDialog(c, content, "ERROR", JOptionPane.ERROR_MESSAGE);
                    break;
                case "BOLD":
                    doc.insertString(doc.getLength(), content + "\n\n", boldSet);
                    JOptionPane.showMessageDialog(c, content, "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
                    break;
            }
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

  /*  public final static JTextArea logEdtTxt = new JTextArea(20, 50);

    public static void log(String content) {
        logEdtTxt.append(content+"\r\n");
    }

    public static void log(Component c, String content, String messageType) {
        logEdtTxt.append(content+"\r\n");
        switch (messageType) {
            case "ERROR":
                JOptionPane.showMessageDialog(c, content, "ERROR", JOptionPane.ERROR_MESSAGE);
                break;
            case "INFORMATION":
                JOptionPane.showMessageDialog(c, content, "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
                break;
            default:
                break;
        }
    }*/
}
