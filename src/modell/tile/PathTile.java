package modell.tile;


import modell.Position;
import java.net.MalformedURLException;

public abstract class PathTile extends Tile implements TileAction {

    private Position nextPos;

    protected PathTile(Position p, String imgPath) throws MalformedURLException {
        super(p, imgPath);

    }

    protected void setNextPos(Position posToSet){
        nextPos = posToSet;
    }

    public Position getNextPos(){
        return nextPos;
    }

    public abstract void sendToPos(Position posToSend);

}
