package edu.chalmers.pickuapp.app.events;

import edu.chalmers.pickuapp.app.model.Coordinate;
import edu.chalmers.pickuapp.app.model.Date;

/**
 * Created by cannonbait on 2014-10-13.
 */
public class SetRouteEvent implements Event{
    private final Date fromDate;
    private final Date toDate;
    private final Coordinate fromCoordinates;
    private final Coordinate toCoordinates;

    public SetRouteEvent(Date fromDate, Date toDate, Coordinate fromCoordinates, Coordinate toCoordinates){
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.fromCoordinates = fromCoordinates;
        this.toCoordinates = toCoordinates;
    }


}
