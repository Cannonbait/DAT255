package edu.chalmers.pickuapp.app.model;

import edu.chalmers.pickuapp.app.events.*;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HitchhikerResponseTest extends Assert {

    private HitchhikerResponse hitchhikerResponse;
    private Coordinate mockCoordinate;
    private Date mockDate;
    private RouteData mockRouteData;
    private Model model;

    @Before
    public void setUp() throws Exception {
        model = new Model(); //Messed-up architecture here... Need to instantiate model to make sequences aware of eachother.
        hitchhikerResponse = (HitchhikerResponse)Sequence.getSequence(HitchhikerResponse.class);
        mockCoordinate = new Coordinate(0, 0);
        mockDate = new Date(2014, 10, 15, 16, 27, 35);
        mockRouteData = new RouteData(mockCoordinate, mockCoordinate, mockDate, mockDate);
    }

    @Test
    public void testProcessEvent() throws Exception {
       //Nothing to test but side-effects, which are tested elsewhere
    }

    @Test
    public void testGetNextSequence() throws Exception {

        //If nothing has happend, there is no next sequence
        assertEquals(hitchhikerResponse.getNextSequence(), null);

        //After processing HitchikerAcceptEvent nextSequence should be HitchhikerWaitForResponse
        hitchhikerResponse.processEvent(new HitchhikerAcceptEvent());
        assertEquals(hitchhikerResponse.getNextSequence().getClass(), MockSequence.class); //Until HitchhikerWaitForResponse exists

        hitchhikerResponse.onStart(); //Reset sequence

        //If hitchhiker declined and wants to continue matchmaking, return him to matchmaking
        hitchhikerResponse.processEvent(new HitchhikerDeclineMatchAndContinueEvent());
        assertEquals(hitchhikerResponse.getNextSequence().getClass(), HitchhikerMatchmaker.class);

        hitchhikerResponse.onStart();

        //If hitchhiker declined and quit match, he should be returned to mode
		hitchhikerResponse.processEvent(new HitchhikerDeclineAndQuitEvent());
        assertEquals(hitchhikerResponse.getNextSequence().getClass(), Mode.class);
    }

    @Test
    public void testIsDone() throws Exception {

        //If nothing has happend, it's not done
        assertTrue(!hitchhikerResponse.isDone());

        //HitchikerResponse is done on any event recieved
        hitchhikerResponse.processEvent(new HitchhikerAcceptEvent());
        assertTrue(hitchhikerResponse.isDone());

        hitchhikerResponse.onStart();
        hitchhikerResponse.processEvent(new HitchhikerDeclineMatchAndContinueEvent());
        assertTrue(hitchhikerResponse.isDone());

        hitchhikerResponse.onStart();
		hitchhikerResponse.processEvent(new HitchhikerDeclineAndQuitEvent());
        assertTrue(hitchhikerResponse.isDone());
    }
}