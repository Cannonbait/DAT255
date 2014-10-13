package edu.chalmers.pickuapp.app.model;

import edu.chalmers.pickuapp.app.events.*;
import edu.chalmers.pickuapp.app.model.*;

/**
 * This class represents the "Matchmaking..."-screen.
 * Whenever a driver is waiting for a match to be made by matchmaking-server, this sequence should be the active sequence
 */
public class DriverMatchmaker extends Sequence {

	private Date date;
	private RouteData routeData;

	public DriverMatchmaker() {
		super();
	}

	@Override
	public void processEvent(Event event) {

		//On match found, set nextSequence to be DriverResponse
		if(event instanceof DriverMatchFoundEvent) {

			isDone = true;
			nextSequence = getSequence(DriverResponse.class);
			//Forwards routeData and date
			((DriverResponse)nextSequence).insert(routeData, date);
		}

		//If driver aborted matchmaking, return to DriverSetRoute
		if(event instanceof AbortMatchmakingEvent) {

			isDone = true;
			nextSequence = getSequence(DriverSetRoute.class);

		}
	}

	@Override
	public Sequence getNextSequence() {
		return nextSequence;
	}


	/**
	 * Matchmaker must know what route the Driver wants to take in order to properly match
	 * So insert that routeData to the matchmaker here
	 * @param routeData The route that the driver will drive
	 */
	public void insert(RouteData routeData, Date date) {
		this.date = new Date(date);
		this.routeData = new RouteData(routeData);
	}

	/**
	 * @return deep-copy of routeData
	 */
	public RouteData getRouteData() {
		return new RouteData(routeData);
	}

	public Date getDate(){
		return date;
	}

}