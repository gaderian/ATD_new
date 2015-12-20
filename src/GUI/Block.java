package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

/**
 * Class: Block
 * Draws a certain block at a specified coordinate
 * <p>
 * Created by id12jzn on 2015-12-07.
 */
public class Block extends Rectangle {
    public int Id;

    /**
     * Creates a block at a certain coordinate on the grid-layout for the board
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @param width width of block
     * @param height height of block
     * @param Id id for block
     */
    public Block(int x, int y, int width, int height, int Id) {
        setBounds(x, y, width, height);
        this.Id = Id;
    }

    /**
     * Draws a graphic for the placed block
     *
     * @param g graphic to draw
     * @param tileImg image to place on block
     */
    public void draw(Graphics g, BufferedImage tileImg) {
        g.drawImage(tileImg, x, y, width, height, null);
    }
}
