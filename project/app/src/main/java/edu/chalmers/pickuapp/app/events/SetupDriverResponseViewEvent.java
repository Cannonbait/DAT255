package edu.chalmers.pickuapp.app.events;

import edu.chalmers.pickuapp.app.model.RouteData;

/**
 * Created by tejp on 20/10/14.
 */
public class SetupDriverResponseViewEvent implements Event {
    private RouteData routeData;

    public SetupDriverResponseViewEvent(RouteData routeData) {
        this.routeData = routeData;
    }

    public RouteData getRouteData() {
        return routeData;
    }
}
