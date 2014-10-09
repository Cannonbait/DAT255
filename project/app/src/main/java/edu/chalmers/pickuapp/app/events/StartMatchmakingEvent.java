package edu.chalmers.pickuapp.app.events;

import edu.chalmers.pickuapp.app.model.Date;
import edu.chalmers.pickuapp.app.model.RouteData;

/**
 * Created by Malin on 2014-10-09.
 */
public class StartMatchmakingEvent implements Event {

    private Date date;
    private RouteData routeData;
    private String id;


    //Save data from where the event was thrown
    public StartMatchmakingEvent (Date date, RouteData routeData, String id) {
        this.date = date;
        this.routeData = routeData;
        setID(id);
    }


    //In order for server to reach the needed data from the event thrown.
    public RouteData getRouteData(){
        return routeData;
    }

    public Date getDate(){
        return date;
    }

    public String getID(){
        return id;
    }

    public void setID(String id){
        //sämst felhantering
        if(!(id.equals("d") || id.equals("h"))) {
            this.id = "d";
        }
    }
}
