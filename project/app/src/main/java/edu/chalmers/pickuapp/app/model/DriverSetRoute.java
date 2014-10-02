package edu.chalmers.pickuapp.app.model;

import edu.chalmers.pickuapp.app.events.*;
import java.lang.*;

class DriverSetRoute extends Sequence {

	private Thread myThread;
	Sequence nextSequence = null;
	boolean isDone = false;

	public DriverSetRoute() {
		super();
	}

	public void processEvent(Event event) {
		
		//ON NEW MAP LOCATION SELECTED-event
		//Save google-maps location data to variable
		
		//ON OK BUTTON
		//unsleep main thread, which will resume execute()
		if(event instanceof DriverPickedRouteEvent) {
			RouteData routeData = new RouteData(((DriverPickedRouteEvent)event).routeData);

			//Set nextSequence to be matchmaker
			//Give routeData to nextSequence (matchmaker)
			

			//This sequence is done now, nothing more to do here
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