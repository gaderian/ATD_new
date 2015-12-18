package controller;

import modell.GraphicEvent;
import modell.Position;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Stack;

/**
 * Class:       controller.Renderer
 *
 * Author:      Erik Moström
 * cs-user:     dv14emm
 * Date:        12/14/15
 */
public class Renderer {
    private final int width;
    private final int height;
    private BufferedImage image;
    private Graphics2D graphics;

    /**
     * Creates a instance of Renderer
     *
     * @param height the height in pixels of the images which will be produced.
     * @param width the width in pixels of the images which will be produced.
     */
    public Renderer(int height, int width){
        this.height = height;
        this.width = width;
    }

    /**
     * Will generate the image from an stack of GraphicEvents.
     *
     * @param s a Stack containing the GraphicEvents which the image should be
     *          produced from
     */
    public void drawImage(Stack<GraphicEvent> s){
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setBackground(new Color(255, 255, 255, 0));

        while (!s.isEmpty()){
            GraphicEvent event = s.pop();

            Position corner = convertPosition(event);
            graphics.drawImage(event.getImage(), corner.getX(), corner.getY(), null);
        }

    }

    /**
     * Will calculate the coordinates of the upper left corner of a image in a
     * GraphicEvent.
     *
     * @param event the GraphicEvent for which the calculation will be made
     * @return the corner Position of the image
     */
    private Position convertPosition(GraphicEvent event) {

        int cornerX = event.getPos().getX() - event.getImage().getWidth()/2;
        int cornerY = event.getPos().getY()- event.getImage().getHeight()/2;

        return new Position(cornerX, cornerY);
    }

    /**
     * Will return the image rendered as an BufferedImage
     *
     * @return a BufferedImage of the latest rendered Image.
     */
    public BufferedImage getImage(){
        return image;
    }
}
