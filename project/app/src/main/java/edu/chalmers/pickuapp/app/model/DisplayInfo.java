package edu.chalmers.pickuapp.app.model;

import edu.chalmers.pickuapp.app.events.Event;

/**
 * Created by elvirajonsson on 2014-10-09.
 */
public class DisplayInfo extends Sequence {
    
    @Override
    public void processEvent(Event event) {

    }

    @Override
    public Sequence getNextSequence() {
        return null;
    }
}
