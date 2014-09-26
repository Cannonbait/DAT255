package edu.chalmers.pickuapp.app;

import android.util.Log;


abstract class Sequence {

	public Sequence() {
		
	}

	public abstract void register();
	public abstract Sequence execute();
	public abstract void unregister();
}