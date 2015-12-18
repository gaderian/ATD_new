package modell; /**
 * Class: Game
 *
 * Author: Isak Hjelt
 *
 * cs-user: dv14iht
 *
 * Date: 2015-11-30
 */

import modell.tile.TCross;
import modell.tile.Tile;
import modell.tile.TileAction;
import modell.tile.TowerTile;
import modell.tower.AirTower;
import modell.tower.GroundTower;
import modell.tower.Tower;
import modell.unit.Unit;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;


public class Game {

    private int timeOfGame = 0;
    private static final int nrOfTowers = 10;
    private static final int tileSize = 54;
    private int nextId = 0;
    private User user;
    private Map map;
    private List<Unit> units;
    private List<Tower> towers;
    private List<Unit> clickUnits;
    private CurrentGraphicState graphicState;
    private int unitsReachedGoal=0;
    private Shop gameShop;

    public Game(Map mapName) {

        this.map = mapName;
        this.units = new LinkedList();
        this.towers = new LinkedList();
        this.clickUnits = new LinkedList();
        this.graphicState = new CurrentGraphicState();
        this.user = new User(this.map.getStartingGold(), 1);
        this.gameShop = new Shop(this.user, this);
        makeTowers();
    }

    public void update() {
        /*Update the units*/
        updateUnits();
        /*Update the Towers*/
        updateTowers();
        /*Generate graphic info about the user*/
        this.graphicState.addGraphicEvent(this.user.generateGraphicEvent());

        graphicState.gameTick(timeOfGame);
        timeOfGame++;

    }

    private void updateUnits() {
        synchronized (gameShop) {

            ListIterator itrUnits = units.listIterator();
            Unit currentUnit;
            TileAction currentTile;
            GraphicEvent tempEvent;
            Position tempTilePos;

            while (itrUnits.hasNext()) {
                currentUnit = (Unit) itrUnits.next();
            /*Remove the unit if it has reached goal*/
                if (currentUnit.hasReachedGoal()) {
                    tempEvent = currentUnit.generateGraphicEvent();
                    this.graphicState.removeGraphicEvent(tempEvent);
                    itrUnits.remove();
                    this.unitsReachedGoal++;
                    this.user.setPoints(this.user.getScore()+1);
                }
            /*Remove the unit if it's dead*/
                else if (!currentUnit.isAlive()) {
                    tempEvent = currentUnit.generateGraphicEvent();
                    this.graphicState.removeGraphicEvent(tempEvent);
                    itrUnits.remove();
                } else if (currentUnit.move()) {
                    tempEvent = currentUnit.generateGraphicEvent();
                    this.graphicState.addGraphicEvent(tempEvent);
                    if (currentUnit.isInMiddleOfTile()) {
                        tempTilePos = PositionConverter.unitPosConverter(currentUnit.getPosition());
                        currentTile = (TileAction) map.getTileAt(tempTilePos);
                        currentTile.landOn(currentUnit);
                        /*Give the user more money*/
                        this.user.increaseCredits(user.getIncome());
                    }
                }

            }
        }
    }

    private void updateTowers() {
        ListIterator itrTowers = towers.listIterator();
        Tower currentTower;
        Unit currentUnit;

        while (itrTowers.hasNext()) {
            currentTower = (Tower) itrTowers.next();
            GraphicEvent e = null;
            /*Does the tower have a target?*/
            if (currentTower.hasValidTarget()) {
                /*Call the attack mathod*/
                e = currentTower.attack(timeOfGame);

            } else {
                /*Try to find a target for the tower*/
                ListIterator itrUnits = units.listIterator();
                while (itrUnits.hasNext()) {
                    currentUnit = (Unit) itrUnits.next();
                    if(currentUnit.isAlive() && currentTower.withinRange(currentUnit)){
                        currentTower.setTarget(currentUnit);
                        e = currentTower.attack(timeOfGame);
                        break;
                    }
                }
            }

            if (e != null) {
                graphicState.addGraphicEvent(e);
            }
        }
    }
    public boolean isWon(){
        return this.unitsReachedGoal >= this.map.getWinScore();
    }
    public void clickAtPos(Position clickPos){
        Position p = PositionConverter.unitPosConverter(clickPos);
        Tile newTile = null;

        Iterator unitItr = clickUnits.listIterator();
        Unit currentUnit;
        while(unitItr.hasNext()){
            currentUnit = (Unit)unitItr.next();
            /*Remove if dead*/
            if(!currentUnit.isAlive()){
                unitItr.remove();
            }else if((clickPos.getDistance(currentUnit.getPosition()) <= this.tileSize)){
                newTile = currentUnit.click();
                if(newTile == null){
                    unitItr.remove();
                }else{
                    map.swapTile(newTile);
                    return;
                }
            }
        }
        /*Else see if user clicked on TCross*/
        Tile t = map.getTileAt(p);

        if ( t!= null && TCross.class.isAssignableFrom(t.getClass()) ){
            TCross tCross = (TCross) t;
            tCross.changeDirection();
        }
    }

