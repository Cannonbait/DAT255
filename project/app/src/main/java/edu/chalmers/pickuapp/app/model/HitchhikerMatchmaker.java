package edu.chalmers.pickuapp.app.model;

import edu.chalmers.pickuapp.app.events.*;
import edu.chalmers.pickuapp.app.model.*;

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
	public void processEvent(Event event) {

		//On match found, set nextSequence to be HitchkerResponse
		if(event instanceof HitchhikerMatchFoundEvent) {

			isDone = true;
			nextSequence = getSequence(MockSequence.class); //MockSequence until there is a HitchhikerResponse.class

			//NOTE to whoever implements HitchhikerResponse:
			//You probably want to insert data from HitchhikerMatchFoundEvent into HitchhikerResponse
			//since that data should be displayed for the Hitchhiker when he decides to answer yes or no
			//to the match
			//Joakim Thorén aka Kuxe
		}

		//If hitchhiker aborted matchmaking, return to HitchhikerSetRoute
		if(event instanceof AbortMatchmakingEvent) {

			isDone = true;
			nextSequence = getSequence(HitchhikerSetRoute.class);

		}
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