package edu.chalmers.pickuapp.app.model;



import edu.chalmers.pickuapp.app.events.*;

abstract class Sequence implements EventListener{

	public Sequence() {
		
	}

	public void register() {
        EventBus.INSTANCE.registerListener(this);
        onRegister();
    }
    public void unregister(){
        EventBus.INSTANCE.removeListener(this);
        onUnregister();
    }
    public abstract void onRegister();
	public abstract Sequence execute();
    public abstract void onUnregister();

}