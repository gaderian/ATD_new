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
 * is created
 */
public abstract class PathTile extends Tile implements TileAction {

    private Position nextPos;

    /**
     * PathTile is a constructor that will send the parameters to Tile
     * that PathTile extends
     * @param p
     * @param imgPath
     * @throws MalformedURLException
     */
    protected PathTile(Position p, String imgPath)
            throws MalformedURLException {
        super(p, imgPath);

    }

    /**
     * SetNextPos is an abstarctmetod that all classes that extends
     * PathTile will override
     * @param posToSet
     */
    protected void setNextPos(Position posToSet){

        nextPos = posToSet;
    }

    /**
     * GetNextPos is a abstractmetod that all classes that extends
     * PathTile will override
     * @return
     */
    public Position getNextPos(){

        return nextPos;
    }

    /**
     * SendToPos is an abstractmetod that all classes that extends
     * pathTile will override when that type of class will be
     * created
     * @param posToSend, The posistion that the unit will be send to
     */
    public abstract void sendToPos(Position posToSend);

}
