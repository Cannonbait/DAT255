package edu.chalmers.pickuapp.app.events;

import edu.chalmers.pickuapp.app.events.*;
import edu.chalmers.pickuapp.app.model.*;

/**
 * Created by elvirajonsson on 2014-10-09.
 * Revised by Joakim Thorén on 2014-10-11.
 */
public class HitchhikerMatchFoundEvent implements Event {

	//Save whatever data required for displaying info about match
	//I.E: Time, location, Name of driver and hitchhiker etcetc
	
	public RouteData routeData;

	//Pass that data to this constructor
	public HitchhikerMatchFoundEvent(RouteData routeData) {
		this.routeData = new RouteData(routeData);
	}

}
