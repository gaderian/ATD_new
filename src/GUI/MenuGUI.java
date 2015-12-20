package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Builds the starting menu GUI for when the application is starting
 * <p>
 * Created by id12jzn on 2015-11-30.
 */
public class MenuGUI {

    private JPanel panel = new JPanel();
    private JButton newGame;
    private JButton highScore;
    private JButton quit;
    private JButton sound;
    private JButton about;
    private String gameTitle = "Anti main.tower Defence";
    private JPanel upperPanel = null;
    private JPanel middlePanel = null;
    private JPanel lowerPanel;
    private CLayout c;

    public MenuGUI(CLayout c) {
        this.c = c;
    }

    /**
     * Builds the upper part of the GUI
     *
     * @return JPanel including this
     */
    private JPanel buildUpperPanel() {
        JPanel upperPanel = new JPanel();
        JLabel picLabel = new JLabel(new ImageIcon(getClass().getResource("loggo.png")));
        upperPanel.add(picLabel);
        upperPanel.setOpaque(false);

        return upperPanel;
    }

    /**
     *Builds the middle part of the GUI
     *
     * @return JPanel including this
     */
    private JPanel buildMiddlePanel() {
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));
        middlePanel.setBackground(new Color(56, 134, 96));

        JPanel buttonPanelOver = new JPanel();
        buttonPanelOver.setPreferredSize(new Dimension(400, 45));
        buttonPanelOver.setBackground(new Color(56, 134, 96));

        JPanel buttonPanelMiddle = new JPanel();
        buttonPanelMiddle.setPreferredSize(new Dimension(400, 45));
        buttonPanelMiddle.setBackground(new Color(56, 134, 96));

        JPanel buttonPanelUnder = new JPanel();
        buttonPanelUnder.setPreferredSize(new Dimension(400, 45));
        buttonPanelUnder.setBackground(new Color(56, 134, 96));

        JPanel buttonPanelLast = new JPanel();
        buttonPanelLast.setPreferredSize(new Dimension(400, 45));
        buttonPanelLast.setBackground(new Color(56, 134, 96));


        newGame = new JButton(new ImageIcon(getClass().getResource("newGameButton.png")));
        newGame.addMouseListener(new NewGameListener(newGame, c));
        newGame.setBorderPainted(false);
        newGame.setContentAreaFilled(false);
        newGame.setFocusPainted(false);
        buttonPanelOver.add(newGame);

        highScore = new JButton(new ImageIcon(getClass().getResource("highScoreButton.png")));
        highScore.addMouseListener(new HighScoreListener(highScore, c));
        highScore.setBorderPainted(false);
        highScore.setContentAreaFilled(false);
        highScore.setFocusPainted(false);
        buttonPanelMiddle.add(highScore);

        quit = new JButton(new ImageIcon(getClass().getResource("QuitButton.png")));
        quit.addMouseListener(new QuitListener(quit, c));
        quit.setBorderPainted(false);
        quit.setContentAreaFilled(false);
        quit.setFocusPainted(false);
        buttonPanelUnder.add(quit);

        about = new JButton(new ImageIcon(getClass().getResource("aboutButton.png")));
        about.addMouseListener(new AboutListener(about, c));
        about.setBorderPainted(false);
        about.setContentAreaFilled(false);
        about.setFocusPainted(false);
        buttonPanelLast.add(about);

        middlePanel.add(buttonPanelOver, BorderLayout.NORTH);
        middlePanel.add(buttonPanelMiddle, BorderLayout.CENTER);
        middlePanel.add(buttonPanelLast, BorderLayout.SOUTH);
        middlePanel.add(buttonPanelUnder, BorderLayout.SOUTH);

        return middlePanel;
    }

    /**
     * Builds the lower part of the GUI
     *
     * @return JPanel including this
     */
    private JPanel buildLowerPanel() {
        JPanel lowerPanel = new JPanel();
        lowerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        lowerPanel.setBackground(new Color(56, 134, 96));

        sound = new JButton(new ImageIcon(getClass().getResource("sound.png")));
        sound.addMouseListener(new SoundListener(sound, c));
        sound.setBorderPainted(false);
        sound.setContentAreaFilled(false);
        sound.setFocusPainted(false);
        lowerPanel.add(sound, new FlowLayout(FlowLayout.RIGHT));

        return lowerPanel;
    }

    /**
     * @return The main menu GUI as a JPanel
     */
    public JPanel getPanel() {
        panel.setBackground(new Color(56, 134, 96));
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
