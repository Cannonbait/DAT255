package edu.chalmers.pickuapp.app.model;

import edu.chalmers.pickuapp.app.events.*;

/**
 * Created by elvirajonsson on 2014-09-29.
 */
public class Mode extends Sequence {

    public Mode(){

    }
    @Override
    public void onRegister() {
        EventBus.INSTANCE.reportEvent(new DrawMode());
    }

    @Override
    public Sequence execute() {
        return null;
    }

    @Override
    public void onUnregister() {

    }

    @Override
    public void onEvent(Event e) {
       // if (e.getClass() == someEventClass){ //if pickedDriver
            //wake up thread
       // } else if (e.getClass() == someEventClass){ //if picked HH
            //do shit
        //}
    }
}
