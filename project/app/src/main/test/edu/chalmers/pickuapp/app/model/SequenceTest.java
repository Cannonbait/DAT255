package edu.chalmers.pickuapp.app.model;

import edu.chalmers.pickuapp.app.events.PickedDriverEvent;
import edu.chalmers.pickuapp.app.events.PickedHitchhikerEvent;
import edu.chalmers.pickuapp.app.events.SetRouteEvent;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class SequenceTest extends Assert {

    private Sequence activeSequence;
    private HashMap<Class<? extends Sequence>, Sequence> sequences;

    @Before
    public void setUp() throws Exception {
        sequences = new HashMap<Class<? extends Sequence>, Sequence>();
        sequences.put(Mode.class, new Mode());
        sequences.put(HitchhikerSetRoute.class, new HitchhikerSetRoute());
        sequences.put(DriverSetRoute.class, new DriverSetRoute());
        sequences.put(DriverMatchmaker.class, new DriverMatchmaker());
        sequences.put(HitchhikerMatchmaker.class, new HitchhikerMatchmaker());
        sequences.put(DriverResponse.class, new DriverResponse());
        sequences.put(HitchhikerResponse.class, new HitchhikerResponse());
        sequences.put(HitchhikerWaitResponse.class, new HitchhikerWaitResponse());
        sequences.put(DisplayInfo.class, new DisplayInfo());
        sequences.put(ExitApp.class, new ExitApp());

        Sequence.setSequencesSource(sequences);

        activeSequence = sequences.get(Mode.class);
    }

    @Test
    public void testGetNextSequence() throws Exception {
        //each subclass tests this method
    }

    @Test
    public void testGetSequence() throws Exception {
        Sequence mode = Sequence.getSequence(Mode.class);
        assertEquals(mode.getClass(), new Mode().getClass());

        Sequence hitchhikerSetRoute = Sequence.getSequence(HitchhikerSetRoute.class);
        assertEquals(hitchhikerSetRoute.getClass(), new HitchhikerSetRoute().getClass());

        Sequence driverSetRoute = Sequence.getSequence(DriverSetRoute.class);
        assertEquals(driverSetRoute.getClass(), new DriverSetRoute().getClass());

        Sequence driverMatchMaker = Sequence.getSequence(DriverMatchmaker.class);
        assertEquals(driverMatchMaker.getClass(), new DriverMatchmaker().getClass());

        Sequence hitchhikerMatchMaker = Sequence.getSequence(HitchhikerMatchmaker.class);
        assertEquals(hitchhikerMatchMaker.getClass(), new HitchhikerMatchmaker().getClass());

        Sequence driverResponse = Sequence.getSequence(DriverResponse.class);
        assertEquals(driverResponse.getClass(), new DriverResponse().getClass());

        Sequence hitchhikerResponse = Sequence.getSequence(HitchhikerResponse.class);
        assertEquals(hitchhikerResponse.getClass(), new HitchhikerResponse().getClass());

        Sequence hictchhikerWaitForResponse = Sequence.getSequence(HitchhikerWaitResponse.class);
        assertEquals(hictchhikerWaitForResponse.getClass(), new HitchhikerWaitResponse().getClass());

        Sequence displayInfo = Sequence.getSequence(DisplayInfo.class);
        assertEquals(displayInfo.getClass(), new DisplayInfo().getClass());

        Sequence exitApp = Sequence.getSequence(ExitApp.class);
        assertEquals(exitApp.getClass(), new ExitApp().getClass());

    }

    @Test
    public void testSetSequencesSource() throws Exception {
        HashMap<Class<? extends Sequence>, Sequence> tmp =
                (HashMap<Class<? extends Sequence>, Sequence>) sequences.clone();

        Sequence.setSequencesSource(tmp);
        assertEquals(Sequence.getSequencesSource(), sequences);
    }

    @Test
    public void testOnStart() throws Exception {
        activeSequence.onStart();
        assertFalse(activeSequence.isDone());
    }

    @Test
    public void testIsDone() throws Exception {
        ((Mode)activeSequence).processEvent(new PickedDriverEvent());
        assertTrue(activeSequence.isDone());
    }
}