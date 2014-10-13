package edu.chalmers.pickuapp.app.model;

import edu.chalmers.pickuapp.app.events.Event;
import edu.chalmers.pickuapp.app.events.MeetupEvent;

public class DriverResponse extends Sequence{

	private boolean isDone = false;
	private RouteData routeData;
	private Date date;
	private Sequence nextSequence;

	public DriverResponse(){
		super();
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public void processEvent(Event event) {
		if(event instanceof MeetupEvent){
			nextSequence = getSequence(DisplayInfo.class);
			isDone = true;
		}
	}

	public void insert(RouteData routeData, Date date){
		this.routeData = routeData;
		this.date = date;
	}

	@Override
	public boolean isDone() {
		return isDone;
	}
}//end DriverResponse