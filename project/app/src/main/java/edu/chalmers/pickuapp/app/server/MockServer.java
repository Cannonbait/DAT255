package edu.chalmers.pickuapp.app.server;

import edu.chalmers.pickuapp.app.events.Event;
import edu.chalmers.pickuapp.app.events.EventBus;
import edu.chalmers.pickuapp.app.events.EventListener;

/**
 * Created by Malin on 2014-10-06.
 */
public class MockServer implements EventListener {


    public MockServer(){
        EventBus.INSTANCE.registerListener(this);
    }


    @Override
    public void onEvent(Event e) {

    }
}
