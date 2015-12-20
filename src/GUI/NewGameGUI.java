package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Builds the GUI for the menu when the new game button is pressed it reads in all the levels and displays them in a
 * drop down list where the user can select a level then actually start the game
 * <p>
 * Created by id12jzn on 2015-12-02.
 */
public class NewGameGUI {
    private ArrayList<String> levels;
    private JComboBox levelList = new JComboBox<String>();
    private JPanel panel = new JPanel();
    private JLabel overLabel = new JLabel();
    private JButton newGame;
    private JButton back;
    private JButton sound;
    private JButton help;
    private CLayout c;

    public NewGameGUI(CLayout c) {
        this.c = c;
    }

    /**
     * Builds the upper part of the GUI
     *
     * @return JPanel including this
     */
    private JPanel buildUpperPanel() {
        JPanel upperPanel = new JPanel();
        //BufferedImage myPicture = ImageIO.read(new File("loggo.png"));
        JLabel picLabel = new JLabel(new ImageIcon(getClass().getResource("loggo.png")));
        upperPanel.add(picLabel);
        upperPanel.setOpaque(false);

        return upperPanel;
    }

    /**
     * Builds the middle part of the GUI
     *
     * @return JPanel including this
     */
    private JPanel buildMiddlePanel() {
        JPanel middlePanel = new JPanel();
        middlePanel.setBackground(new Color(56, 134, 96));
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));
        JPanel levelListPanel = new JPanel();
        levelListPanel.setPreferredSize(new Dimension(55, 25));
        levelListPanel.setBackground(new Color(56, 134, 96));
        JPanel buttonPanelOver = new JPanel();
        buttonPanelOver.setPreferredSize(new Dimension(400, 45));
        buttonPanelOver.setBackground(new Color(56, 134, 96));
        JPanel buttonPanelUnder = new JPanel();
        buttonPanelUnder.setPreferredSize(new Dimension(400, 45));
        buttonPanelUnder.setBackground(new Color(56, 134, 96));
        JPanel buttonPanelLast = new JPanel();
        buttonPanelLast.setPreferredSize(new Dimension(400, 45));
        buttonPanelLast.setBackground(new Color(56, 134, 96));

        overLabel.setText("Level:");
        overLabel.setPreferredSize(new Dimension(55, 25));
        overLabel.setFont(new Font("arial", Font.PLAIN, 20));
        overLabel.setForeground(Color.white);
        updateLevels();
        levelList = new JComboBox(levels.toArray());
        levelListPanel.add(overLabel);
        levelListPanel.add(levelList);
        middlePanel.add(levelListPanel, BorderLayout.CENTER);

        back = new JButton(new ImageIcon(getClass().getResource("backButton.png")));
        back.addMouseListener(new BackListener(back, c));
        back.setBorderPainted(false);
        back.setContentAreaFilled(false);
        back.setFocusPainted(false);
        buttonPanelUnder.add(back);

        newGame = new JButton(new ImageIcon(getClass().getResource("newGameButton.png")));
        newGame.addMouseListener(new StartGameListener(newGame, c, levelList));
        newGame.setBorderPainted(false);
        newGame.setContentAreaFilled(false);
        newGame.setFocusPainted(false);
        buttonPanelOver.add(newGame);

        help = new JButton(new ImageIcon(getClass().getResource("helpButton.png")));
        help.addMouseListener(new HelpListener(help, c));
        help.setBorderPainted(false);
        help.setContentAreaFilled(false);
        help.setFocusPainted(false);
        buttonPanelLast.add(help);

        middlePanel.add(buttonPanelOver, BorderLayout.CENTER);
        middlePanel.add(buttonPanelLast, BorderLayout.SOUTH);
        middlePanel.add(buttonPanelUnder, BorderLayout.SOUTH);

        return middlePanel;
    }

    /**
     * Builds the lower part of the GUI including the toggle ssound icon
     *
     * @return JPanel including this
     */
    private JPanel buildSoundPanel() {
        JPanel soundPanel = new JPanel();
        soundPanel.setBackground(new Color(56, 134, 96));
        soundPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        sound = new JButton(new ImageIcon(getClass().getResource("sound.png")));
        sound.addMouseListener(new SoundListener(sound, c));

        sound.setBorderPainted(false);
        sound.setContentAreaFilled(false);
        sound.setFocusPainted(false);

        soundPanel.add(sound);

        return soundPanel;
    }

    /**
     * @return A panel that shows the game menu after "New game" is pressed
     */
    public JPanel getPanel() {
        panel.setBackground(new Color(56, 134, 96));
        panel.setLayout(new BorderLayout());
        //Add panels to the frame
        panel.add(buildUpperPanel(), BorderLayout.NORTH);
        panel.add(buildMiddlePanel(), BorderLayout.CENTER);
        panel.add(buildSoundPanel(), BorderLayout.SOUTH);

        return panel;
    }

    /**
     * Updates the level name
     */
    private void updateLevels() {
        levels = c.mapinfo.getLevelNames();
    }
}
