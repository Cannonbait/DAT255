package edu.chalmers.pickuapp.app.server;

import edu.chalmers.pickuapp.app.events.*;
import edu.chalmers.pickuapp.app.model.Coordinate;
import edu.chalmers.pickuapp.app.model.RouteData;

/**
 * Created by Malin on 2014-10-06.
 */
public class MockServer implements EventListener {

    private RouteData routeData;


    public MockServer(RouteData routeData){
        EventBus.INSTANCE.registerListener(this);
        System.out.println("Hallo");

        this.routeData = routeData;
    }


    @Override
    public void onEvent(Event event) {

        if (event instanceof DriverPickedRouteEvent) {
            System.out.println("Searching for match...");
            match(((DriverPickedRouteEvent) event).getRouteData());
            //match routeData with existing data in mockserver
        }

        if (event instanceof AbortMatchmakingEvent) {
            System.out.println("Aborted matchmaking");
        }
    }



    //n0000b matchmaking
    public void match(RouteData routeData){

        RouteData mockRouteData = new RouteData(new Coordinate(10,10),new Coordinate(20,20));

        if(routeData.getSource() == mockRouteData.getSource()){
            if(routeData.getDestination() == mockRouteData.getDestination()) {
                System.out.println("Match made!");
            }
        }

    }
}
