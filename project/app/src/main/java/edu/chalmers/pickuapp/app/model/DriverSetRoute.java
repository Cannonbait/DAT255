package edu.chalmers.pickuapp.app.model;

import android.util.Log;
import edu.chalmers.pickuapp.app.events.*;
import java.util.*;

public class DriverSetRoute extends Sequence {

    private RouteData route;

	public DriverSetRoute() {
		super();

        Calendar cal = Calendar.getInstance();
        route = new RouteData(
            new Coordinate(0, 0),
            new Coordinate(0, 0), 
            new Date(
                cal.get(Calendar.YEAR), 
                cal.get(Calendar.MONTH), 
                cal.get(Calendar.DAY_OF_MONTH), 
                cal.get(Calendar.HOUR_OF_DAY), 
                cal.get(Calendar.MINUTE),
                cal.get(Calendar.SECOND)),
            new Date(
                0,
                0,
                0,
                0,
                0,
                0));
	}

    @Override
    public void onStart() {
        super.onStart();
        EventBus.INSTANCE.reportEvent(new SetDefaultRouteDataEvent(route));
    }

	public void processEvent(Event event) {
		
		if (event instanceof SetRouteEvent){
            route = new RouteData(((SetRouteEvent) event).getRoute());
            nextSequence = getSequence(DriverMatchmaker.class);
            ((DriverMatchmaker)nextSequence).setRouteData(route);
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