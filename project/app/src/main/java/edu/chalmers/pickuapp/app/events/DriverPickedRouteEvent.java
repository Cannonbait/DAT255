package edu.chalmers.pickuapp.app.events;

import edu.chalmers.pickuapp.app.model.*;

/**
 * Event that occurs when the driver clicks OK-button after selecting a route
 */
public class DriverPickedRouteEvent implements Event {

	private Date date;
	private RouteData routeData;

	/**
	 * @param routeData what route did the driver select?
	 */
	public DriverPickedRouteEvent(RouteData routeData, Date date) {
		this.date = date;
		this.routeData = routeData;
	}

    public RouteData getRouteData(){
        return routeData;
    }

	public Date getDate(){
		return date;
	}

}