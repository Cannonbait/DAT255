package edu.chalmers.pickuapp.app.model;

import android.util.Log;
import edu.chalmers.pickuapp.app.model.Sequence;
import edu.chalmers.pickuapp.app.events.*;

class HitchhikerWaitResponse extends Sequence {
	
	public MockSequence() {
		super();
	}

	@Override
	public void processEvent(Event e) {
		//If driver declined this hitchhiker, return to matchmaking
		
		//If driver accepted this hitchhiker, goto displayinfo
	}

    @Override
    public Sequence getBackSequence() {
        return this; //Don't go anywhere else when waiting for answer - you can't regret your accept
    }

    @Override
    public boolean isDone() {
    	return true;
    }

}