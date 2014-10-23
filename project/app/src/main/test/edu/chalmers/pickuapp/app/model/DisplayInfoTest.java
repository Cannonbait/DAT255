package edu.chalmers.pickuapp.app.model;

import edu.chalmers.pickuapp.app.events.DisplayInfoOKEvent;
import edu.chalmers.pickuapp.app.events.AbortMatchmakingEvent;
import edu.chalmers.pickuapp.app.events.MeetupEvent;
import edu.chalmers.pickuapp.app.model.Coordinate;
import org.junit.Before;
import org.junit.Test;

import java.lang.Exception;

import static org.junit.Assert.*;

public class DisplayInfoTest {

    private DisplayInfo displayInfo;
    private Model model;
    private RouteData routeData;

    @Before
    public void setUp(){
        model = new Model();
        displayInfo = (DisplayInfo)Sequence.getSequence(DisplayInfo.class);
        Coordinate meetupPoint = new Coordinate(2.0, 3.0);
        Date date = new Date(2014, 12, 3, 15, 30, 0);
		routeData = new RouteData(meetupPoint, meetupPoint, date, date);
    }

    @Test
    public void testProcessEvent() throws Exception {
        //Nothing to test
    }

    @Test
    public void testGetNextSequence() throws Exception {
        //Before any event
        assertNull(displayInfo.getNextSequence());

        //Wrong event
        displayInfo.processEvent(new AbortMatchmakingEvent());
        assertNull(displayInfo.getNextSequence());

        //MeetupEvent
        displayInfo.processEvent(new MeetupEvent(routeData));
        assertNull(displayInfo.getNextSequence());

        //DisplayInfoOKEvent
        displayInfo.processEvent(new DisplayInfoOKEvent());
        assertEquals(displayInfo.getNextSequence().getClass(), Mode.class);
    }

    @Test
    public void testOnStart() throws Exception {
		//On start is supposed to run after insert call from previous sequence
		assertNull(displayInfo.getRouteData());
		displayInfo.insert(routeData);
		displayInfo.onStart();
		assertTrue(!displayInfo.isDone());
	}


    @Test //(expected = NullPointerException.class)
    public void testGetRouteData() throws Exception {
        assertNull(displayInfo.getRouteData());

        displayInfo.insert(routeData);
        assertEquals(displayInfo.getRouteData(), routeData);

    }
}