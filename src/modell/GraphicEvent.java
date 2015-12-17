package modell; /**
 * Class: GraphicEvent
 *
 * Author: Isak Hjelt
 *
 * cs-user: dv14iht
 *
 * Date: 2015-12-07
 */

import java.awt.image.BufferedImage;

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
    /*Set i a time the Graphic object will be visible. When the object has 
     lived for the specified time (measured in game ticks) it will be removed. If this time is not set 
     the object will live untill it is removed*/

    public void setVisibilityTime(int timestamp, int visibilityTime) {
        this.visibilityTime = visibilityTime;
        this.timeStamp = timestamp;
    }

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

    public int getId() {
        return this.id;
    }

    public boolean isLaserEvent() {
        return isLaserEvent;
    }
    
    public Position getPos(){
        return this.pos;
    }

    public BufferedImage getImage() {
        return this.image;
    }
}
