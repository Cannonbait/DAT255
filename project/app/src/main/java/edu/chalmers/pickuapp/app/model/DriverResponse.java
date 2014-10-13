package edu.chalmers.pickuapp.app.model;

import edu.chalmers.pickuapp.app.events.Event;

public class DriverResponse extends Sequence{

	private boolean isDone = false;
	private RouteData routeData;
	private Date date;
	private Sequence nextSequens;

	public DriverResponse(){
		super();
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public void processEvent(Event event) {
		/* TODO- which event to listen for?
		if(event instanceof FunEvent){
			nextSequens = getSequence(DisplayInfo.class);
			isDone = true;
		}
		*/
	}

	public void insert(RouteData routeData, Date date){
		this.routeData = routeData;
		this.date = date;
	}

	@Override
	public Sequence getNextSequence() {
		return nextSequence;
	}

	@Override
	public boolean isDone() {
		return isDone;
	}
}//end DriverResponse