    public boolean isLoss(){
        return ((this.units.size() == 0) && (this.user.getCredits() < 30));
    }

    public void addUnit(Unit unit){
        TileAction currentTile;
        Position tempTilePos;

        /*Set the next position of the unit to the position of the start tile*/
        Position posToSet = (map.getStartTile().getPosition());
        unit.setCurrentPosition(posToSet);
        unit.setNextTilePos(posToSet);
        /*Call landon for the first tile the unit spawns on*/
        tempTilePos = PositionConverter.unitPosConverter(unit.getPosition());
        currentTile = (TileAction) map.getTileAt(tempTilePos);
        currentTile.landOn(unit);

        this.units.add(unit);
        /*If the unit it clickable add it to the clickable list*/
        if(unit.isClickable()){
            this.clickUnits.add(unit);
        }
    }

    public Shop getShop(){
        return this.gameShop;
    }

    private void makeTowers(){
        int nrOfTowerTiles = map.getTowerTiles().size();
        Iterator it = map.getTowerTiles().iterator();
        LinkedList <TowerTile>towerTileList = new LinkedList();
        int random;
        Tower tower;
        Position tempTilePos;
        TowerTile tempTowerTile;
        int towersToPlace = this.nrOfTowers;
        int placedTowers=0;

        if(nrOfTowerTiles == 0){
            throw new IllegalStateException("There are no towerTiles!");
        }
        /*place towers in a list*/
        while(it.hasNext()){
            towerTileList.add((TowerTile) it.next());
        }
        /*Check to see if there are to few tiles for the towers*/
        if(towersToPlace > nrOfTowerTiles){
            /*If there is to few tiles, place as many towers as possible*/
            towersToPlace = nrOfTowerTiles;
        }
        /*Place all towers on random tiles*/
        while(placedTowers < towersToPlace){
            random = (int)(Math.random() * (nrOfTowerTiles));
            /*Add a tower and set the position of the tower to a random towerTiles position*/

            tempTilePos = towerTileList.get(random).getPosition();
            tempTowerTile = (TowerTile) map.getTileAt(tempTilePos);
            if(!tempTowerTile.isOccupied()){

                /*Place every other tower as Air tower and Ground tower*/
                if(placedTowers % 2 == 0){
                    tower = new GroundTower(PositionConverter.tilePosConverter(tempTilePos),getNextObjectId());
                }else{
                    tower = new AirTower(PositionConverter.tilePosConverter(tempTilePos),getNextObjectId());
                    //tower = new GroundTower(modell.PositionConverter.tilePosConverter(tempTilePos),getNextObjectId());
                }

                this.towers.add(tower);
                tempTowerTile.setOccupied(true);
                placedTowers++;

                /*Generate a graphic event when the tower is added to the game*/
                GraphicEvent tempEvent = tower.generateGraphicEvent();
                this.graphicState.addGraphicEvent(tempEvent);

            }
        }
    }

    public CurrentGraphicState getGraphicState(){
        return this.graphicState;
    }

    /**
     * Get the next id to give to a object in the game.
     *
     * @return
     */
    public int getNextObjectId(){
        int ret = this.nextId;
        this.nextId++;
        return ret;
    }

    public User getUser(){
        return user;
    }
}