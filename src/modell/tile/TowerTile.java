package modell.tile;/*
 * main.java.main.tile.TowerTile
 * 
 * Date 29/11- 2015
 * @author id12jwn
 */


import modell.Position;
import java.net.MalformedURLException;

/**
 * main.java.main.tile.TowerTile is a class that extends the main.java.main.tile.main.tile.PathTile class. The class
 * have a own image that will be used in the user interface. 
 *
 */
public class TowerTile extends Tile {

    boolean isOccupied = false;
    /**
     *  
     * @param p
     */
    public TowerTile(Position p) throws MalformedURLException {
	    super(p, "towerTile.png");
    }
    public boolean isOccupied(){
        return this.isOccupied;
    }
    public void setOccupied(Boolean isOccupied){
        this.isOccupied = isOccupied;
    }
}
