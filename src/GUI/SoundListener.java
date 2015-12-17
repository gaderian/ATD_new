package GUI;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Joakim on 2015-11-30.
 */
class SoundListener implements MouseListener {

        private JButton button;
        private CLayout c;

        public SoundListener(JButton button, CLayout c) {
            this.button = button;
            this.c = c;
        }

    public void mouseClicked(MouseEvent e) {

        if(button.getIcon().toString().contains("Off")) {
            button.setIcon(new ImageIcon( getClass().getResource("sound.png") ));
            c.music.play("defaultMusic.wav");
        }
        else {
            button.setIcon(new ImageIcon( getClass().getResource("soundOff.png") ));
            c.music.stop();
        }

    }

    public void mousePressed(MouseEvent e) {
        //button.setIcon(new ImageIcon( getClass().getResource("src/main/resources/soundOff.png") ));

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(java.awt.event.MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {

    }

    }