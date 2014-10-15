package edu.chalmers.pickuapp.app.model;

import edu.chalmers.pickuapp.app.events.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HitchhikerWaitResponseTest {

    private Model model;
    private HitchhikerWaitResponse hitchhikerWaitResponse = (HitchhikerWaitResponse)Sequence.getSequence(HitchhikerWaitResponse.class);
    private RouteData mockRouteData;
    private Date mockDate;

    @Before
    public void setUp(){
        model = new Model();
        mockRouteData = new RouteData(new Coordinate(0, 0), new Coordinate(1, 1));
        mockDate = new Date(2014, 10, 15, 21, 19, 59);
    }

    @Test
    public void testProcessEvent() throws Exception {
        //Nothing to test
    }

    @Test
    public void testGetBackSequence() throws Exception {
        //It is disallowed to go back when waiting for answer from driver
        assertNull(hitchhikerWaitResponse.getBackSequence());
    }

    @Test
    public void testGetNextSequence() throws Exception {
        assertNull(hitchhikerWaitResponse.getNextSequence());

        hitchhikerWaitResponse.processEvent(new DriverDeclineHitchhiker());
        assertEquals(hitchhikerWaitResponse.getNextSequence().getClass(), HitchhikerMatchmaker.class);

        hitchhikerWaitResponse.onStart();
        hitchhikerWaitResponse.processEvent(new DriverDeclineKeepSearch());
        assertEquals(hitchhikerWaitResponse.getNextSequence().getClass(), HitchhikerMatchmaker.class);

        hitchhikerWaitResponse.onStart();
        hitchhikerWaitResponse.processEvent(new DriverPicksUpHitchhikerEvent(mockRouteData, mockDate));
        assertEquals(hitchhikerWaitResponse.getNextSequence().getClass(), DisplayInfo.class);        
    }

    @Test
    public void testIsDone() throws Exception {
        assertFalse(hitchhikerWaitResponse.isDone());

        hitchhikerWaitResponse.processEvent(new DriverDeclineHitchhiker());
        assertTrue(hitchhikerWaitResponse.isDone());

        hitchhikerWaitResponse.onStart();
        hitchhikerWaitResponse.processEvent(new DriverDeclineKeepSearch());
        assertTrue(hitchhikerWaitResponse.isDone());

        hitchhikerWaitResponse.onStart();
        hitchhikerWaitResponse.processEvent(new DriverPicksUpHitchhikerEvent(mockRouteData, mockDate));
        assertTrue(hitchhikerWaitResponse.isDone());
    }   
}