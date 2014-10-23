package edu.chalmers.pickuapp.app.server;

import android.util.Log;
import edu.chalmers.pickuapp.app.events.*;
import edu.chalmers.pickuapp.app.model.Coordinate;
import edu.chalmers.pickuapp.app.model.Date;
import edu.chalmers.pickuapp.app.model.RouteData;


public class MockNetwork implements Network{
    RouteData data;
    Thread t;

    public void startDriverMatchmaking(final RouteData data){
        //If we want to modify the match do this here
        Log.i("MockNetwork", "Starting matchmaking");
        this.data = new RouteData(data);
        sendPacket(new DriverMatchFoundEvent(this.data, this.data.getStartDate()));
    }

    public void startHitchhikerMatchmaking(final RouteData data){
        //If we want to modify the match do this here
        this.data = new RouteData(data);
        sendPacket(new HitchhikerMatchFoundEvent(this.data, this.data.getStartDate()));
    }

    public void hitchhikerAcceptMatch(){
        sendPacket(new DriverPicksUpHitchhikerEvent(data, data.getStartDate()));
    }

    public void hitchhikerDeclineMatch(){
        sendPacket(new DriverDeclineHitchhikerEvent());
    }

    public void hitchhikerAbortMatchmaking(){
        t.interrupt();
    }

    private void sendPacket(final Event event){
        t = new Thread(){
            public void run(){
                try {
                    this.sleep(5000);
                    Log.i("Thread", "HERPDERPDERP");
                    EventBus.INSTANCE.reportEvent(event);
                    EventBus.INSTANCE.reportEvent(new ForwardClickedEvent());
                } catch (InterruptedException e){

                }
            }
        };
        t.start();
    }


}
