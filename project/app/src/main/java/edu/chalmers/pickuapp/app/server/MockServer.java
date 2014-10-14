package edu.chalmers.pickuapp.app.server;

import edu.chalmers.pickuapp.app.events.*;
import edu.chalmers.pickuapp.app.model.Coordinate;
import edu.chalmers.pickuapp.app.model.Date;
import edu.chalmers.pickuapp.app.model.RouteData;

/**
 * Created by Malin on 2014-10-06.
 */
public class MockServer implements EventListener {

    private RouteData routeData;


    public MockServer(){
        EventBus.INSTANCE.registerListener(this);
        System.out.println("Hallo");
     }


    @Override
    public void onEvent(Event event) {

        if (event instanceof StartMatchmakingEvent) {
            StartMatchmakingEvent sme = (StartMatchmakingEvent)event;
            //write driver's data to a txt file. then start matchmaking from the hitchhiker txt file
            writeToFile(sme.getDate(),sme.getRouteData(),sme.getID());

            match(sme.getRouteData(),sme.getDate(),sme.getID());
        }

        if (event instanceof AbortMatchmakingEvent) {
            System.out.println("Aborted matchmaking");
        }
    }



    //n0000b matchmaking
    public void match(RouteData routeData, Date date, String id){

        RouteData mockRouteData = new RouteData(new Coordinate(10,10),new Coordinate(20,20));

        if(routeData.getOrigin() == mockRouteData.getOrigin()){
            if(routeData.getDestination() == mockRouteData.getDestination()) {
                System.out.println("Match made!");
            }
        }

    }

    public void writeToFile(Date date, RouteData routeData, String id){
        if(id.equals("d")){
            //we're using phones so writing to a  txt file is stupid.
        }
    }
}
