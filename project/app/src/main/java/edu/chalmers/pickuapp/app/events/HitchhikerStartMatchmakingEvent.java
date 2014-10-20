package edu.chalmers.pickuapp.app.events;

import edu.chalmers.pickuapp.app.model.RouteData;

/**
 * Created by cannonbait on 2014-10-20.
 */
public class HitchhikerStartMatchmakingEvent implements Event{

    private RouteData routeData;

    //Save data from where the event was thrown
    public HitchhikerStartMatchmakingEvent(RouteData routeData) {
        this.routeData = routeData;
    }


    //In order for server to reach the needed data from the event thrown.
    public RouteData getRouteData(){
        return routeData;
    }

}
