package edu.chalmers.pickuapp.app.model;

import edu.chalmers.pickuapp.app.events.*;
import edu.chalmers.pickuapp.app.model.*;


public class DriverMatchmaker extends Sequence {

	private RouteData routeData;
	private Sequence nextSequence;
	private boolean isDone = false;


	public DriverMatchmaker() {
		super();
	}

	@Override
	public void processEvent(Event event) {

		//On match found, set nextSequence to be DriverResponse
		if(event instanceof DriverMatchFoundEvent) {

			isDone = true;
			nextSequence = getSequence(MockSequence.class);
		}

		//If driver aborted matchmaking, return to DriverSetRoute
		if(event instanceof DriverAbortMatchmakingEvent) {

			isDone = true;
			nextSequence = getSequence(DriverSetRoute.class);

		}
	}

	@Override
	public Sequence getNextSequence() {
		return nextSequence;
	}

	@Override
	public boolean isDone() {
		return isDone;
	}

	/**
	 * Matchmaker must know what route the Driver wants to take in order to properly match
	 * So insert that routeData to the matchmaker here
	 * @param routeData The route that the driver will drive
	 */
	public void insert(RouteData routeData) {
		routeData = new RouteData(routeData);
	}

}