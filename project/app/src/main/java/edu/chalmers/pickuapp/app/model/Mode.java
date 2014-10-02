package edu.chalmers.pickuapp.app.model;

import edu.chalmers.pickuapp.app.events.*;
import android.util.*;

/**
 * Created by elvirajonsson on 2014-09-29.
 */
public class Mode extends Sequence {

    private boolean pickedDriver;
    private boolean pickedHitchhiker;
    private Thread myThread;

    public Mode(){
        super();
    }
    @Override
    public void onRegister() {
        Log.i("PickUApp", "OnregisterMode");
        myThread = Thread.currentThread();
        pickedDriver = false;
        pickedHitchhiker = false;
        EventBus.INSTANCE.reportEvent(new DrawMode());
    }

    @Override
    public Sequence execute() {
        try {
            myThread.wait();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        if(pickedDriver){
            Log.i("PickUApp", "PickedDriverMode");
            return getSequence(DriverSetRoute.class);
        } else if(pickedHitchhiker){
            return getSequence(HitchhikerSetRoute.class);
        }
        return null;
    }

    @Override
    public void onUnregister() {

    }

    @Override
    public void onEvent(Event e) {

       if (e instanceof PickedDriver){
           myThread.notify();
            pickedDriver = true;
        } else if (e instanceof PickedHitchhiker){
           myThread.notify();
            pickedHitchhiker = true;
        }
    }
}
