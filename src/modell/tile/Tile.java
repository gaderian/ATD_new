package modell.tile;
/*
 * main.java.main.tile.main.tile
 * 
 * Date 1/12- 2015
 * @author id12jwn
 */

import modell.Position;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * main.java.main.tile.main.tile is a class is a abctact class...
 */
public abstract class Tile {

    private URL path;
    //private String path;
    private BufferedImage img = null;
    private Position pos;

    /**
     * main.tile is the constructor that will set the values...
     * @param p
     * @param imgPath
     */
    protected Tile(Position p, String imgPath) throws MalformedURLException {
	    pos = p;
	    path = getClass().getResource(imgPath);
        readInImg();
    }

    /**
     *
     * @param url
     */
    public void changeImgURL(String url){
        path = getClass().getResource(url);
    }

    //
    /**
     * readInImg will read in the img that is given of the path
     */
    public void readInImg(){
	/*try {
	    File file = new File(path);
	    img = ImageIO.read(file);

	} catch (IOException e) {
	    e.printStackTrace();
	}*/
    }

    /*added by Erik M*/
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
