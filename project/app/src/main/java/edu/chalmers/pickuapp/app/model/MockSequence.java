package edu.chalmers.pickuapp.app.model;

import android.util.Log;
import edu.chalmers.pickuapp.app.model.Sequence;
import edu.chalmers.pickuapp.app.events.*;

class MockSequence extends Sequence {
	
	public MockSequence() {
		super();
	}

	@Override
	public void processEvent(Event e) {	}

    @Override
    public boolean isDone() {
    	return true;
    }

}