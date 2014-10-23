package edu.chalmers.pickuapp.app.model;

import android.util.Log;
import edu.chalmers.pickuapp.app.events.*;

/**
 * Created by elvirajonsson on 2014-10-09.
 */
public class DisplayInfo extends Sequence {

    private RouteData routeData;

    public DisplayInfo(){
        super();
    }
    //How do I get the meetup data from model to view?
    @Override
    public void processEvent(Event event) {
         if(event instanceof DisplayInfoOKEvent){
            nextSequence = getSequence(Mode.class);
            isDone = true;
        }
    }

    @Override
    public Sequence getBackSequence() {
        return getSequence(Mode.class);
    }

    @Override
    public void onStart(){
        super.onStart();
		EventBus.INSTANCE.reportEvent(new MeetupEvent(routeData));
    }

	/**
	 * Method to insert the data needed for the sequence
	 *
	 * @param routeData The coordinate on which the driver and hitchhiker should meet up
	 */
    public void insert(RouteData routeData){
        this.routeData = routeData;
    }

    public RouteData getRouteData(){
		return routeData;
	}
}
