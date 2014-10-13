package edu.chalmers.pickuapp.app.model;

import edu.chalmers.pickuapp.app.events.*;

/**
 * Created by Tejp on 2014-10-13.
 */
public class ExitApp extends Sequence {

    @Override
    public void onStart() {
        EventBus.INSTANCE.reportEvent(new ExitAppEvent());
    }

    @Override
    public void processEvent(Event event) {

    }

    @Override
    public Sequence getPreviousSequence() {
        return null;
    }
}
