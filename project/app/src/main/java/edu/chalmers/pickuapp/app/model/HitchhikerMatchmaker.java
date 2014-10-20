package edu.chalmers.pickuapp.app.model;

import edu.chalmers.pickuapp.app.events.*;

/**
 * This class represents the "Matchmaking..."-screen.
 * Whenever a hitchhiker is waiting for a match to be made by matchmaking-server, this sequence should be the active sequence
 */
public class HitchhikerMatchmaker extends Sequence {

	private RouteData routeData;

	public HitchhikerMatchmaker() {
		super();
	}

	@Override
	public void onStart() {
		EventBus.INSTANCE.reportEvent(new HitchhikerStartMatchmakingEvent(routeData));
	}

	@Override
	public void processEvent(Event event) {

		//On match found, set nextSequence to be HitchkerResponse
		if(event instanceof HitchhikerMatchFoundEvent) {
			HitchhikerMatchFoundEvent hitchhikerMatchFoundEvent = (HitchhikerMatchFoundEvent)event;

			isDone = true;
			nextSequence = getSequence(HitchhikerResponse.class);
			((HitchhikerResponse)nextSequence).insert(hitchhikerMatchFoundEvent.routeData, hitchhikerMatchFoundEvent.date);
		}

		//If hitchhiker aborted matchmaking, return to HitchhikerSetRoute
		if(event instanceof AbortMatchmakingEvent) {

			isDone = true;
			nextSequence = getBackSequence();

		}
	}

    @Override
    public Sequence getBackSequence() {
        return getSequence(HitchhikerSetRoute.class);
    }


    /**
	 * Matchmaker must know what route the Hitchhiker wants to take in order to properly match
	 * So insert that routeData to the matchmaker here
	 * @param routeData The route that the hitchhiker will wants to go
	 */
	public void insert(RouteData routeData) {
		this.routeData = new RouteData(routeData);
	}

	/**
	 * @return deep-copy of routeData
	 */
	public RouteData getRouteData() {
		return new RouteData(routeData);
	}

}