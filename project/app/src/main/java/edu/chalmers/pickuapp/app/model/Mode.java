package edu.chalmers.pickuapp.app.model;

import edu.chalmers.pickuapp.app.events.*;

/**
 * Created by elvirajonsson on 2014-09-29.
 */
public class Mode extends Sequence {

    private boolean pickedDriver;
    private boolean pickedHitchhiker;

    public Mode(){
        super();
    }
    @Override
    public void onRegister() {
        pickedDriver = false;
        pickedHitchhiker = false;
        EventBus.INSTANCE.reportEvent(new DrawMode());
    }

    @Override
    public Sequence execute() {

        if(pickedDriver){
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
       String src = e.getClass().getName();

       if (src.equals("PickedDriver")){
            pickedDriver = true;
        } else if (src.equals("PickedHitchhiker")){
            pickedHitchhiker = true;
        }
    }
}
