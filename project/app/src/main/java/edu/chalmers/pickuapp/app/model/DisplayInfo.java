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
<<<<<<< HEAD
        EventBus.INSTANCE.reportEvent(new MeetupEvent(meetupPoint, dropOffPoint, date));
    }

    /**
     * Method to insert the data needed for the sequence
     *
     * @param meetupPoint The place on which the driver and hitchhiker should meet up
     * @param dropOffPoint The place on which the hitchhiker should be dropped off
     * @param date When to meet
     */
    public void insert(Coordinate meetupPoint, Coordinate dropOffPoint, Date date){
        this.meetupPoint = meetupPoint;
        this.dropOffPoint = dropOffPoint;
        this.date = date;
=======
        EventBus.INSTANCE.reportEvent(new MeetupEvent(routeData));
>>>>>>> Fixing
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
