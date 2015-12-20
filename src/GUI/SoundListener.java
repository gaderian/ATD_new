package GUI;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Listener for when the sound-button is pressed. It pauses or starts the sound depending on if the sound is cerruntly
 * playing or not. Laos is able to update the button.
 *
 * Created by Joakim on 2015-11-30.
 */
class SoundListener implements MouseListener {
    private JButton button;
    private CLayout c;

    public SoundListener(JButton button, CLayout c) {
        this.button = button;
        this.c = c;
    }

    /**
     * Plays or pauses the background music depending on which is toggled at the momemt
     *
     * @param e mouse event
     */
    public void mouseClicked(MouseEvent e) {
        if (button.getIcon().toString().contains("Off")) {
            button.setIcon(new ImageIcon(getClass().getResource("sound.png")));
            c.music.play("defaultMusic.wav");
        } else {
            button.setIcon(new ImageIcon(getClass().getResource("soundOff.png")));
            c.music.stop();
        }
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(java.awt.event.MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
}