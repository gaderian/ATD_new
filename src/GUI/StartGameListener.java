package GUI;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Listener for showing the new game GUI and also updates the button
 *
 * Created by id12jzn on 2015-12-09.
 */
public class StartGameListener implements MouseListener {
    private JButton button;
    private CLayout c;
    private JComboBox<String> jcb;

    public StartGameListener(JButton button, CLayout c, JComboBox<String> jcb) {
        this.button = button;
        this.c = c;
        this.jcb = jcb;
    }

    public void mouseClicked(MouseEvent e) {
        c.showGame(jcb.getSelectedItem().toString());
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(java.awt.event.MouseEvent e) {
        button.setIcon(new ImageIcon( getClass().getResource("newGameHover.png") ));
    }

    public void mouseExited(MouseEvent e) {
        button.setIcon(new ImageIcon( getClass().getResource("newGameButton.png") ));
    }
}
