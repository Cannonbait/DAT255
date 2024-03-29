package edu.chalmers.pickuapp.app.server;

import android.util.Log;
import edu.chalmers.pickuapp.app.events.*;
import edu.chalmers.pickuapp.app.model.Coordinate;
import edu.chalmers.pickuapp.app.model.Date;
import edu.chalmers.pickuapp.app.model.RouteData;

/**
 * Created by Malin on 2014-10-06.
 */
public class MockNetworkClient implements Runnable {

    private RouteData routeData;

    public void match(RouteData routeData, String id){

        RouteData mockRouteData = new RouteData(new Coordinate(10,10),new Coordinate(20,20), new Date(),new Date());
        String mockID = "h";

        if(routeData.getOrigin() == mockRouteData.getOrigin()
                && routeData.getDestination() == mockRouteData.getDestination()
                && id != mockID) {
            EventBus.INSTANCE.reportEvent(new DriverMatchFoundEvent(routeData));
        }

    }

    public void setData(RouteData routeData){
        this.routeData = routeData;
    }

    @Override
    public void run() {
        try{
            Thread.sleep(5000);
            EventBus.INSTANCE.reportEvent(new HitchhikerMatchFoundEvent(routeData));
            EventBus.INSTANCE.reportEvent(new DriverMatchFoundEvent(this.routeData));
            EventBus.INSTANCE.reportEvent(new ForwardClickedEvent());
        }catch(InterruptedException e){

        }
    }
}
