package edu.chalmers.pickuapp.app.model;

import android.util.Log;
import edu.chalmers.pickuapp.app.model.Sequence;
import edu.chalmers.pickuapp.app.events.*;

class HitchhikerWaitResponse extends Sequence {
	
	public HitchhikerWaitResponse() {
		super();
	}

	@Override
	public void processEvent(Event e) {
		//If driver declined this hitchhiker, return to matchmaking
		if(e instanceof DriverDeclineHitchhiker || e instanceof DriverDeclineKeepSearch) {
			nextSequence = Sequence.getSequence(HitchhikerMatchmaker.class);

			isDone = true;
		}

		//If driver accepted this hitchhiker, goto displayinfo
		if(e instanceof DriverPicksUpHitchhikerEvent) {
			DriverPicksUpHitchhikerEvent temp = (DriverPicksUpHitchhikerEvent)e;
			nextSequence = Sequence.getSequence(DisplayInfo.class);
			((DisplayInfo)nextSequence).insert(temp.getRouteData().getOrigin(), temp.getDate());

			isDone = true;
		}
	}

    @Override
    public Sequence getBackSequence() {
        return null; //Don't go anywhere else when waiting for answer - you can't regret your accept
    }

    @Override
    public boolean isDone() {
    	return isDone;
    }

}