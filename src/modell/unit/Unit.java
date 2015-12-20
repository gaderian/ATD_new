package modell.unit;

/**
 * Class: Unit
 *
 * Author: Isak Hjelt
 *
 * cs-user: dv14iht
 *
 * Date: 2015-11-26
 */


import javax.imageio.ImageIO;
import modell.GraphicEvent;
import modell.Position;
import modell.PositionConverter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import modell.tile.Tile;

public abstract class Unit {
    /*Static variables*/

    protected static final int MAXSPEED = 50;
    private static final int tileSize = 54;
    private static final int dmgGraphicDelay = 500;
    protected String name;
    protected Position pos;
    protected int id;
    private Position nextTilePos;
    protected URL imagePath;
    protected BufferedImage image;
    protected int health;
    private double fullHealth = -1;
    protected int price;
    protected int speed;
    protected boolean isClickableUnit = false;
    protected Boolean flying;
    protected Boolean teleporter=false;
    private int timeLived = 0;
    private boolean hasReachedGoal = false;
    private int lastDmg = 0;
    private long lastTimeDmg = 0;

    /**
     * Initializes a new {@code Unit} which is being put on specified position.
     *
     * @param pos the position
     *
     */
    public Unit(Position pos, int id) {
        this.pos = pos;
        this.nextTilePos = pos;
        this.health = 10;
        this.price = 10;
        this.speed = 45;
        this.name = "UnNamed";
        this.flying = false;
        this.id = id;

    }

    /**
     * Moves to the nextPos
     *
     * @return returns true if the unit moved.
     */
    public boolean move() {
        boolean ret = false;

        /*Move more frequently depending on the speed*/
        if (((timeLived) % ((MAXSPEED + 1) - speed)) == 0) {
            this.pos = getNextPos();
            ret = true;
        }
        timeLived++;
        return ret;
    }

    public boolean isFlying() {
        return flying;
    }

    public Position getPosition() {
        return this.pos;
    }

    public void setCurrentPosition(Position pos) {
        this.pos = PositionConverter.tilePosConverter(pos);
    }

    public void setNextTilePos(Position nextTilePos) {
        this.nextTilePos = nextTilePos;
    }

    public void takeDamage(int dmg) {
        if(fullHealth == -1){
            this.fullHealth = health;
        }
        this.health -= dmg;
        this.lastDmg = dmg;
        this.lastTimeDmg = System.currentTimeMillis();
    }

    public boolean isAlive() {
        return (this.health > 0);
    }

    public void setHasReachedGoal(boolean b) {
        this.hasReachedGoal = b;
        this.health = 0;
    }

    public boolean hasReachedGoal() {
        return this.hasReachedGoal;
    }


    /**
     *
     * @return the position one step closer to the middle of the next tile
     */
    private Position getNextPos() {
        int currentX = this.pos.getX();
        int currentY = this.pos.getY();
        int tileX = this.nextTilePos.getX();
        int tileY = this.nextTilePos.getY();
        int tileMiddlePosX = (((tileX) * tileSize-1) + (tileSize / 2));
        int tileMiddlePosY = (((tileY) * tileSize-1) + (tileSize / 2));

        if (currentX < tileMiddlePosX) {
            currentX++;
        } else if (currentX > tileMiddlePosX) {
            currentX--;
        }
        if (currentY < tileMiddlePosY) {
            currentY++;
        } else if (currentY > tileMiddlePosY) {
            currentY--;
        }
        return new Position(currentX, currentY);
    }

    /**
     *
     * @return true if the unit is on the position in the middle of the tile,
     * false if not.
     */
    public boolean isInMiddleOfTile() {
        int currentX = this.pos.getX();
        int currentY = this.pos.getY();
        int tileX = this.nextTilePos.getX();
        int tileY = this.nextTilePos.getY();
        int tileMiddlePosX = (((tileX) * tileSize-1) + (tileSize / 2));
        int tileMiddlePosY = (((tileY) * tileSize-1) + (tileSize / 2));

        return (currentX == tileMiddlePosX) && (currentY == tileMiddlePosY);
    }



    public GraphicEvent generateGraphicEvent() {
        BufferedImage whole = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
        BufferedImage sprite = image;

        Graphics2D g = whole.createGraphics();
        g.drawImage(sprite, 0, 0, null);

        if ((System.currentTimeMillis() - lastTimeDmg) < dmgGraphicDelay) {
            double length = 40;
            int greenRect = (int)((health/fullHealth) * length);

            g.setColor(new Color(255, 0, 27));
            g.setStroke(new BasicStroke(2));
            g.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, 12));
            g.drawString(String.valueOf("-" + String.valueOf(this.lastDmg)), (sprite.getWidth() / 3), (sprite.getHeight() / 4));
            g.fillRect(0,40,40,4);
            g.setColor(new Color(59, 255, 0));
            g.fillRect(0,40,greenRect,4);

        }
        return (new GraphicEvent(this.id, this.pos, whole));
    }
    public Tile click(){
        return null;
    }
    public boolean isClickable(){
        return this.isClickableUnit;
    }
}
