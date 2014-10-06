package edu.chalmers.pickuapp.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;


public class DriverSetRouteActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hitchhiker_set_route); //TODO change the resource once a resource for DriverSetRoute is available
    }

    public void doneWithPicking(View view) {
        DatePicker datePicker = (DatePicker)findViewById(R.id.date_picker );
        TimePicker timePicker = (TimePicker)findViewById(R.id.time_picker);

        System.out.println(
                String.format("%d:%d %d/%d-%d",
                        timePicker.getCurrentHour(),
                        timePicker.getCurrentMinute(),
                        datePicker.getDayOfMonth(),
                        datePicker.getMonth(),
                        datePicker.getYear() )
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.driver_set_route, menu);
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
