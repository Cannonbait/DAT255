package edu.chalmers.pickuapp.app.model;

import edu.chalmers.pickuapp.app.events.*;
import java.lang.*;

class DriverSetRoute extends Sequence {
	
	public DriverSetRoute() {
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
		if(e instanceof DriverPickedRoute) {
			RouteData routeData = new RouteData(((DriverPickedRoute)e).routeData);

			//Give routeData to next sequence (matchmaker)
		}
	}
}