package edu.chalmers.pickuapp.app.events;

import java.util.ArrayList;
import java.util.List;

/**
 * EventBuss handels connections between Listeners and occuring events
 */
public enum EventBus {
    INSTANCE;

    private List<EventListener> listeners = new ArrayList<EventListener>();

    public void registerListener(EventListener listener) {
        listeners.add(listener);
    }

    public void removeListener(EventListener listener) {
        listeners.remove(listener);
    }

    public void reportEvent(Event e) {
        for (EventListener listener : listeners) {
            listener.onEvent(e);
        }
    }
}
