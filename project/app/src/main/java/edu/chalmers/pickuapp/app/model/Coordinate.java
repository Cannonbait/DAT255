package edu.chalmers.pickuapp.app.model;

public class Coordinate {
	private double latitude = 0.0;
	private double longitude = 0.0;

	/**
	 * Create coordinate with latitude and longitude
	 * @param  latitude  Geographical latitude-coordinate
	 * @param  longitude Geographical longitude-coordinate
	 */
	public Coordinate(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	/**
	 * Copy-constructor
	 * @param  coordinate copy-element
	 */
	public Coordinate(Coordinate coordinate) {
		this(coordinate.getLatitude(), coordinate.getLongitude());
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	@Override
	public boolean equals(Object rhs) {
		if(rhs == null) {
			//This is boring
			return false;
		}
		if(rhs == this) {
			return true;
		}
		if(!getClass().equals(rhs.getClass())) {
			return false;
		}
		//equals() sucks
		Coordinate temp = (Coordinate)rhs;
		return latitude == temp.latitude && longitude == temp.longitude;
	}

}