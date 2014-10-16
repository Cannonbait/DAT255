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
		routeData = new RouteData(coordinate, coordinate, date, date);
		date = new Date(2014, 10, 16, 8, 20, 27);

		dr = (DriverResponse)Sequence.getSequence(DriverResponse.class);

		dr.insert(routeData, date);
	}

	@Test
	public void testOnStart(){
		boolean temp = dr.isDone();
		DriverPicksUpHitchhikerEvent dpu = new DriverPicksUpHitchhikerEvent(routeData, date);
		dr.processEvent(dpu);
		assertTrue(!temp && dr.isDone());
	}

	@Test
	public void testProssesEvent_Event(){
		//TODO
	}

	@Test
	public void testInsert_RouteData_Date(){
		RouteData tempRouteData = new RouteData(new Coordinate(0, 0), new Coordinate(0, 0), date, date);
		Date tempDate = new Date(0, 0, 0, 0, 0, 0);
		dr.insert(tempRouteData, tempDate);
		assertTrue(!(dr.getRouteData().equals(routeData)) && !(dr.getDate().equals(date)));
	}

	@Test
	public void testIsDone(){
		//Nothing done, should be false
		assertTrue(!dr.isDone());
		DriverPicksUpHitchhikerEvent drp = new DriverPicksUpHitchhikerEvent(routeData, date);

		dr.processEvent(drp);
		assertTrue(dr.isDone());
	}

	@Test
	public void testGetRouteData(){
		assertTrue(dr.getRouteData().equals(new RouteData(new Coordinate(2, 5), new Coordinate(2, 5), date, date)));
	}

	@Test
	public void testGetDate(){
		assertTrue(dr.getDate().equals(new Date(2014, 10, 16, 8, 20, 27)));
	}

	@Test
	public void testGetNextSequnce(){
		assertTrue(dr.getNextSequence() == null);

		DriverPicksUpHitchhikerEvent drPick = new DriverPicksUpHitchhikerEvent(routeData, date);
		dr.processEvent(drPick);
		assertEquals(dr.getNextSequence().getClass(), DisplayInfo.class);

		DriverDeclineKeepSearchEvent keepSearch = new DriverDeclineKeepSearchEvent();
		dr.processEvent(keepSearch);
		assertEquals(dr.getNextSequence().getClass(), DriverMatchmaker.class);

		DriverDeclineHitchhikerEvent drDecline = new DriverDeclineHitchhikerEvent();
		dr.processEvent(drDecline);
		assertEquals(dr.getNextSequence().getClass(), DriverSetRoute.class);
	}
}