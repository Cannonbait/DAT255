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

    public void processEvent(Event e){

    }
}
