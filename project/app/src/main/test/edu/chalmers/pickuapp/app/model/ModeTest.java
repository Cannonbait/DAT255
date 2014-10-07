package edu.chalmers.pickuapp.app.model;

import edu.chalmers.pickuapp.app.events.Event;
import edu.chalmers.pickuapp.app.events.PickedDriverEvent;
import junit.framework.*;

public class ModeTest extends TestCase {

    private Mode mode = new Mode();

    public void testProcessEventPickedDriver() throws Exception{
        Event event = new PickedDriverEvent();
        mode.processEvent(event);
        assertTrue(mode.isDone());
    }

    public void testGetNextSequence() throws Exception {

    }

    public void testIsDone() throws Exception {

    }
}