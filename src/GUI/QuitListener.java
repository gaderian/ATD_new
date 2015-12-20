package GUI;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Listener for when the quit button is pressed and is able to update the button. When it's pressed it closes the game
 * <p>
 * Created by id12jzn on 2015-11-30.
 */
public class QuitListener implements MouseListener {
    private JButton button;
    private CLayout c;

    public QuitListener(JButton button, CLayout c) {
        this.button = button;
        this.c = c;
    }

    public void mouseClicked(MouseEvent e) {
        c.exit();
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(java.awt.event.MouseEvent e) {
        button.setIcon(new ImageIcon(getClass().getResource("QuitHover.png")));
    }

    public void mouseExited(MouseEvent e) {
        button.setIcon(new ImageIcon(getClass().getResource("QuitButton.png")));
    }
}
