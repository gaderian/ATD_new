package GUI;

import javax.swing.*;
import java.awt.*;
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
    public void draw(Graphics g, URL tilepath) {

        g.drawImage(new ImageIcon(tilepath, "tile").getImage(), x, y, width, height, null);
    }
}
