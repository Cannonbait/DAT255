package edu.chalmers.pickuapp.app.events;

import edu.chalmers.pickuapp.app.model.Coordinate;
import edu.chalmers.pickuapp.app.model.Date;
import edu.chalmers.pickuapp.app.model.RouteData;

/**
 * Created by elvirajonsson on 2014-10-10.
 */
//This event is to be trown by the model containing the data on where and when the driver and HH should meet up
public class MeetupEvent implements Event {

	private RouteData routeData;


    public MeetupEvent(RouteData routeData){
        this.routeData = routeData;
    }

    public RouteData getRouteData(){
		return routeData;
    }

}