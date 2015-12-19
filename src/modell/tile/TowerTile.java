package modell.tile;/*
 * TowerTile
 * 
 * Date 29/11- 2015
 * @author id12jwn
 */


import modell.Position;
import java.net.MalformedURLException;

/**
 * TowerTile is a class that extends the PathTile class. The class
 * have a own image that will be used in the user interface.
 */
public class TowerTile extends Tile {

    boolean isOccupied = false;

    /**
     *  TowerTile is a conctructor that eill set the positon of the
     *  tile and also the image path to its image
     * @param p
     */
    public TowerTile(Position p) throws MalformedURLException {
	    super(p, "towerTile.png");
    }

    /**
     * isOccupied is a method that check if the position has a tower on
     * that position or not.
     *
     * @return a boolean variabel
     */
    public boolean isOccupied(){
        return this.isOccupied;
    }

    /**
     * setOccupied is a method that will set that the positon of the tile
     * is taken so the compuputer knows that and does not set another
     * tower there.
     * @param isOccupied
     */
    public void setOccupied(Boolean isOccupied){
        this.isOccupied = isOccupied;
    }
}
