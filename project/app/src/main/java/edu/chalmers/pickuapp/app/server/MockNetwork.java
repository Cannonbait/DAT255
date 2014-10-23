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
        this.data = new RouteData(data);
        sendPacket(new DriverMatchFoundEvent(this.data, this.data.getStartDate()));
    }

    public void startHitchhikerMatchmaking(final RouteData data){
        //If we want to modify the match do this here
        this.data = new RouteData(data);
        sendPacket(new HitchhikerMatchFoundEvent(this.data, this.data.getStartDate()));
    }

    public void hitchhikerAcceptMatch(){
        final double num = Math.random();
        if (num< 0.4){
            sendPacket(new DriverPicksUpHitchhikerEvent(data, data.getStartDate()));
        } else if (num < 0.8){
            sendPacket(new DriverDeclineHitchhikerEvent());
        } else {
            sendPacket(new DriverDeclineHitchhikerEvent(), 20000);
        }


    }

    public void hitchhikerDeclineMatch(){
        sendPacket(new DriverDeclineHitchhikerEvent());
    }

    public void hitchhikerAbortMatchmaking(){
        t.interrupt();
    }

    private void sendPacket(final Event event, final long timer){
        t = new Thread(){
            public void run(){
                try {
                    this.sleep(timer);
                    EventBus.INSTANCE.reportEvent(event);
                    EventBus.INSTANCE.reportEvent(new ForwardClickedEvent());
                } catch (InterruptedException e){}
            }
        };
        t.start();
    }

    private void sendPacket(final Event event){
        sendPacket(event, 5000);
    }


}

