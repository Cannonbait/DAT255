package edu.chalmers.pickuapp.app.model;

import edu.chalmers.pickuapp.app.events.DisplayInfoOKEvent;
import edu.chalmers.pickuapp.app.events.AbortMatchmakingEvent;
import edu.chalmers.pickuapp.app.events.MeetupEvent;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DisplayInfoTest {

    private DisplayInfo displayInfo;
    private Model model;
    private Coordinate meetupPoint;
    private Date date;


    @Before
    public void setUp(){
        model = new Model();
        displayInfo = (DisplayInfo)Sequence.getSequence(DisplayInfo.class);
        meetupPoint = new Coordinate(2.0, 3.0);
        date = new Date(2014, 12, 3, 15, 30, 0);
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
        displayInfo.processEvent(new MeetupEvent(meetupPoint, date));
        assertNull(displayInfo.getNextSequence());

        //DisplayInfoOKEvent
        displayInfo.processEvent(new DisplayInfoOKEvent());
        assertEquals(displayInfo.getNextSequence().getClass(), Mode.class);
    }

    @Test
    public void testOnStart() throws Exception {
        displayInfo.onStart();
        assertTrue(!displayInfo.isDone());
        assertNull(displayInfo.getMeetupPoint());
        assertNull(displayInfo.getDate());

    }

    @Test
    public void testGetDate() throws Exception {
        assertNull(displayInfo.getDate());

        displayInfo.insert(meetupPoint, date);
        assertEquals(displayInfo.getDate(), date);

    }

    @Test
    public void testGetMeetupPoint() throws Exception {
        assertNull(displayInfo.getMeetupPoint());

        displayInfo.insert(meetupPoint, date);
        assertEquals(displayInfo.getMeetupPoint(), meetupPoint);

    }
}