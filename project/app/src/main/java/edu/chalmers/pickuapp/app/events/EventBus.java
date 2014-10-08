package edu.chalmers.pickuapp.app.events;

import android.util.*;

import java.util.*;

/**
 * EventBuss handels connections between Listeners and occuring events
 */
public enum EventBus {
    INSTANCE;

    private Set<EventListener> listeners = new HashSet<EventListener>();

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
