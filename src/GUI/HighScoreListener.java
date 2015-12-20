package GUI;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Listener for showing the highscore GUI and changing the highscore button
 *
 * Created by id12jzn on 2015-11-30.
 */
public class HighScoreListener implements MouseListener {
    private JButton button;
    private CLayout c;

    public HighScoreListener(JButton button, CLayout c) {
        this.button = button;
        this.c = c;
    }

    /**
     * Updates the highscores before showing the highscore GUI
     *
     * @param e mouse event
     */
    public void mouseClicked(MouseEvent e) {
        c.highScore.updateHighscores();
        c.showCard("highScore");
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
        button.setIcon(new ImageIcon(getClass().getResource("highScoreHover.png")));
    }

    public void mouseExited(MouseEvent e) {
        button.setIcon(new ImageIcon(getClass().getResource("highScoreButton.png")));
    }
}
