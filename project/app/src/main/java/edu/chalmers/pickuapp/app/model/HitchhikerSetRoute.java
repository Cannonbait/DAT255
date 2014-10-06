package edu.chalmers.pickuapp.app.model;

import edu.chalmers.pickuapp.app.events.*;

/**
 * Created by Malin on 2014-09-30.
 */
public class HitchhikerSetRoute extends Sequence {

    public HitchhikerSetRoute() {
        super();
    }

    public void processEvent(Event event) {
        
    }
    public Sequence getNextSequence() {
        return null;
    };
    public boolean isDone() {
        return false;
    };

}
