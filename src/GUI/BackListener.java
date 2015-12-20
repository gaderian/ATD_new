package GUI;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


/* BackListener
 *
 * Date 15/12- 2015
 * @author id12jwn
 */

public class BackListener implements MouseListener {
    private JButton button;
    private CLayout c;

    /**
     * BackListener is a constructor that will sett the
     * parameters button anc Clayout
     *
     * @param button, The button it listen to
     * @param c, The clayout class
     */
    public BackListener(JButton button, CLayout c) {

        this.button = button;
        this.c = c;
    }

    /**
     * When a user click on the button it will show the card
     * menu
     * @param e, a event
     */
    public void mouseClicked(MouseEvent e) {
        c.showCard("menu");

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
    public void mouseEntered(MouseEvent e) {
        button.setIcon(new ImageIcon( getClass().getResource("backHover.png") ));
    }

    /**
     * The method mouseEnterd will change the image
     * @param e, a event
     */
    public void mouseExited(MouseEvent e) {
        button.setIcon(new ImageIcon( getClass().getResource("backButton.png") ));

    }

}
