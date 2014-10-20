package edu.chalmers.pickuapp.app.events;

import edu.chalmers.pickuapp.app.model.Coordinate;
import edu.chalmers.pickuapp.app.model.Date;
import edu.chalmers.pickuapp.app.model.RouteData;

public class SetDefaultRouteDataEvent implements Event {
    
	public RouteData routeData;

    public SetDefaultRouteDataEvent(RouteData routeData) {
    	this.routeData = new RouteData(routeData);
    }
}