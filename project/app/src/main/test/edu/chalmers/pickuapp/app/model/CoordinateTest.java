package edu.chalmers.pickuapp.app.model;

import edu.chalmers.pickuapp.app.events.*;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CoordinateTest extends Assert {

    private Coordinate coordinate;
    private double latitute = 5;
    private double longitude = 7;

    @Before
    public void setUp() throws Exception {
        coordinate = new Coordinate(latitute, longitude);
    }

    @Test
    public void testGetLatitude() {
        assertEquals(coordinate.getLatitude(), latitute);
    }

    @Test
    public void testGetLongitude() {
        assertEquals(coordinate.getLongitude(), longitude);
    }

    @Test
    public void testEquals() {
        Coordinate nullCoordinate = null;
        Coordinate differentCoordinate = new Coordinate(latitute+1, longitude+1);
        Coordinate differentLatitute = new Coordinate(latitute+1, longitude);
        Coordinate differentLongitude = new Coordinate(latitute, longitude+1);
        Coordinate identicalCoordinate = new Coordinate(latitute, longitude);
        Coordinate sameCoordinate = coordinate;

        assertTrue(!coordinate.equals(nullCoordinate));
        assertTrue(!coordinate.equals(differentCoordinate));
        assertTrue(!coordinate.equals(differentLatitute));
        assertTrue(!coordinate.equals(differentLongitude));
        assertTrue(coordinate.equals(identicalCoordinate));
        assertTrue(coordinate.equals(sameCoordinate));

    }
}