package edu.chalmers.pickuapp.app.model;

import edu.chalmers.pickuapp.app.events.Event;

/**
 * Created by Ance on 2014-10-06.
 */
public class DriverResponse extends Sequence{

	private boolean isDone = false;
	private RouteData routeData;
	private Date date;

	public DriverResponse(){
		super();
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public void processEvent(Event event) {
		/* TODO- waiting for event
		if(event instanceof EventFormMatchMaking){
			nextSequens = getSequence(WhateversIsNext.class);
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
}