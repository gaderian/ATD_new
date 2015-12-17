package GUI;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by id12jzn on 2015-12-16.
 */
public class GameListener implements MouseListener {

    private JButton button;
    private CLayout c;
    private GameGUI g;

    public GameListener(CLayout c, GameGUI g) {

        this.button = button;
        this.c = c;
        this.g = g;
    }
// TODO Gårdkoda ikte köpandet
    public void mouseClicked(MouseEvent e) {
        //button.setIcon(new ImageIcon("main/resources/highScorePressed.png"));
        //c.showCard("highScore");

        int x = e.getX() - ((g.myWidth / 2) - (g.gameBoard.blockSize*(g.gameBoard.worldWidth/2)));
        int y = e.getY();
        System.out.println(x+" "+y);
        c.userinfo.hasClicked(x, y);

        if(g.store.button[0].contains(e.getPoint()) && c.userinfo.canBuyUnit(1)) {
            c.userinfo.buyUnit(1);
        }
        if(g.store.button[1].contains(e.getPoint()) && c.userinfo.canBuyUnit(2)) {
            c.userinfo.buyUnit(2);
        }
        if(g.store.button[2].contains(e.getPoint()) && c.userinfo.canBuyUnit(3)) {
            c.userinfo.buyUnit(3);
            g.gameOver = true;
            //c.showGameOver();
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
