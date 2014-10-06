package edu.chalmers.pickuapp.app;

import android.app.Activity;
import android.content.*;
import android.os.Bundle;
import android.util.*;
import android.view.*;
import android.widget.*;


public class GenericActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        int layoutID = intent.getIntExtra("layoutID", R.layout.error);
        setContentView(layoutID);

    }

    public void doneWithPicking(View view){
        TimePicker timePicker = (TimePicker)findViewById(R.id.time_picker);
        Log.i("PickUApp", timePicker.getCurrentHour() + ":" + timePicker.getCurrentMinute());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.hitchhiker_set_route, menu);
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
