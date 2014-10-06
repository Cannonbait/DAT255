package edu.chalmers.pickuapp.app.model;

import edu.chalmers.pickuapp.app.events.*;
import android.util.*;

/**
 * Created by elvirajonsson on 2014-09-29.
 */
public class Mode extends Sequence {

    private Sequence nextSequence = null;
    private boolean isDone = false;

    public Mode(){
        super();
    }

    @Override
    public void processEvent(Event event) {
        
        if(event instanceof PickedDriverEvent){
            nextSequence = getSequence(DriverSetRoute.class);
            isDone = true;
        } else if(event instanceof PickedHitchhikerEvent){
            nextSequence = getSequence(HitchhikerSetRoute.class);
            isDone = true;
        }
    }

    public Sequence getNextSequence() {
        return nextSequence;
    };
    public boolean isDone() {
        return isDone;
    };
}
