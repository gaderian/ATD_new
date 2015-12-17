package modell;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Stack;

/**
 *
 * @author guldsmurfen
 */
public class CurrentGraphicState {

    private HashMap allGraphicEvents;
    private LinkedList <GraphicEvent>eventsWithLifetime;

    public CurrentGraphicState() {
        this.allGraphicEvents = new HashMap();
        this.eventsWithLifetime = new LinkedList();
    }
    /**
     * This method needs to be called for every time tick in the game to 
     * remove objects with no lifetime left.
     * 
     * @param currentTime the current timetick
     */
    public void gameTick(int currentTime){
         ListIterator itrUnits = this.eventsWithLifetime.listIterator();
         GraphicEvent currentEvent;

         while(itrUnits.hasNext()){
             currentEvent = (GraphicEvent) itrUnits.next();
             /* If the lifetime of the object has run out remove 
             the object from the list and from the HashMap*/
             if(currentEvent.shouldBeRemoved(currentTime)){
                 itrUnits.remove();
                 this.allGraphicEvents.remove(currentEvent.getId());
             }
         }
    }

    public void addGraphicEvent(GraphicEvent e) {
        this.allGraphicEvents.put(e.getId(), e);
        if(e.isLifeTimeObject()){
            this.eventsWithLifetime.add(e);
        }
    }

    public void removeGraphicEvent(GraphicEvent e){
        this.allGraphicEvents.remove(e.getId());
    }

    /**
     *
     * @return returns the most recent version of all events.
     */
    public Stack getCurrentGraphicState() {
        Stack s = new Stack();
        Iterator itr;
        GraphicEvent currentEvent;
        itr = allGraphicEvents.values().iterator();

        while (itr.hasNext()) {
            currentEvent = (GraphicEvent) itr.next();
            s.push(currentEvent);
        }
        return s;
    }
}
