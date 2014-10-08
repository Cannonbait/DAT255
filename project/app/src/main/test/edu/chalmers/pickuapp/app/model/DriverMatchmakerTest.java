package edu.chalmers.pickuapp.app.model;

import edu.chalmers.pickuapp.app.events.*;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DriverMatchmakerTest extends Assert {

    private DriverMatchmaker driverMatchmaker;
    private Coordinate mockCoordinate;
    private RouteData mockRouteData;
    private Model model;

    @Before
    public void setUp() throws Exception {
        model = new Model(); //Messed-up architecture here... Need to instantiate model to make sequences aware of eachother.
        driverMatchmaker = (DriverMatchmaker)Sequence.getSequence(DriverMatchmaker.class);
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
        assertEquals(driverMatchmaker.getNextSequence(), null);

        //If match was found, next sequence should prompt the driver to accept or decline
        
        //If match was aborted, next sequence should be driverSetRoute
    }

    @Test
    public void testIsDone() throws Exception {

        //If nothing has happend, it's not done
        assertTrue(!driverMatchmaker.isDone());

        //If match was found, it's done
        
        //If matchmaking was aborted, it's done
    }

    @Test
    public void testInsert() {
    	//Make sure the routeData is actually inserted into the class without losing any data
		driverMatchmaker.insert(mockRouteData);
		assertEquals(mockRouteData, driverMatchmaker.getRouteData());
	}
}