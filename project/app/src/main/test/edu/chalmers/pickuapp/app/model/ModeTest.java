package edu.chalmers.pickuapp.app.model;

import edu.chalmers.pickuapp.app.events.*;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ModeTest extends Assert {

    private Mode mode;
    private Model model;

    @Before
    public void setUp() throws Exception {
        model = new Model();
        mode = (Mode)Sequence.getSequence(Mode.class);
    }

    @Test
    public void testProcessEvent() throws Exception {
        //Nothing to test
    }

    @Test
    public void testGetNextNull() throws Exception {
        assertEquals(mode.getNextSequence(), null);
    }

    @Test
    public void testGetNextPickedDriver() throws Exception {
        assertTrue(!mode.isDone());
        mode.processEvent(new PickedDriverEvent());
        assertEquals(mode.getNextSequence().getClass(), DriverSetRoute.class);
    }

    @Test
    public void testGetNextHHSetRoute() throws Exception {
        assertTrue(!mode.isDone());
        mode.processEvent(new PickedHitchhikerEvent());
        assertEquals(mode.getNextSequence().getClass(), HitchhikerSetRoute.class);
    }

    @Test
    public void testIsDoneFalse() throws Exception {
        assertTrue(!mode.isDone());
    }
    @Test
    public void testIsDonePickedDriver() throws Exception {
        mode.processEvent(new PickedDriverEvent());
        assertTrue(mode.isDone());
    }

    @Test
    public void testIsDonePickedHitchhiker() throws Exception {
        mode.processEvent(new PickedHitchhikerEvent());
        assertTrue(mode.isDone());
    }
}