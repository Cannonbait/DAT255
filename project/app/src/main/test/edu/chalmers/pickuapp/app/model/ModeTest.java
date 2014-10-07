package edu.chalmers.pickuapp.app.model;

import edu.chalmers.pickuapp.app.events.*;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ModeTest extends Assert {

    private Mode mode;

    @Before
    public void setUp() throws Exception {
        mode = new Mode();
    }

    @Test
    public void testProcessEvent() throws Exception {
       /* Event evt = new PickedDriverEvent();
        mode.processEvent(evt);
        assertTrue(mode.isDone());*/
    }

    @Test
    public void testGetNextSequence() throws Exception {

    }

    @Test
    public void testIsDone() throws Exception {

    }
}