package edu.chalmers.pickuapp.app;

import android.app.*;
import android.os.Bundle;
import android.text.format.*;
import android.util.*;
import android.view.*;
import android.widget.*;
import com.google.android.gms.maps.*;
import edu.chalmers.pickuapp.app.model.*;
import edu.chalmers.pickuapp.app.events.*;

import java.util.*;


public class DriverMatchmakerActivity extends ChildActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_matchmaker);
    }


    public void abortMatchmaking(View view) {
        EventBus.INSTANCE.reportEvent(new AbortMatchmakingEvent());
        EventBus.INSTANCE.reportEvent(new ForwardClickedEvent());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        EventBus.INSTANCE.reportEvent(new AbortMatchmakingEvent());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.driver_matchmaker, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
