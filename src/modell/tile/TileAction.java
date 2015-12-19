package modell.tile;
/**
 * TileAction
 *
 * Date 29/11- 2015
 * @author id12jwn
 */

import modell.unit.Unit;

/**
 * TileAction is a interface that the tiles will use
 */
public interface TileAction {

    /**
     * landOn is a method that the tiles will use when units are
     * steping on them
     *
     * @param unit, the unit that will step on the tile
     */
    public void landOn(Unit unit);

}
