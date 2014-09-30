package edu.chalmers.pickuapp.app.events;

import edu.chalmers.pickuapp.app.model.*;

/**
 * Event that occurs when the driver clicks OK-button after selecting a route
 */
public class DriverPickedRoute implements Event {

	public RouteData routeData;

	/**
	 * @param routeData what route did the driver select?
	 */
	public DriverPickedRoute(RouteData routeData) {
		this.routeData = routeData;
	}

}