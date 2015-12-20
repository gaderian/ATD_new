package modell.tile;
/*
 * Tile
 * 
 * Date 1/12- 2015
 * @author id12jwn
 */

import modell.Position;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Tile {

    private URL path;
    private BufferedImage img = null;
    private Position pos;

    /**
     * Tile is the constructor that will set the positon of the tile and
     * the image path that is different for all types of tiles.
     *
     * @param p, the position of the tile
     * @param imgPath, the path to witch image the tile have
     */
    public Tile(Position p, String imgPath) throws MalformedURLException {
	    pos = p;
	    path = getClass().getResource(imgPath);
        try {
            img = ImageIO.read(this.path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * changeImgRL is a method that will change the img depending on the url
     * it takes in.
     *
     * @param url, the url to the image
     */
    public void changeImgURL(String url){
        path = getClass().getResource(url);
        try {
            img = ImageIO.read(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * getImageURL is a method that gets the URL of the image
     * @return URL of the image
     */
    public URL getImageURL(){
        return path;
    }

    /**
     * getImage is a getter that will get the image of
     * the main.tile
     * @return image
     */
    public BufferedImage getImg(){
	return img;
    }

    /**
     * getPostion is a getter that will get the position of the main.tile
     * @return
     */
    public Position getPosition() {
	return pos;
    }

}
