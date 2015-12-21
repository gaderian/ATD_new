package modell.tile;
/*
 * PathTile
 *
 * Date 29/11- 2015
 * @author id12jwn
 */
import modell.Position;
import java.net.MalformedURLException;

/**
 * PathTile is a abstract class that will define what type of tile that
 * is created.
 */
public abstract class PathTile extends Tile implements TileAction {

    private Position nextPos;

    /**
     * PathTile is a constructor that will send the parameters to Tile
     * that PathTile extends
     *
     * @param p the position of the tile
     * @param imgPath the path to the tiles image
     * @throws MalformedURLException if something goes wrong with the image url
     */
    protected PathTile(Position p, String imgPath)
            throws MalformedURLException {
        super(p, imgPath);

    }

    /**
     * SetNextPos is an abstarct method that all classes that extends
     * PathTile will override
     *
     * @param posToSet the position which the tile will give to any units
     *                 stepping on it
     */
    protected void setNextPos(Position posToSet){

        nextPos = posToSet;
    }

    /**
     * GetNextPos is a abstract method that all classes that extends
     * PathTile will override
     *
     * @return the position of the tile
     */
    public Position getNextPos(){

        return nextPos;
    }

    /**
     * SendToPos is an abstract method that all classes that extends
     * pathTile will override when that type of class will be
     * created
     * @param posToSend, The position that the unit will be send to
     */
    public abstract void sendToPos(Position posToSend);

}
