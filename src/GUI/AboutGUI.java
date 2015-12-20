package GUI;

import javax.swing.*;
import java.awt.*;
/* AboutGUI
 *
 * Date 29/11- 2015
 * @author id12jwn
 */

public class AboutGUI extends JPanel {

    // create the variable that will be used
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

    /**
     * AboutGUI is a constructor that will sett parametern c
     * to its Clayout
     * @param c
     */
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

        // create a new panel tha will be in the middelpanel
        JPanel overPanel = new JPanel();

        // setting the size of the panel and the background
        overPanel.setPreferredSize(new Dimension(400, 30));
        overPanel.setBackground(new Color(56, 134, 96));

        // create a new panel tha will be in the middelpanel
        JPanel underPanel = new JPanel();

        // setting the size of the panel and the background
        underPanel.setPreferredSize(new Dimension(4, 100));
        underPanel.setBackground(new Color(56, 134, 96));

        // Setting that overLabel vill flow to center
        overLabel.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Setting the text, the size of the text and the coloer of it
        overLabel.setText("Who made this game:");
        overLabel.setFont(new Font("arial", Font.PLAIN, 30));
        overLabel.setForeground(Color.white);

        // adding the overLabel to the overPanel
        overPanel.add(overLabel, BorderLayout.CENTER);

        // Setting the size of the label
        middelLabel1.setPreferredSize(new Dimension(300,25));

        // Setting text, textszie and the color of it
        middelLabel1.setText("Isak Hjelt");
        middelLabel1.setFont(new Font("arial", Font.PLAIN, 25));
        middelLabel1.setForeground(Color.white);
        middelLabel1.setHorizontalAlignment(JLabel.CENTER);

        // adding the text to the underPAnel
        underPanel.add(middelLabel1, BorderLayout.NORTH);

        // Setting the size of the label
        middelLabel2.setPreferredSize(new Dimension(300,25));

        // Setting text, textszie and the color of it
        middelLabel2.setText("Erik Moström");
        middelLabel2.setFont(new Font("arial", Font.PLAIN, 25));
        middelLabel2.setForeground(Color.white);
        middelLabel2.setHorizontalAlignment(JLabel.CENTER);

        // adding the text to the underPAnel
        underPanel.add(middelLabel2, BorderLayout.NORTH);

        // Setting the size of the label
        middelLabel3.setPreferredSize(new Dimension(300,25));

        // Setting text, textszie and the color of it
        middelLabel3.setText("Julia Westman ");
        middelLabel3.setFont(new Font("arial", Font.PLAIN, 25));
        middelLabel3.setLayout(new FlowLayout(FlowLayout.CENTER));
        middelLabel3.setForeground(Color.white);
        middelLabel3.setHorizontalAlignment(JLabel.CENTER);

        // adding the text to the underPAnel
        underPanel.add(middelLabel3, BorderLayout.CENTER);

        // Setting the size of the label
        middelLabel4.setPreferredSize(new Dimension(300,25));

        // Setting text, textszie and the color of it
        middelLabel4.setText("Joakim Zakrisson");
        middelLabel4.setFont(new Font("airal", Font.PLAIN, 25));
        middelLabel4.setForeground(Color.white);
        middelLabel4.setHorizontalAlignment(JLabel.CENTER);

        // adding the text to the underPanel
        underPanel.add(middelLabel4, BorderLayout.CENTER);

        // adding the panels in to the middelpanel
        middlePanel.add(overPanel);
        middlePanel.add(underPanel);

        return middlePanel;

    }

    /**
     * bulidLowerPanel is a method that will build the lowerPanel
     * that will be used in the about screen.
     * @return the lowerPanel
     */
    private JPanel buildLowerPanel() {

        // create the lowerPanel
        JPanel lowerPanel = new JPanel();

        // Setting a flowlayout to right and the backgound
        lowerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        lowerPanel.setBackground(new Color(56, 134, 96));

        // create a JButton that will hava a image as icon
        sound = new JButton(new ImageIcon( getClass().
                getResource("sound.png") ));

        // adding a mouseListner to the button
        sound.addMouseListener(new SoundListener(sound, c));

        // adding settings on the button
        sound.setBorderPainted(false);
        sound.setContentAreaFilled(false);
        sound.setFocusPainted(false);

        // create a Jbutton that will have a imgae as icon on it
        back = new JButton(new ImageIcon( getClass().
                getResource("backButton.png")));

        // adding a mouesListner
        back.addMouseListener(new BackListener(back, c));

        // adding settings on the button
        back.setBorderPainted(false);
        back.setContentAreaFilled(false);
        back.setFocusPainted(false);
        back.setHorizontalAlignment(JButton.CENTER);

        // adding buttons to the panel
        lowerPanel.add(back);
        lowerPanel.add(sound, new FlowLayout(FlowLayout.RIGHT));

        return lowerPanel;
    }

    /**
     * getPanel is a method that will builed all panels and add them.
     *
     * @return the panel
     */
    public JPanel getPanel() {
        // Setting the background
        panel.setBackground(new Color(56, 134, 96));

        // building panels
        upperPanel = buildUpperPanel();
        middlePanel = buildMiddlePanel();
        lowerPanel = buildLowerPanel();

        // setting borderLayout
        panel.setLayout(new BorderLayout());

        // Adding panels to the frame
        panel.add(upperPanel, BorderLayout.NORTH);
        panel.add(middlePanel, BorderLayout.CENTER);
        panel.add(lowerPanel, BorderLayout.SOUTH);

        return panel;
    }

}
