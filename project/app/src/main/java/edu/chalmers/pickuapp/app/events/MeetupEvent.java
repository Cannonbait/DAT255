package edu.chalmers.pickuapp.app.events;

import edu.chalmers.pickuapp.app.model.Coordinate;
import edu.chalmers.pickuapp.app.model.Date;

/**
 * Created by elvirajonsson on 2014-10-10.
 */
//This event is to be trown by the model containing the data on where and when the driver and HH should meet up
public class MeetupEvent implements Event {

    private Coordinate meetupPoint;
    private Coordinate dropOffPoint;
    private Date date;

    public MeetupEvent(Coordinate meetupPoint, Coordinate dropOffPoint, Date date){
        this.meetupPoint = meetupPoint;
        this.dropOffPoint = dropOffPoint;
        this.date = date;
    }

    public Coordinate getMeetupPoint(){
        return new Coordinate(meetupPoint.getLatitude(), meetupPoint.getLongitude());
    }

    public Coordinate getDropOffPoint(){
        return new Coordinate(dropOffPoint);
    }

    public Date getDate(){
        return new Date(date);
    }
}
