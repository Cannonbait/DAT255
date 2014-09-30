package edu.chalmers.pickuapp.app.model;

import edu.chalmers.pickuapp.app.events.*;

/**
 * Created by Malin on 2014-09-30.
 */
public class HitchhikerSetRoute extends Sequence {

    public HitchhikerSetRoute() {
        super();
    }

    public void onRegister() {

    }
    public Sequence execute() {
        //Sleep until ok-button
        //Then return appropiate sequence, for now return MockSequence
        return getSequence(MockSequence.class);
    }
    public void onUnregister() {

    }

    public void onEvent(Event e) {

        //ON NEW MAP LOCATION SELECTED-event
        //Save google-maps location data to variable

        //ON OK BUTTON
        //unsleep main thread, which will resume execute()
    }

}
