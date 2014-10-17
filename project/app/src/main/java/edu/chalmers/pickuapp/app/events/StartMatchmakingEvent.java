package edu.chalmers.pickuapp.app.events;

import edu.chalmers.pickuapp.app.model.Date;
import edu.chalmers.pickuapp.app.model.RouteData;

/**
 * Created by Malin on 2014-10-09.
 */
public class StartMatchmakingEvent implements Event {

    private RouteData routeData;

    //Save data from where the event was thrown
    public StartMatchmakingEvent (RouteData routeData) {
        this.routeData = routeData;
    }


    //In order for server to reach the needed data from the event thrown.
    public RouteData getRouteData(){
        return routeData;
    }

}
