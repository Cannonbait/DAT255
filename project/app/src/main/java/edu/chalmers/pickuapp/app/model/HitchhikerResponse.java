package edu.chalmers.pickuapp.app.model;

import edu.chalmers.pickuapp.app.events.*;

public class HitchhikerResponse extends Sequence {

	private RouteData routeData;
	private Date date;

	public HitchhikerResponse(){
		super();
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public void processEvent(Event event) {
		//If accept, goto HitchhikerWaitForResponse
		if(event instanceof HitchhikerAcceptEvent) {
			nextSequence = Sequence.getSequence(MockSequence.class); //until there is a HitchhikerWaitForResponse.class
			//((HitchhikerWaitForResponse)nextSequence).insert(routeData, date); //Insert routeData to waitForResponse
			//routeData and date 
			isDone = true;
		}

		//If decline and continue search, goto matchmaking with same indata as before
		if(event instanceof HitchhikerDeclineMatchAndContinueEvent) {
			//Do not need to pass any data to matchmaker; it is already known
			nextSequence = Sequence.getSequence(HitchhikerMatchmaker.class);
			isDone = true;
		}
		
		//If decline and quit, goto mode
		if(event instanceof HitchhikerDeclineAndQuitEvent) {
			nextSequence = Sequence.getSequence(Mode.class);
			isDone = true;
		}
	}

    @Override
    public Sequence getBackSequence() {
        return getSequence(Mode.class); //TODO is this corrent? where should this lead to?
    }

    public void insert(RouteData routeData, Date date){
		this.routeData = routeData;
		this.date = date;
	}

	@Override
	public boolean isDone() {
		return isDone;
	}
}