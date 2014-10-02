package edu.chalmers.pickuapp.app.model;

import edu.chalmers.pickuapp.app.events.*;

/**
 * Created by elvirajonsson on 2014-09-29.
 */
public class Mode extends Sequence {

    private Sequence nextSequence = null;

    public Mode(){
        super();
    }

    @Override
    public void processEvent(Event event) {
        
        if(event instanceof PickedDriverEvent){
            nextSequence = getSequence(DriverSetRoute.class);
        } else if(event instanceof PickedHitchhikerEvent){
            nextSequence = getSequence(HitchhikerSetRoute.class);
        }
    }
}
