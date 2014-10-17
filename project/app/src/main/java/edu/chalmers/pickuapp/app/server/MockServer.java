package edu.chalmers.pickuapp.app.server;

import android.util.Log;
import edu.chalmers.pickuapp.app.events.*;
import edu.chalmers.pickuapp.app.model.Coordinate;
import edu.chalmers.pickuapp.app.model.Date;
import edu.chalmers.pickuapp.app.model.RouteData;

/**
 * Created by Malin on 2014-10-06.
 */
public class MockServer implements Runnable {


    private RouteData routeData;

    //skräpdate
    private Date date = new Date();

    public MockServer(){

    }



    //n0000b matchmaking
    public void match(RouteData routeData, Date date, String id){

        RouteData mockRouteData = new RouteData(new Coordinate(10,10),new Coordinate(20,20), new Date(),new Date());
        String mockID = "h";

        if(routeData.getOrigin() == mockRouteData.getOrigin()
                && routeData.getDestination() == mockRouteData.getDestination()
                && id != mockID) {
            EventBus.INSTANCE.reportEvent(new DriverMatchFoundEvent(routeData, date));
        }

    }

    public void setData(RouteData routeData){
        this.routeData = routeData;
    }

    @Override
    public void run() {

        try{
            Thread.sleep(5000);
        }catch(Exception e){

        }

        //Currently this causes a crash
        //EventBus.INSTANCE.reportEvent(new DriverMatchFoundEvent(this.routeData,this.date));
        Log.i("MockServer","FOUND MATCH!!!!!!###############");

    }
}
