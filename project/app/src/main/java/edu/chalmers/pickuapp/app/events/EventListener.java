package edu.chalmers.pickuapp.app.events;

/**
 * Classes which listens to event needs to implement this interaface.
 */
public interface EventListener {
    void onEvent(Event e);
}
