package edu.chalmers.pickuapp.app.model;

public class RouteData {

	private Coordinate source;
	private Coordinate destination;

	/**
	 * @param  source      Where to pickup
	 * @param  destination Where to go
	 */
	public RouteData(Coordinate source, Coordinate destination) {
		this.source = new Coordinate(source);
		this.destination = new Coordinate(destination);
	}

	/**
	 * Copy-constructor
	 * @param  routeData routeData to copy 
	 */
	public RouteData(RouteData routeData) {
		this(routeData.source, routeData.destination);
	}

	public Coordinate getSource() {
		return new Coordinate(source);
	}

	public Coordinate getDestination() {
		return new Coordinate(destination);
	}

}