package GUI;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/* AboutListener
 *
 * Date 29/11- 2015
 * @author id12jwn
 */
public class AboutListener implements MouseListener {
    private JButton button;
    private CLayout c;

    /**
     * AboutListener is a constructor that will sett the
     * parameters button anc Clayout
     *
     * @param button, The button it listen to
     * @param c, The clayout class
     */
    public AboutListener(JButton button, CLayout c) {

        this.button = button;
        this.c = c;
    }

    /**
     * When a user click on the button it will show the card
     * about
     * @param e, a event
     */
    public void mouseClicked(MouseEvent e) {
        c.showCard("about");

    }

    /**
     * The method needs to be there
     * @param e
     */
    public void mousePressed(MouseEvent e) {
    }

    /**
     * The method needs to be there
     * @param e
     */
    public void mouseReleased(MouseEvent e) {
    }

    /**
     * The method mouseEnterd will change the image
     * @param e, a event
     */
    public void mouseEntered(java.awt.event.MouseEvent e) {
        button.setIcon(new ImageIcon( getClass().getResource("aboutHover.png") ));
    }

    /**
     * The method mouseExited will change the image
     * @param e, a event
     */
    public void mouseExited(MouseEvent e) {
        button.setIcon(new ImageIcon( getClass().getResource("aboutButton.png") ));

    }

}
