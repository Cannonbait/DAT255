package edu.chalmers.pickuapp.app;

import android.app.*;
import android.os.*;
import edu.chalmers.pickuapp.app.events.*;

/**
 * Created by Tejp on 2014-10-08.
 */
public class ChildActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MasterActivity.setCurrentRunningChild(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.INSTANCE.reportEvent(new OnStartEvent());
    }

    public void processEvent(Event e){

    }

    // Child-activities closes when the master says they do.
    @Override
    public void onBackPressed() {
        EventBus.INSTANCE.reportEvent(new BackClickedEvent());
    }
}
