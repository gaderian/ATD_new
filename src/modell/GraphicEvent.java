package modell;

import java.awt.image.BufferedImage;

/**
 * Class: GraphicEvent
 *
 * Author: Isak Hjelt
 *
 * cs-user: dv14iht
 *
 * Date: 2015-12-07
 */

public class GraphicEvent {

    private Position pos;
    private BufferedImage image = null;
    boolean isLaserEvent;
    private int id;
    private int timeStamp;
    private int visibilityTime = -1;

    public GraphicEvent(int id, Position pos, BufferedImage img) {
        this.isLaserEvent = false;
        this.pos = pos;
        this.id = id;
        this.image = img;
    }

    /**
     * Set a time the Graphic object will be visible. When the object has
     * lived for the specified time (measured in game ticks) it will be removed. If this time is not set
     * the object will live until it is removed.
     *
     * @param timestamp the time of the events creation
     * @param visibilityTime the lifespan of the event.
     */
    public void setVisibilityTime(int timestamp, int visibilityTime) {
        this.visibilityTime = visibilityTime;
        this.timeStamp = timestamp;
    }

    /**
     * Check if this event should be removed.
     *
     * @param currentTime the current time (in ticks).
     * @return returns true if the event should be removed and false if not.
     */
    public boolean shouldBeRemoved(int currentTime) {
        return ((currentTime)-(this.timeStamp)) >= this.visibilityTime;
    }

    /**
     * 
     * @return true if this object has a set lifetime false if not.
     */
    public boolean isLifeTimeObject(){
        return this.visibilityTime >= 0;
    }

    /**
     *
     * @return returns the id of the event.
     */
    public int getId() {
        return this.id;
    }

    /**
     *
     * @return returns the position the event is registered on.
     */
    public Position getPos(){
        return this.pos;
    }

    /**
     *
     * @return returns the image of the event.
     */
    public BufferedImage getImage() {
        return this.image;
    }
}
