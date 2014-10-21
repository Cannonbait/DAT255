package edu.chalmers.pickuapp.app.model;

import android.util.Log;
import edu.chalmers.pickuapp.app.events.*;

/**
 * Created by elvirajonsson on 2014-10-09.
 */
public class DisplayInfo extends Sequence {
    private Coordinate meetupPoint = null;
    private Coordinate dropOffPoint = null;
    private Date date = null;

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
        EventBus.INSTANCE.reportEvent(new MeetupEvent(meetupPoint, dropOffPoint, date));
    }

    /**
     * Method to insert the data needed for the sequence
     *
     * @param meetupPoint The coordinate on which the driver and hitchhiker shouls meet up
     * @param dropOffPoint
     * @param date
     */
    public void insert(Coordinate meetupPoint, Coordinate dropOffPoint, Date date){
        this.meetupPoint = meetupPoint;
        this.dropOffPoint = dropOffPoint;
        this.date = date;
    }

    public Date getDate(){
        return new Date(date);
    }

    public Coordinate getMeetupPoint(){
        return new Coordinate(meetupPoint);
    }

    public Coordinate getDropOffPoint(){
        return new Coordinate(dropOffPoint);
    }
}
