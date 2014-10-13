package edu.chalmers.pickuapp.app.events;

import edu.chalmers.pickuapp.app.model.Date;
import edu.chalmers.pickuapp.app.model.RouteData;

/**
 * An event that is sent when a driver accepts a hitchhiker
 */
public class DriverPicksUpHitchhikerEvent implements Event{

	private RouteData routeData;
	private Date date;

	public DriverPicksUpHitchhikerEvent(RouteData routeData, Date date){
		this.routeData = routeData;
		this.date = date;
	}

	public RouteData getRouteData(){
		return routeData;
	}

	public  Date getDate(){
		return date;
	}
}