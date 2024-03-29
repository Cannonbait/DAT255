package edu.chalmers.pickuapp.app.model;

import edu.chalmers.pickuapp.app.events.*;

public class HitchhikerResponse extends Sequence {

	private RouteData routeData;

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
			nextSequence = Sequence.getSequence(HitchhikerWaitResponse.class);
			//((HitchhikerWaitResponse)nextSequence).insert(routeData, date); //is this needed?

			isDone = true;
			EventBus.INSTANCE.reportEvent(new ForwardClickedEvent());
		}

		//If decline and continue search, goto matchmaking with same indata as before
		if(event instanceof HitchhikerDeclineMatchAndContinueEvent) {
			//Do not need to pass any data to matchmaker; it is already known
			nextSequence = Sequence.getSequence(HitchhikerMatchmaker.class);

			isDone = true;
			EventBus.INSTANCE.reportEvent(new ForwardClickedEvent());
		}
		
		//If decline and quit, goto mode
		if(event instanceof HitchhikerDeclineAndQuitEvent) {
			nextSequence = Sequence.getSequence(Mode.class);

			isDone = true;
			EventBus.INSTANCE.reportEvent(new ForwardClickedEvent());
		}
	}

    @Override
    public Sequence getBackSequence() {
        return getSequence(HitchhikerMatchmaker.class); //TODO is this corrent? where should this lead to?
    }

    public void insert(RouteData routeData){
		this.routeData = routeData;
	}

	@Override
	public boolean isDone() {
		return isDone;
	}
}