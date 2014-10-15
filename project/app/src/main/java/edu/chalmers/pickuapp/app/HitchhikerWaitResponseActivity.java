package edu.chalmers.pickuapp.app;

import android.app.*;
import android.os.Bundle;
import android.text.format.*;
import android.util.*;
import android.view.*;
import android.widget.*;
import edu.chalmers.pickuapp.app.model.*;
import edu.chalmers.pickuapp.app.events.*;
import java.util.*;


public class HitchhikerWaitResponseActivity extends ChildActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hitchhiker_wait_response);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.hitchhiker_wait_response, menu);
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
