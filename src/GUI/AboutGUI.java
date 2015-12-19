package GUI;

import javax.swing.*;
import java.awt.*;
/* Start
 *
 * Date 29/11- 2015
 * @author id12jwn
 */

public class AboutGUI extends JPanel {

    // create the variable that will be useed
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

    public AboutGUI(CLayout c) {
        this.c = c;
    }

    /**
     * bulidUpperPanel is a method that will build the upperPanel that will
     * be used in the about screen
     *
     * @return, return the upperPanel
     */
    private JPanel buildUpperPanel() {

        JPanel upperPanel = new JPanel();

        // create a image of the games loggo
        JLabel picLabel = new JLabel(new ImageIcon( getClass().
                getResource("loggo.png") ));

        // adding the loggo to the panel
        upperPanel.add(picLabel);
        upperPanel.setOpaque(false);

        return upperPanel;
    }

    /**
     * bulidMiddelPanel is a method that will build the middelPanel
     * that will be used in the about screen. The panel is sepeated
     * in tree other panels
     *
     * @return the middelPanel
     */
    private JPanel buildMiddlePanel() {

        // crates and the middelPanel
        JPanel middlePanel = new JPanel();

        // sets a boxlayout on the panel
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));

        // give the panel a background
        middlePanel.setBackground(new Color(56, 134, 96));

        // crates a new panel tha will be in the middelpanel
        JPanel overPanel = new JPanel();

        // setting the size of the panel
        overPanel.setPreferredSize(new Dimension(400, 30));


        overPanel.setBackground(new Color(56, 134, 96));

        JPanel underPanel = new JPanel();
        underPanel.setPreferredSize(new Dimension(4, 100));
        underPanel.setBackground(new Color(56, 134, 96));

        overLabel.setLayout(new FlowLayout(FlowLayout.CENTER));
        overPanel.setPreferredSize(new Dimension(400,25));
        overLabel.setText("Who made this game:");
        overLabel.setFont(new Font("arial", Font.PLAIN, 30));
        overLabel.setForeground(Color.white);
        overPanel.add(overLabel, BorderLayout.CENTER);

        middelLabel1.setPreferredSize(new Dimension(300,25));
        middelLabel1.setText("Isak Hjelt");
        middelLabel1.setFont(new Font("arial", Font.PLAIN, 25));
        middelLabel1.setForeground(Color.white);
        middelLabel1.setHorizontalAlignment(JLabel.CENTER);
        underPanel.add(middelLabel1, BorderLayout.NORTH);

        middelLabel2.setPreferredSize(new Dimension(300,25));
        middelLabel2.setText("Erik Moström");
        middelLabel2.setFont(new Font("arial", Font.PLAIN, 25));
        middelLabel2.setForeground(Color.white);
        middelLabel2.setHorizontalAlignment(JLabel.CENTER);
        underPanel.add(middelLabel2, BorderLayout.NORTH);


        middelLabel3.setPreferredSize(new Dimension(300,25));
        middelLabel3.setText("Julia Westman ");
        middelLabel3.setFont(new Font("arial", Font.PLAIN, 25));
        middelLabel3.setLayout(new FlowLayout(FlowLayout.CENTER));
        middelLabel3.setForeground(Color.white);
        middelLabel3.setHorizontalAlignment(JLabel.CENTER);
        underPanel.add(middelLabel3, BorderLayout.CENTER);

        middelLabel4.setPreferredSize(new Dimension(300,25));
        middelLabel4.setText("Joakim Zakrisson");
        middelLabel4.setFont(new Font("airal", Font.PLAIN, 25));
        middelLabel4.setForeground(Color.white);
        middelLabel4.setHorizontalAlignment(JLabel.CENTER);
        underPanel.add(middelLabel4, BorderLayout.CENTER);


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
        back.addMouseListener(new BackListener(back, c));
        back.setBorderPainted(false);
        back.setContentAreaFilled(false);
        back.setFocusPainted(false);
        back.setHorizontalAlignment(JButton.CENTER);

        lowerPanel.add(back );
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
