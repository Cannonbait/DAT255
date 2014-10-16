package edu.chalmers.pickuapp.app.model;


import edu.chalmers.pickuapp.app.events.*;
import edu.chalmers.pickuapp.app.server.MockServer;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class MockServerTest extends Assert implements EventListener {

    private MockServer mockServer;
    private RouteData mockRouteData;
    private Date mockDate;
    private String mockID;

    @Before
    public void setUp(){
        mockServer = new MockServer();
        mockRouteData = new RouteData(new Coordinate(10,10),new Coordinate(20,20),new Date(),new Date());
        mockDate = new Date();
        mockID = "d";
    }

    @Test
    public void testOnEvent(){


        EventBus.INSTANCE.reportEvent(new StartMatchmakingEvent(mockDate,mockRouteData,mockID));
    }


    @Override
    public void onEvent(Event e) {
        assertEquals(DriverMatchFoundEvent.class,e.getClass());
    }
}