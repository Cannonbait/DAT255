package edu.chalmers.pickuapp.app.model;

import android.util.Log;
import edu.chalmers.pickuapp.app.events.*;

public class DriverSetRoute extends Sequence {

    private RouteData route;

	public DriverSetRoute() {
		super();
	}

	public void processEvent(Event event) {
		
		if (event instanceof SetRouteEvent){
            route = new RouteData(((SetRouteEvent) event).getRoute());
        }
	}

    @Override
    public Sequence getBackSequence() {
        return getSequence(Mode.class);
    }

}