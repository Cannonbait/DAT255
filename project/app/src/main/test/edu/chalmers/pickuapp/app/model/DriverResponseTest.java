package edu.chalmers.pickuapp.app.model;

import static org.junit.Assert.*;

import edu.chalmers.pickuapp.app.events.EventBus;
import edu.chalmers.pickuapp.app.events.DriverDeclineHitchhikerEvent;
import edu.chalmers.pickuapp.app.events.DriverPicksUpHitchhikerEvent;
import edu.chalmers.pickuapp.app.events.DriverDeclineKeepSearchEvent;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.Exception;

public class DriverResponseTest extends Assert{

	Model model;
	DriverResponse dr;
	Coordinate coordinate;
	RouteData routeData;
	Date date;

	@Before
	public void setUp() throws Exception{
		model = new Model();
		coordinate = new Coordinate(2, 5);
        date = new Date(2014, 10, 16, 8, 20, 27);
        routeData = new RouteData(coordinate, coordinate, date, date);

		dr = (DriverResponse)Sequence.getSequence(DriverResponse.class);

		dr.insert(routeData);
	}


	@Test
	public void testOnStart(){
      	boolean temp = dr.isDone();
		DriverPicksUpHitchhikerEvent dpu = new DriverPicksUpHitchhikerEvent(routeData);
		dr.processEvent(dpu);
		assertTrue(!temp && dr.isDone());
	}

	@Test
	public void testProssesEvent_Event(){

		DriverPicksUpHitchhikerEvent drPick = new DriverPicksUpHitchhikerEvent(routeData);
		dr.processEvent(drPick);
		assertEquals(dr.getNextSequence().getClass(), DisplayInfo.class);
		assertTrue(dr.isDone());

		//set variable isDone to false
		dr.onStart();
		DriverDeclineKeepSearchEvent keepSearch = new DriverDeclineKeepSearchEvent();
		dr.processEvent(keepSearch);
		assertEquals(dr.getNextSequence().getClass(), DriverMatchmaker.class);
		assertTrue(dr.isDone());

		dr.isDone();
		DriverDeclineHitchhikerEvent drDecline = new DriverDeclineHitchhikerEvent();
		dr.processEvent(drDecline);
		assertEquals(dr.getNextSequence().getClass(), DriverSetRoute.class);
		assertTrue(dr.isDone());
	}

	@Test
	public void testSetSequenceDoneAndReportForward(){
		//Is tested by other, public, methods
	}

	@Test
	public void testGetBackSequence(){
		assertEquals(dr.getBackSequence().getClass(), Mode.class);
	}

	@Test
	public void testInsert_RouteData_Date(){
		RouteData tempRouteData = new RouteData(new Coordinate(0, 0), new Coordinate(0, 0), date, date);
		Date tempDate = new Date(0, 0, 0, 0, 0, 0);
		dr.insert(tempRouteData);
		assertTrue(!(dr.getRouteData().equals(routeData)));
	}


	@Test
	public void testIsDone(){
		//Nothing done, should be false
		assertTrue(!dr.isDone());
		DriverPicksUpHitchhikerEvent drp = new DriverPicksUpHitchhikerEvent(routeData);

		dr.processEvent(drp);
		assertTrue(dr.isDone());
	}

	@Test
	public void testGetRouteData(){
		assertTrue(dr.getRouteData().equals(new RouteData(new Coordinate(2, 5), new Coordinate(2, 5), date, date)));
	}

    @Test
	public void testGetNextSequnce(){
		assertTrue(dr.getNextSequence() == null);

		DriverPicksUpHitchhikerEvent drPick = new DriverPicksUpHitchhikerEvent(routeData);
		dr.processEvent(drPick);
		assertEquals(dr.getNextSequence().getClass(), DisplayInfo.class);

		DriverDeclineKeepSearchEvent keepSearch = new DriverDeclineKeepSearchEvent();
		dr.processEvent(keepSearch);
		assertEquals(dr.getNextSequence().getClass(), DriverMatchmaker.class);

		DriverDeclineHitchhikerEvent drDecline = new DriverDeclineHitchhikerEvent();
		dr.processEvent(drDecline);
		assertEquals(dr.getNextSequence().getClass(), DriverSetRoute.class);
	}
}//end DriverResponseTest