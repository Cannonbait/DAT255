package edu.chalmers.pickuapp.app.model;

import edu.chalmers.pickuapp.app.events.*;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RouteDataTest extends Assert {

    RouteData routeData;
    Coordinate source;
    Coordinate destination;

    @Before
    public void setUp() throws Exception {
       source = new Coordinate(0, 0);
       destination = new Coordinate(100, 100);
       routeData = new RouteData(source, destination);
    }

    /**
     * Test RouteData(RouteData)-constructor
     */
    @Test
    public void RouteDataRouteData() {
        RouteData copyRouteData = new RouteData(routeData);
        assertEquals(copyRouteData, routeData);
    }

    @Test
    public void testEquals() {
        RouteData nullRouteData = null;
        RouteData differentRouteData = new RouteData(new Coordinate(5, 5), new Coordinate(6, 6));
        RouteData identicalRouteData = new RouteData(routeData);
        RouteData sameRouteData = routeData;

        assertTrue(!routeData.equals(nullRouteData));
        assertTrue(!routeData.equals(differentRouteData));
        assertTrue(routeData.equals(identicalRouteData));
        assertTrue(routeData.equals(sameRouteData));
    }
}