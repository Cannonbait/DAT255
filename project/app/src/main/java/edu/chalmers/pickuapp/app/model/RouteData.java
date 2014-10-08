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

	@Override
	public boolean equals(Object rhs) {
		if(rhs == null) {
			return false;
		}
		if(this.getClass() != rhs.getClass()) {
			return false;
		}
		RouteData temp = (RouteData)rhs;
		return source.equals(temp.source) && destination.equals(temp.destination);
	}

}