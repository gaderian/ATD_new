package GUI;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * A MouseListener for the store which calls the buyUnit method from the interface if a user is clicking on a buy unit
 * button and if a user can afford the unit
 * <p>
 * Created by id12jzn on 2015-12-16.
 */
public class GameListener implements MouseListener {
    private CLayout c;
    private GameGUI g;

    public GameListener(CLayout c, GameGUI g) {
        this.c = c;
        this.g = g;
    }

    /**
     * Checks if the user clicks any of the store buttons, also checks if a user can afford a unit and if so it will
     * create certain unit
     *
     * @param e a mouse event
     */
    public void mouseClicked(MouseEvent e) {
        int x = e.getX() - ((g.myWidth / 2) - (g.gameBoard.blockSize * (g.gameBoard.worldWidth / 2)));
        int y = e.getY();
        System.out.println(x + " " + y);
        c.userinfo.hasClicked(x, y);

        if (g.store.button[0].contains(e.getPoint()) && c.userinfo.canBuyUnit(1)) {
            c.userinfo.buyUnit(1);
        }
        if (g.store.button[1].contains(e.getPoint()) && c.userinfo.canBuyUnit(2)) {
            c.userinfo.buyUnit(2);
        }
        if (g.store.button[2].contains(e.getPoint()) && c.userinfo.canBuyUnit(3)) {
            c.userinfo.buyUnit(3);
            g.gameOver = true;
        }
        c.game.u = c.userinfo.getUser();
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(java.awt.event.MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

}
