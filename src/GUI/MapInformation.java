package GUI;

import modell.Position;
import modell.tile.Tile;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Interface which is used by the GUI classes to retrieve information from the model
 *
 * Created by id12jzn on 2015-12-09.
 */
public interface MapInformation {


    ArrayList<String> getLevelNames();

    HashMap<Position, Tile> getMap(String s);
}
