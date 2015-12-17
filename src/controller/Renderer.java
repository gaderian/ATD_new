package controller;

import modell.GraphicEvent;
import modell.Position;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Stack;

/**
 * Class:       controller.Renderer
 * <p/>
 * Author:      Erik Moström
 * cs-user:     dv14emm
 * Date:        12/14/15
 */
public class Renderer {
    private final int width;
    private final int height;
    private BufferedImage image;
    private Graphics2D graphics;

    public Renderer(int height, int width){
        this.height = height;
        this.width = width;
    }

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

    private Position convertPosition(GraphicEvent event) {

        int cornerX = event.getPos().getX() - event.getImage().getWidth()/2;
        int cornerY = event.getPos().getY()- event.getImage().getHeight()/2;

        return new Position(cornerX, cornerY);
    }

    public BufferedImage getImage(){
        return image;
    }
}
