package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by juliawestman on 2015-12-15.
 */
public class HelpGUI extends JPanel {

    private CLayout c;
    private JButton sound;
    private JButton back;
    private JPanel panel= new JPanel();;
    private JPanel upperPanel;
    private JPanel middlePanel;
    private JPanel lowerPanel;
    private JLabel overLabel = new JLabel();
    private JLabel middelLabel1 = new JLabel();
    private JLabel middelLabel2 = new JLabel();
    private JLabel middelLabel3 = new JLabel();
    private JLabel middelLabel4 = new JLabel();
    private JLabel middelLabel5 = new JLabel();
    private JLabel loverLabel = new JLabel();

    public HelpGUI(CLayout c) {
        this.c = c;
    }

    private JPanel buildUpperPanel() {
        JPanel upperPanel = new JPanel();

        //BufferedImage myPicture = ImageIO.read(new File("loggo.png"));
        JLabel picLabel = new JLabel(new ImageIcon( getClass().getResource("loggo.png") ));
        upperPanel.add(picLabel);

        upperPanel.setOpaque(false);

        return upperPanel;
    }
    private JPanel buildMiddlePanel() {
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));
        middlePanel.setBackground(new Color(56, 134, 96));

        JPanel overPanel = new JPanel();
        overPanel.setPreferredSize(new Dimension(400, 30));
        overPanel.setBackground(new Color(56, 134, 96));

        JPanel underPanel = new JPanel();
        underPanel.setPreferredSize(new Dimension(4, 100));
        underPanel.setBackground(new Color(56, 134, 96));

        overLabel.setLayout(new FlowLayout(FlowLayout.CENTER));
        overPanel.setPreferredSize(new Dimension(400,25));
        overLabel.setText("How to Play:");
        overLabel.setFont(new Font("Copperplate", Font.PLAIN, 30));
        overLabel.setForeground(Color.white);
        overPanel.add(overLabel, BorderLayout.CENTER);

        middelLabel1.setPreferredSize(new Dimension(500,25));
        middelLabel1.setText("Select and buy you'r unit's. Try to stay alive to the");
        middelLabel1.setFont(new Font("Copperplate", Font.PLAIN, 18));
        middelLabel1.setForeground(Color.white);
        underPanel.add(middelLabel1, BorderLayout.NORTH);

        middelLabel2.setPreferredSize(new Dimension(500,25));
        middelLabel2.setText("end of the pathWay. All the units has different ");
        middelLabel2.setFont(new Font("Copperplate", Font.PLAIN, 18));
        middelLabel2.setForeground(Color.white);
        underPanel.add(middelLabel2, BorderLayout.NORTH);


        middelLabel3.setPreferredSize(new Dimension(500,25));
        middelLabel3.setText("speed and different health. ");
        middelLabel3.setFont(new Font("Copperplate", Font.PLAIN, 18));
        middelLabel3.setForeground(Color.white);
        underPanel.add(middelLabel3, BorderLayout.CENTER);

        middelLabel4.setPreferredSize(new Dimension(500,25));
        middelLabel4.setText("You will win when you complete the maps number ");
        middelLabel4.setFont(new Font("Copperplate", Font.PLAIN, 18));
        middelLabel4.setForeground(Color.white);
        underPanel.add(middelLabel4, BorderLayout.CENTER);

        middelLabel5.setPreferredSize(new Dimension(500,25));
        middelLabel5.setText("of wave");
        middelLabel5.setFont(new Font("Copperplate", Font.PLAIN, 18));
        middelLabel5.setForeground(Color.white);
        underPanel.add(middelLabel5, BorderLayout.CENTER);

        middlePanel.add(overPanel);
        middlePanel.add(underPanel);


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

        back = new JButton(new ImageIcon( getClass().getResource("backButton.png") ));
        back.addMouseListener(new BackToGameMenuListener(back, c));
        back.setBorderPainted(false);
        back.setContentAreaFilled(false);
        back.setFocusPainted(false);
        back.setHorizontalAlignment(JLabel.CENTER);

        lowerPanel.add(back);
        lowerPanel.add(sound, new FlowLayout(FlowLayout.RIGHT));

        return lowerPanel;
    }

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
