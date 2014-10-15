package edu.chalmers.pickuapp.app.model;

import android.util.Log;
import edu.chalmers.pickuapp.app.events.*;

/**
 * Created by elvirajonsson on 2014-10-09.
 */
public class DisplayInfo extends Sequence {
    private Coordinate meetupPoint = null;
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
        return getSequence(Mode.class); //TODO where should this goto?
    }

    @Override
    public void onStart(){
        super.onStart();
        EventBus.INSTANCE.reportEvent(new MeetupEvent(meetupPoint, date));
    }

    public void insert(Coordinate meetupPoint, Date date){
        this.meetupPoint = meetupPoint;
        this.date = date;
    }

    public Date getDate(){
        return date;
    }

    public Coordinate getMeetupPoint(){
        return meetupPoint;
    }
}
