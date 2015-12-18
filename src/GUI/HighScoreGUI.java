package GUI;

import javax.swing.*;

import modell.HighscoreDB;

import java.awt.*;
import java.util.ArrayList;

/**
 * Class HighScoreGUI
 * Builds the GUI for the highscore panel. This calls the HighscoreDB class to retrieve the info from the database
 * <p>
 * Created by Joakim on 2015-12-02.
 */
public class HighScoreGUI {

    private JFrame frame;
    private JPanel panel = new JPanel();
    private JButton newGame;
    private JButton highScore;
    private JButton quit;
    private JButton sound;
    private JButton back;
    private JPanel upperPanel = null;
    private JPanel lowerPanel;
    private JPanel middelPanel;
    private JTextArea textArea = new JTextArea("HIGHSCORES\n", 20, 20);
    private JScrollPane scrollPane = new JScrollPane(textArea,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    ArrayList<String> highscores = new ArrayList<>();
    private CLayout c;
    HighscoreDB dbs = new HighscoreDB();
    public HighScoreGUI(CLayout c) {
        this.c = c;
    }
    JLabel label = new JLabel();

    private JPanel buildUpperPanel() {
        JPanel upperPanel = new JPanel();
        JLabel picLabel = new JLabel(new ImageIcon(getClass().getResource("loggo.png")));
        upperPanel.add(picLabel);
        upperPanel.setOpaque(false);

        return upperPanel;
    }

    private JPanel buildMiddelPanel() {
        JPanel middelPanel = new JPanel();
        middelPanel.setBackground(new Color(56, 134, 96));
        updateHighscores();
        label.setFont(new Font("Arial", Font.PLAIN, 25));
        label.setForeground(Color.white);
        middelPanel.add(label);

        return middelPanel;
    }

    private JPanel buildLowerPanel() {
        JPanel lowerPanel = new JPanel();
        lowerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        lowerPanel.setBackground(new Color(56, 134, 96));
        JPanel soundPanel = new JPanel();
        soundPanel.setBackground(new Color(56, 134, 96));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(56, 134, 96));

        sound = new JButton(new ImageIcon(getClass().getResource("sound.png")));
        sound.addMouseListener(new SoundListener(sound, c));
        sound.setBorderPainted(false);
        sound.setContentAreaFilled(false);
        sound.setFocusPainted(false);
        soundPanel.add(sound);

        back = new JButton(new ImageIcon(getClass().getResource("backButton.png")));
        back.addMouseListener(new BackListener(back, c));
        back.setLayout(new FlowLayout(FlowLayout.LEFT));
        back.setBorderPainted(false);
        back.setContentAreaFilled(false);
        back.setFocusPainted(false);
        buttonPanel.add(back);

        lowerPanel.add(buttonPanel, BorderLayout.WEST);
        lowerPanel.add(soundPanel, BorderLayout.SOUTH);

        return lowerPanel;
    }

    /**
     * @return The highscore main.GUI as a JPanel
     */
    public JPanel getPanel() {

        upperPanel = buildUpperPanel();
        middelPanel = buildMiddelPanel();
        lowerPanel = buildLowerPanel();

        panel.setBackground(new Color(56, 134, 96));
        panel.setLayout(new BorderLayout());

        //Add panels to the frame
        panel.add(upperPanel, BorderLayout.NORTH);
        panel.add(middelPanel, BorderLayout.CENTER);
        panel.add(lowerPanel, BorderLayout.SOUTH);

        return panel;
    }

    /**
     * Updates the current highscore-list on the GUI
     */
    public void updateHighscores() {
        label.setText("");
        highscores = dbs.getData();
        highscores.forEach((temp) -> label.setText("<html>" + label.getText() + "\t\t\t" + "<br>" + temp + "<html>"));

    }


}
