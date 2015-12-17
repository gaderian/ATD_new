package GUI;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by juliawestman on 2015-12-17.
 */
public class BackToGameMenuListener implements MouseListener{
        private JButton button;
        private CLayout c;

        public BackToGameMenuListener(JButton button, CLayout c) {

            this.button = button;
            this.c = c;
        }

        public void mouseClicked(MouseEvent e) {
            c.showCard("newGame");

        }

        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
            button.setIcon(new ImageIcon( getClass().getResource("backHover.png") ));
        }

        public void mouseExited(MouseEvent e) {
            button.setIcon(new ImageIcon( getClass().getResource("backButton.png") ));

        }


}
