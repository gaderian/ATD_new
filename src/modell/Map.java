package modell;

import modell.tile.PathTile;
import modell.tile.Start;
import modell.tile.Tile;
import modell.tile.TowerTile;
import java.util.Collection;
import java.util.HashMap;

/**
 * Class:       Map
 *
 * Author:      Erik Moström
 * cs-user:     dv14emm
 * Date:        12/2/15
 */
public class Map {
    private HashMap<Position, Tile> pathTiles;
    private HashMap<Position, Tile> towerTiles;
    private HashMap<Position, Tile> completeMap;
    private Tile startTile;
    private String name;
    private int waves;
    private int winScore;
    private int startingGold;

    /**
     * Creates a instance of Map, should only be accessed by a MapFactory
     */
    protected Map(){
        pathTiles = new HashMap<>();
        towerTiles = new HashMap<>();
        completeMap = new HashMap<>();
    }

    /**
     * Adds a tile to the map, should only be called by a MapFactory.
     * @param t the tile to add
     */
    protected void addTile(Tile t){
        if (PathTile.class.isAssignableFrom(t.getClass())) {
            pathTiles.put(t.getPosition(), t);
            if(Start.class.isAssignableFrom(t.getClass())){
                startTile = t;
            }
        } else if (TowerTile.class.isAssignableFrom(t.getClass())){
            towerTiles.put(t.getPosition(), t);
        }
        completeMap.put(t.getPosition(), t);
    }

    /**
     * Returns the tile at a specified position if there is any.
     *
     * @param p the Position which to check.
     * @return the Tile of the position
     */
    public Tile getTileAt(Position p){
        Tile tile = pathTiles.get(p);

        if (tile == null){
            tile = towerTiles.get(p);
        }

        return tile;
    }

    /**
     * Method for troubleshooting maps.
     */
    public void printMap(){
        System.out.println("map name:\t" + name);
        System.out.println("nr of waves:\t" + waves);
        System.out.println("score to win:\t" + winScore);
        System.out.println("starting gold:\t" + startingGold + "\n");
        for (int row = 1; row <= 12; row++){
            String line = "";
            for (int col = 1; col <= 12; col++){
                Tile t = getTileAt(new Position(col, row));
                if(t == null){
                    line = line + "  ";
                }else if (TowerTile.class.isAssignableFrom(t.getClass())){
                    line = line + "T ";
                }else if (PathTile.class.isAssignableFrom(t.getClass())){
                    line = line + "P ";
                }else {
                    line = line + "? ";
                }
            }
            System.out.println(line);
        }
        System.out.println();
    }

    /**
     * Returns the name of the map.
     *
     * @return the name of the map
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the number of waves that will be sent.
     *
     * @return the number of waves that will be sent.
     */
    public int getWaves() {
        return waves;
    }

    /**
     * Returns the score needed to win the level.
     *
     * @return the score needed to win the level.
     */
    public int getWinScore() {
        return winScore;
    }

    /**
     * Sets the name of the map.
     *
     * @param name the name of the map
     */
    protected void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the number of waves the level will continue for.
     *
     * @param waves the number of waves the level will be
     */
    protected void setWaves(int waves) {
        this.waves = waves;
    }

    /**
     * Sets the score needed to be achieved for completing the level.
     *
     * @param winScore the score needed to win
     */
    protected void setWinScore(int winScore) {
        this.winScore = winScore;
    }

    /**
     * Returns the amount of gold the player will start with on this level.
     *
     * @return the amount of starting gold
     */
    public int getStartingGold() {
        return startingGold;
    }

    /**
     * Sets the amount of gold the player will start with on this level.
     *
     * @param startingGold the desired amount of starting gold
     */
    public void setStartingGold(int startingGold) {
        this.startingGold = startingGold;
    }

    /**
     * Returns all TowerTiles of this map
     *
     * @return a Collection containing all the tower tiles
     */
    public Collection<Tile> getTowerTiles(){
        return towerTiles.values();
    }

    /**
     * Returns the entire map with all tiles added to the map.
     *
     * @return a HashMap containing all the tiles of the map
     */
    public HashMap<Position, Tile> getCompleteMap() {
        return completeMap;
    }

    /**
     * Returns the Tile of the map specified as the starting tile.
     *
     * @return the start tile of the map
     */
    public Tile getStartTile() {
        return startTile;
    }

    /**
     * A swap can only be made if the new Tile has the same position as another
     * already in the map. When swapping a tile the new will have some basic
     * information added to it from the old, if they are of the same type.
     *
     * @param t the tile to swap with one already in the map
     */
    public void swapTile(Tile t){
        Position tilePos = t.getPosition();
        Tile oldTile = getTileAt(tilePos);

        /*end if there are no old tile*/
        if (oldTile == null){
            return;
        }

        /* If both of the tiles are path tiles, give the new tile the position
         * where it should send units to */
        if(PathTile.class.isAssignableFrom(t.getClass()) &&
                PathTile.class.isAssignableFrom(oldTile.getClass())){

            PathTile path = (PathTile) t;
            PathTile oldPath = (PathTile) oldTile;

            path.sendToPos(oldPath.getNextPos());

            pathTiles.remove(tilePos);
            pathTiles.put(tilePos, t);
        }

        if (TowerTile.class.isAssignableFrom(t.getClass())
                && PathTile.class.isAssignableFrom(oldTile.getClass())){

            towerTiles.remove(tilePos);
            towerTiles.put(tilePos, t);
        }

        completeMap.remove(tilePos);
        completeMap.put(tilePos, t);

    }
}
