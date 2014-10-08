package edu.chalmers.pickuapp.app;

import android.app.*;
import android.os.*;

/**
 * Created by Tejp on 2014-10-08.
 */
public class ChildActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MasterActivity.setCurrentRunningChild(this);
    }
}
