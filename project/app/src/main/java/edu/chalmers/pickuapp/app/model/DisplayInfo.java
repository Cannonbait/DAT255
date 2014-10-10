package edu.chalmers.pickuapp.app.model;

import edu.chalmers.pickuapp.app.events.*;

/**
 * Created by elvirajonsson on 2014-10-09.
 */
public class DisplayInfo extends Sequence {
    private Sequence nextSequence;
    private Coordinate meetupPoint = null;
    private Date date = null;

    public DisplayInfo(){
        super();
    }
    //How do I get the meetup data from model to view?
    @Override
    public void processEvent(Event event) {
        if(event instanceof MeetupEvent){
            meetupPoint = ((MeetupEvent) event).getMeetupPoint();
            date = ((MeetupEvent) event).getDate();
        } else if(event instanceof DisplayInfoOKEvent){
            nextSequence = getSequence(Mode.class);
            isDone = true;
        }

    }

    @Override
    public Sequence getNextSequence() {
        return nextSequence;
    }

    @Override
    public void onStart(){
        super.onStart();
        meetupPoint = null;
        date = null;
    }

    public Date getDate(){
        return date;
    }

    public Coordinate getMeetupPoint(){
        return meetupPoint;
    }
}
