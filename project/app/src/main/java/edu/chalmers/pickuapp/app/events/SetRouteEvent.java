package edu.chalmers.pickuapp.app.events;

import edu.chalmers.pickuapp.app.model.Coordinate;
import edu.chalmers.pickuapp.app.model.Date;
import edu.chalmers.pickuapp.app.model.RouteData;

/**
 * Created by cannonbait on 2014-10-13.
 */
public class SetRouteEvent implements Event{
    private final RouteData route;
    public SetRouteEvent(RouteData route){
        this.route = route;
    }
    public RouteData getRoute(){ return route; }


}
