package edu.chalmers.pickuapp.app.model;

import edu.chalmers.pickuapp.app.events.*;
import junit.framework.Assert;
import org.junit.*;
import org.junit.runner.*;
import org.junit.runners.*;

@RunWith(JUnit4.class)
public class EventBusTest extends Assert {

    private boolean eventRecieved = false;
    private TestEventListener listener = new TestEventListener();
    @Before
    public void setUp() {
        EventBus.INSTANCE.registerListener(listener);
    }


    @Test
    public void testReportEvent() {

        Boolean startValue = listener.getCurrentBoolValue();
        EventBus.INSTANCE.reportEvent(new TestBooleanDataEvent(true));
        assertNotSame(startValue, listener.getCurrentBoolValue());
        assertEquals(true, listener.getCurrentBoolValue());
    }

    private class TestBooleanDataEvent implements Event {
        private boolean boolValue;

        private TestBooleanDataEvent(boolean boolValue) {
            this.boolValue = boolValue;
        }

        public boolean isBoolValue() {
            return boolValue;
        }

        public void setBoolValue(boolean boolValue) {
            this.boolValue = boolValue;
        }
    }

    private class TestEventListener implements EventListener {

        private boolean reportedBoolValue;

        @Override
        public void onEvent(Event e) {
            if (e instanceof TestBooleanDataEvent) {
                reportedBoolValue = ((TestBooleanDataEvent)e).boolValue;
            }
        }

        public boolean getCurrentBoolValue() {
            return reportedBoolValue;
        }
    }
}
