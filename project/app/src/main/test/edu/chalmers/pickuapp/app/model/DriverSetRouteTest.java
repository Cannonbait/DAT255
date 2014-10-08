package edu.chalmers.pickuapp.app.model;

import edu.chalmers.pickuapp.app.events.*;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DriverSetRouteTest extends Assert {

    private DriverSetRoute driverSetRoute;
    private Coordinate mockCoordinate;
    private RouteData mockRouteData;
    private Model model;

    @Before
    public void setUp() throws Exception {
        model = new Model(); //Messed-up architecture here... Need to instantiate model to make sequences aware of eachother.
        driverSetRoute = (DriverSetRoute)Sequence.getSequence(DriverSetRoute.class);
        mockCoordinate = new Coordinate(0, 0);
        mockRouteData = new RouteData(mockCoordinate, mockCoordinate);
    }

    @Test
    public void testProcessEvent() throws Exception {
       //Nothing to test but side-effects, which are tested elsewhere
    }

    @Test
    public void testGetNextSequence() throws Exception {

        //If nothing has happend, there is no next sequence
        assertEquals(driverSetRoute.getNextSequence(), null);

        DriverPickedRouteEvent driverPickedRouteEvent = new DriverPickedRouteEvent(mockRouteData); //need args
        driverSetRoute.processEvent(driverPickedRouteEvent);

        //If driverPickedRouteEvent was processed, enter matchmaking
        assertEquals(driverSetRoute.getNextSequence().getClass(), new DriverMatchmaker().getClass());
    }

    @Test
    public void testIsDone() throws Exception {

        //If nothing has happend, it's not done
        assertTrue(!driverSetRoute.isDone());

        DriverPickedRouteEvent driverPickedRouteEvent = new DriverPickedRouteEvent(mockRouteData);
        driverSetRoute.processEvent(driverPickedRouteEvent);

        //If driverPickedRouteEvent was processed, this sequence is done
        assertTrue(driverSetRoute.isDone());
    }
}