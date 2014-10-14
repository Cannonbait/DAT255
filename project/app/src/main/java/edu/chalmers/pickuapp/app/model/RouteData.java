package edu.chalmers.pickuapp.app.model;

public class RouteData {

	private Coordinate origin;
	private Coordinate destination;

	/**
	 * @param  origin      Where to pickup
	 * @param  destination Where to go
	 */
	public RouteData(Coordinate origin, Coordinate destination) {
		this.origin = new Coordinate(origin);
		this.destination = new Coordinate(destination);
	}

	/**
	 * Copy-constructor
	 * @param  routeData routeData to copy 
	 */
	public RouteData(RouteData routeData) {
		this(routeData.origin, routeData.destination);
	}

	public Coordinate getOrigin() {
		return new Coordinate(origin);
	}

	public Coordinate getDestination() {
		return new Coordinate(destination);
	}

	@Override
	public boolean equals(Object rhs) {
		if(rhs == null) {
			return false;
		}
		if(rhs == this) {
			return true;
		}
		if(this.getClass() != rhs.getClass()) {
			return false;
		}
		RouteData temp = (RouteData)rhs;
		return origin.equals(temp.origin) && destination.equals(temp.destination);
	}

}