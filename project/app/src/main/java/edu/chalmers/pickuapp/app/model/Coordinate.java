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

}