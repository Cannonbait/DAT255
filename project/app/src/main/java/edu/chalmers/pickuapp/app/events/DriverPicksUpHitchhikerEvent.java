package edu.chalmers.pickuapp.app.events;

import edu.chalmers.pickuapp.app.model.Date;
import edu.chalmers.pickuapp.app.model.RouteData;

/**
 * An event that is sent when a driver accepts a hitchhiker
 */
public class DriverPicksUpHitchhikerEvent implements Event{

	private RouteData routeData;

	public DriverPicksUpHitchhikerEvent(RouteData routeData){
		this.routeData = routeData;
	}

	public RouteData getRouteData(){
		return routeData;
	}

}