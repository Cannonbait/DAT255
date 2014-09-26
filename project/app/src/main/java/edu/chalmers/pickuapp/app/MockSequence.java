package edu.chalmers.pickuapp.app;

import android.util.Log;

class MockSequence extends Sequence {
	
	public MockSequence() {
		super();
	}

	@Override
	public void register() {
		//Register to eventbuss
	}

	@Override
	public Sequence execute() {

		//Wait for input from eventbuss and then process the input
		Log.d("PickUApp", "MockSequence.exeute()!!");
		return null;

	}

	@Override
	public void unregister() {
		//Unregister from eventbuss
	}

}