package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

/**
 * Class: Block
 * Draws a certain block at a specified coordinate
 * <p>
 * Created by Joakim on 2015-12-07.
 */
public class Block extends Rectangle {
    public int Id;

    public Block(int x, int y, int width, int height, int Id) {
        setBounds(x, y, width, height);
        this.Id = Id;
    }

    /*Added by Erik M*/
    public void draw(Graphics g, BufferedImage tileImg) {

        g.drawImage(tileImg, x, y, width, height, null);
    }
}
