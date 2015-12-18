package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by id12jzn on 2015-12-15.
 *
 * Creates the GUI for when a game is ended as a JPanel
 */
public class GameOverGUI {

    private CLayout c;
    private JPanel panel = new JPanel();
    private JButton ok;
    private JButton sound;
    private JButton newGame;
    private JButton quit;
    private JButton highScore;
    private JPanel upperPanel = null;
    private JPanel middlePanel = null;
    private JPanel lowerPanel;
    private JLabel victoryText = new JLabel();

    public GameOverGUI(CLayout c) {
        this.c = c;

    }

    /**
     * Changes the text in the upper panel based on if the user wins or looses
     */
    public void setEndText() {

        if (c.userinfo.gameWon()){
            victoryText.setText("<html>"+"YOU WIN !"+"<br>"+"Score: "+c.userinfo.getUser().getScore()+"<html>");
        } else {
            victoryText.setText("<html>"+"YOU LOSE !"+"<br>"+"Score: "+c.userinfo.getUser().getScore()+"<html>");
        }


    }

    private JPanel buildUpperPanel() {
        JPanel upperPanel = new JPanel();
        JPanel space = new JPanel();
        space.setBackground(new Color(56, 134, 96));
        space.setPreferredSize(new Dimension(800,100));
        upperPanel.setPreferredSize(new Dimension(400, 200));

        victoryText.setFont(new Font("Arial", Font.ITALIC, 35));
        victoryText.setForeground(Color.white);
        upperPanel.setBackground(new Color(56, 134, 96));
        upperPanel.add(space);
        upperPanel.add(victoryText);

        return upperPanel;
    }

    private JPanel buildMiddlePanel() {
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));
        middlePanel.setBackground(new Color(56, 134, 96));

        newGame = new JButton(new ImageIcon( getClass().getResource("newGameButton.png") ));
        newGame.addMouseListener(new NewGameListener(newGame, c));
        newGame.setBorderPainted(false);
        newGame.setContentAreaFilled(false);
        newGame.setFocusPainted(false);

        highScore = new JButton(new ImageIcon( getClass().getResource("highScoreButton.png") ));
        highScore.addMouseListener(new HighScoreListener(highScore, c));
        highScore.setBorderPainted(false);
        highScore.setContentAreaFilled(false);
        highScore.setFocusPainted(false);

        quit = new JButton(new ImageIcon( getClass().getResource("QuitButton.png") ));
        quit.addMouseListener(new QuitListener(quit, c));
        quit.setBorderPainted(false);
        quit.setContentAreaFilled(false);
        quit.setFocusPainted(false);

        newGame.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        middlePanel.add(newGame);
        highScore.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        middlePanel.add(highScore);
        quit.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        middlePanel.add(quit);

        return middlePanel;

    }

    private JPanel buildLowerPanel() {
        JPanel lowerPanel = new JPanel();
        lowerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        lowerPanel.setBackground(new Color(56, 134, 96));

        sound = new JButton(new ImageIcon( getClass().getResource("sound.png") ));
        sound.addMouseListener(new SoundListener(sound, c));

        sound.setBorderPainted(false);
        sound.setContentAreaFilled(false);
        sound.setFocusPainted(false);

        lowerPanel.add(sound);

        return lowerPanel;
    }



    /**
     *
     * @return The main menu GUI as a JPanel
     */
    public JPanel getPanel() {
        //panel.setBackground(new Color(169,255,151));
        upperPanel = buildUpperPanel();
        middlePanel = buildMiddlePanel();
        lowerPanel = buildLowerPanel();

        panel.setLayout(new BorderLayout());


        //Add panels to the frame
        panel.add(upperPanel, BorderLayout.NORTH);
        panel.add(middlePanel, BorderLayout.CENTER);
        panel.add(lowerPanel, BorderLayout.SOUTH);

        return panel;
    }
}
