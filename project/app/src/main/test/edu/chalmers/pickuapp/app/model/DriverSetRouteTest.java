package edu.chalmers.pickuapp.app.model;

import edu.chalmers.pickuapp.app.events.*;
import edu.chalmers.pickuapp.app.events.SetRouteEvent;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DriverSetRouteTest extends Assert {

    private DriverSetRoute driverSetRoute;
    private Coordinate mockCoordinate;
    private RouteData mockRouteData;
    private Model model;
    private Date mockDate;

    @Before
    public void setUp() throws Exception {
        model = new Model(); //Messed-up architecture here... Need to instantiate model to make sequences aware of eachother.
        driverSetRoute = (DriverSetRoute)Sequence.getSequence(DriverSetRoute.class);
        mockCoordinate = new Coordinate(0, 0);
        mockDate = new Date(2014, 10, 13, 17, 46, 0);
        mockRouteData = new RouteData(mockCoordinate, mockCoordinate, mockDate, mockDate);
    }

    @Test
    public void testProcessEvent() throws Exception {
       //Nothing to test but side-effects, which are tested elsewhere
    }

    @Test
    public void testGetNextSequence() throws Exception {

        //If nothing has happend, there is no next sequence
        assertEquals(driverSetRoute.getNextSequence(), null);

        SetRouteEvent setRouteEvent = new SetRouteEvent(mockRouteData); //need args
        driverSetRoute.processEvent(setRouteEvent);

        //If driverPickedRouteEvent was processed, enter matchmaking
        assertEquals(driverSetRoute.getNextSequence().getClass(), new DriverMatchmaker().getClass());
    }

    @Test
    public void testIsDone() throws Exception {

        //If nothing has happend, it's not done
        assertTrue(!driverSetRoute.isDone());

        SetRouteEvent setRouteEvent = new SetRouteEvent(mockRouteData); //need args
        driverSetRoute.processEvent(setRouteEvent);

        //If driverPickedRouteEvent was processed, this sequence is done
        assertTrue(driverSetRoute.isDone());
    }
}