package edu.chalmers.pickuapp.app.events;

import edu.chalmers.pickuapp.app.events.*;
import edu.chalmers.pickuapp.app.model.*;


/**
 * Event that is sent from server whenever match is found
 */
public class DriverMatchFoundEvent implements Event {

	//Save whatever data required for displaying info about match
	//I.E: Time, location, Name of driver and hitchhiker etcetc
	
	public RouteData routeData;

	//Pass that data to this constructor
	public DriverMatchFoundEvent(RouteData routeData) {
		this.routeData = new RouteData(routeData);
	}

}