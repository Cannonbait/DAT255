package edu.chalmers.pickuapp.app.model;

import edu.chalmers.pickuapp.app.events.*;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HitchhikerMatchmakerTest extends Assert {

    private HitchhikerMatchmaker hitchhikerMatchmaker;
    private Coordinate mockCoordinate;
    private RouteData mockRouteData;
    private Date mockDate;
    private Model model;

    @Before
    public void setUp() throws Exception {
        model = new Model(); //Messed-up architecture here... Need to instantiate model to make sequences aware of eachother.
        hitchhikerMatchmaker = (HitchhikerMatchmaker)Sequence.getSequence(HitchhikerMatchmaker.class);
        mockCoordinate = new Coordinate(0, 0);
        mockDate = new Date(2014, 9, 8, 13, 51, 0); //2014 October 8th 13:51:00
        mockRouteData = new RouteData(mockCoordinate, mockCoordinate, mockDate, mockDate);

    }

    @Test
    public void testProcessEvent() throws Exception {
       //Nothing to test but side-effects, which are tested elsewhere
    }

    @Test
    public void testGetNextSequence() throws Exception {

        //If nothing has happend, there is no next sequence
        assertEquals(hitchhikerMatchmaker.getNextSequence(), null);

        //If match was found, next sequence should prompt the hitchiker to accept or decline
        Event hitchhikerMatchFoundEvent = new HitchhikerMatchFoundEvent(mockRouteData, mockDate);
        hitchhikerMatchmaker.processEvent(hitchhikerMatchFoundEvent);
        assertEquals(hitchhikerMatchmaker.getNextSequence().getClass(), MockSequence.class); //Until there is a HitchikerResponse.class
        
        //If match was aborted, next sequence should be hitchhikerSetRoute
        Event abortMatchmaking = new AbortMatchmakingEvent();
        hitchhikerMatchmaker.processEvent(abortMatchmaking);
        assertEquals(hitchhikerMatchmaker.getNextSequence().getClass(), HitchhikerSetRoute.class);
    }

    @Test
    public void testIsDone() throws Exception {

        //If nothing has happend, it's not done
        assertTrue(!hitchhikerMatchmaker.isDone());

        //If match was found, it's done
        Event hitchhikerMatchFoundEvent = new HitchhikerMatchFoundEvent(mockRouteData, mockDate);
        hitchhikerMatchmaker.processEvent(hitchhikerMatchFoundEvent);
        assertTrue(hitchhikerMatchmaker.isDone());

        //If matchmaking was aborted, it's done
        Event abortMatchmaking = new AbortMatchmakingEvent();
        hitchhikerMatchmaker.processEvent(abortMatchmaking);
        assertTrue(hitchhikerMatchmaker.isDone());
    }

    @Test
    public void testInsert() {
    	//Make sure the routeData is actually inserted into the class without losing any data
		hitchhikerMatchmaker.insert(mockRouteData);
		assertEquals(mockRouteData, hitchhikerMatchmaker.getRouteData());
	}

	@Test
    public void testGetRouteData() {
    	//NA, whatever this test would do is already carried out in testInsert()
	}
}