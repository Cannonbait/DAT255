package edu.chalmers.pickuapp.app.model;

import edu.chalmers.pickuapp.app.events.*;
import android.util.*;

/**
 * Created by elvirajonsson on 2014-09-29.
 */
public class Mode extends Sequence {

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

    @Override
    public Sequence getBackSequence() {
        return getSequence(ExitApp.class);
    }
}
