package GUI;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by id12jzn on 2015-12-17.
 */
public class SettingListener implements MouseListener{

        private JButton button;
        private CLayout c;
        PopupMenu pm;

        public SettingListener(JButton button, CLayout c) {

            this.button = button;
            this.c = c;
        }

        public void mouseClicked(MouseEvent e) {
            //c.showCard("menu");
            pm = new PopupMenu(c);
            pm.openPopup();
        }

        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
            button.setIcon(new ImageIcon( getClass().getResource("settings.png") ));
        }

        public void mouseExited(MouseEvent e) {
            button.setIcon(new ImageIcon( getClass().getResource("settings.png") ));

        }

    }
