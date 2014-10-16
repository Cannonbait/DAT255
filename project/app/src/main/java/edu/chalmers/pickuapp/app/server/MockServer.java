package edu.chalmers.pickuapp.app.server;

import android.util.Log;
import edu.chalmers.pickuapp.app.events.*;
import edu.chalmers.pickuapp.app.model.Coordinate;
import edu.chalmers.pickuapp.app.model.Date;
import edu.chalmers.pickuapp.app.model.RouteData;

/**
 * Created by Malin on 2014-10-06.
 */
public class MockServer implements EventListener, Runnable {

    private boolean doMatch,matchFound;

    public MockServer(){
        EventBus.INSTANCE.registerListener(this);
     }


    @Override
    public void onEvent(Event event) {

        if (event instanceof StartMatchmakingEvent) {
            StartMatchmakingEvent sme = (StartMatchmakingEvent)event;
            //write driver's data to a txt file. then start matchmaking from the hitchhiker txt file
           //saveToServer(sme.getDate(), sme.getRouteData(), sme.getID());

            match(sme.getRouteData(),sme.getDate(),sme.getID());
            doMatch = true;
        }

        if (event instanceof AbortMatchmakingEvent) {
            doMatch = false;
        }
    }



    //n0000b matchmaking
    public void match(RouteData routeData, Date date, String id){

        RouteData mockRouteData = new RouteData(new Coordinate(10,10),new Coordinate(20,20), new Date(),new Date());
        String mockID = "h";
        Date mockDate = date;

        while(doMatch && !matchFound){
            if(routeData.getOrigin() == mockRouteData.getOrigin()
                    && routeData.getDestination() == mockRouteData.getDestination()
                    && id != mockID && date == mockDate) {
                EventBus.INSTANCE.reportEvent(new DriverMatchFoundEvent(routeData, date));
                System.out.println("Match found!");
                matchFound = true;
            }
        }

    }


    /**
     * Methods that in the future (?) seems to be reasonable to have.
     * for now we add them to an array
     */
    public void addDriver(){

    }

    public void sddHH(){

    }

    @Override
    public void run() {

    }
}
