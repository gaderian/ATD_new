/**
 * Class:       tower
 *
 * Author:      Erik Moström
 * cs-user:     dv14emm
 * Date:        2015-11-26
 */

package modell.tower;

import javax.imageio.ImageIO;
import modell.GraphicEvent;
import modell.Position;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import modell.unit.Unit;

public abstract class Tower {

    private BufferedImage image;
    private int range;
    private int speed;
    private int damage;
    private boolean ground;
    private boolean air;
    private Unit target;
    private Position pos;
    private int timeOfLastAttack;
    protected int id;

    /**
     * Creates a instance of a Tower
     *
     * @param range the range of the tower
     * @param speed the firing frequency of the tower
     * @param damage the damage dealt to a unit hit by the tower
     * @param air if the tower can shoot at flying units
     * @param ground if the tower can shoot at ground units
     * @param pos the position of the tower
     */
    protected Tower(int range, int speed, int damage, boolean air,
                    boolean ground, Position pos){
        this.range = range;
        this.speed = speed;
        this.damage = damage;
        this.air = air;
        this.ground = ground;
        this.pos = pos;

        timeOfLastAttack = 0;
        target = null;

    }

    /**
     * Determines if a unit is within range of the tower. A unit is considered
     * to be out of range if it is flying and the tower can only shoot at ground
     * units.
     *
     * @param newTarget the unit to check if within range.
     * @return true if the unit is within range, else false.
     */
    public boolean withinRange(Unit newTarget){
        if(newTarget.isFlying() && !air){
            return false;
        } else if (!newTarget.isFlying() && !ground){
            return false;
        }

        return (pos.getDistance(newTarget.getPosition()) <= range);
    }

    /**
     * Sets a new target for this tower to shoot at
     *
     * @param newTarget the new target
     */
    public void setTarget(Unit newTarget){
        target = newTarget;
    }

    /**
     * Will deal the towers damage to the towers current target.
     *
     * @param time the time of the game given in the number of update cycles
     *             performed by the game.
     * @return a GraphicEvent representing a laser from the tower to the
     * targeted unit
     */
    public GraphicEvent attack(int time) {
        if(time - timeOfLastAttack >= speed){
            target.takeDamage(damage);
            timeOfLastAttack = time;

            BufferedImage laser = createLaserImage();
            int laserID = (id+1)*-1;
            GraphicEvent event = new GraphicEvent(laserID, getLaserPosition(), laser);
            event.setVisibilityTime(time, (speed/3));

            return event;
        }
        return null;
    }

    /**
     * Calculate the center position of the laser image
     *
     * @return the center position of the image
     */
    private Position getLaserPosition() {
        int towerX = this.getPosition().getX();
        int towerY = this.getPosition().getY();
        int targetX = target.getPosition().getX();
        int targetY = target.getPosition().getY();

        int x = towerX - ((towerX - targetX)/2 );
        int y = towerY - ((towerY - targetY)/2 );

        return new Position(x, y);
    }

    /**
     * Creates a buffered image representing a laser
     *
     * @return a buffered image containing a red line representing a laser.
     */
    private BufferedImage createLaserImage() {
        Position towerPos = this.getPosition();
        Position targetPos = target.getPosition();

        /*Calculate the width and height of the image*/
        int width = (towerPos.getX() - targetPos.getX());
        int height = (towerPos.getY() - targetPos.getY());
        if (height == 0) height++;
        if (width == 0) width ++;
        if (width < 0){
            width = width * -1;
        }
        if (height < 0){
            height = height * -1;
        }

        /*Calculate the coordinates for the lasers start and end points*/
        int x, dx, y, dy;

        if (towerPos.getX() >= targetPos.getX()){
            x = width;
            dx = 0;
        } else {
            x = 0;
            dx = width;
        }

        if (towerPos.getY() >= targetPos.getY()){
            y = height;
            dy = 0;
        } else {
            y = 0;
            dy = height;
        }

        /*Make the image*/
        BufferedImage laser = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = laser.createGraphics();
        g.setColor(new Color(255, 0, 0));
        g.setStroke(new BasicStroke(2));
        g.drawLine(x, y, dx, dy);

        return laser;
    }

    /**
     * Will load the image of the tower from disc and save it for later use.
     *
     * @param path an URL to the image to be loaded
     */
    protected void loadImage(URL path){
        try {
            image = ImageIO.read(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Will check if the towers current target is valid. If the target is dead
     * or out of range the target is invalid since the tower cannot shoot at it.
     *
     * @return true if current target is valid, else false.
     */
    public boolean hasValidTarget(){
        if (target != null) {
            return target.isAlive() && withinRange(target);
        }
        return false;
    }

    /**
     * Returns the position of the tower.
     *
     * @return the position of the tower.
     */
    public Position getPosition() {
        return pos;
    }

    /**
     * Will generate a GraphicEvent with the image of the tower.
     *
     * @return a GraphicEvent with the towers position and image.
     */
    public GraphicEvent generateGraphicEvent() {

        return (new GraphicEvent(this.id, this.pos, image));
    }
}
