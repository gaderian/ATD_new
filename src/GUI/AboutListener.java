package GUI;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by juliawestman on 2015-12-15.
 */
public class AboutListener implements MouseListener {
    private JButton button;
    private CLayout c;

    public AboutListener(JButton button, CLayout c) {

        this.button = button;
        this.c = c;
    }

    public void mouseClicked(MouseEvent e) {
        c.showCard("about");

    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(java.awt.event.MouseEvent e) {
        button.setIcon(new ImageIcon( getClass().getResource("aboutHover.png") ));
    }

    public void mouseExited(MouseEvent e) {
        button.setIcon(new ImageIcon( getClass().getResource("aboutButton.png") ));

    }

}
