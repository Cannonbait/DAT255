package edu.chalmers.pickuapp.app.model;

import edu.chalmers.pickuapp.app.events.*;

class DriverSetRoute extends Sequence {

	Sequence nextSequence = null;
	boolean isDone = false;

	public DriverSetRoute() {
		super();
	}

	public void processEvent(Event event) {
		
		//ON OK BUTTON
		//unsleep main thread, which will resume execute()
		if(event instanceof DriverPickedRouteEvent) {
			RouteData routeData = new RouteData(((DriverPickedRouteEvent)event).routeData);		
			
			//Give routeData to nextSequence (matchmaker)
			DriverMatchmaker driverMatchmaker = (DriverMatchmaker) getSequence(DriverMatchmaker.class);
			driverMatchmaker.insert(routeData);
			
			//Set nextSequence to be matchmaker
			nextSequence = driverMatchmaker;

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