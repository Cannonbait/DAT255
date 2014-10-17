package edu.chalmers.pickuapp.app.model;

public class RouteData {

	private Coordinate origin;
	private Coordinate destination;
    private Date startDate;
    private Date stopDate;

    /**
     * Needs to be defined for lazy initialization
     */
    public RouteData(){}
    	/**
	 * @param  origin      Where to pickup
	 * @param  destination Where to go
	 */
	public RouteData(Coordinate origin, Coordinate destination, Date startDate, Date stopDate) {
		this.origin = new Coordinate(origin);
		this.destination = new Coordinate(destination);
        this.startDate = startDate;
        this.stopDate = stopDate;
	}

	/**
	 * Copy-constructor
	 * @param  routeData routeData to copy 
	 */
	public RouteData(RouteData routeData) {
		this(new Coordinate(routeData.origin), new Coordinate(routeData.destination), new Date(routeData.startDate), new Date(routeData.stopDate));

	}

	public Coordinate getOrigin() {
		return new Coordinate(origin);
	}

	public Coordinate getDestination() {
		return new Coordinate(destination);
	}

    public Date getStartDate() { return new Date(startDate); }

    public Date getStopDate() { return new Date(stopDate); }

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