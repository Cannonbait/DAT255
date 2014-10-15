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
            nextSequence = getSequence(DriverMatchmaker.class);
        }
	}

    @Override
    public Sequence getBackSequence() {
        return getSequence(Mode.class);
    }

    @Override
    public boolean isDone(){
        return nextSequence != null;
    }

}