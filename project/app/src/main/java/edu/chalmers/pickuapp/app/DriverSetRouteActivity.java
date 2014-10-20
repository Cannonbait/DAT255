package edu.chalmers.pickuapp.app;

import android.app.*;
import android.content.*;
import android.os.Bundle;
import android.text.format.*;
import android.util.*;
import android.view.*;
import android.widget.*;
import edu.chalmers.pickuapp.app.Fragments.DatePickerFragment;
import edu.chalmers.pickuapp.app.Fragments.TimePickerFragment;
import edu.chalmers.pickuapp.app.events.*;
import edu.chalmers.pickuapp.app.model.Coordinate;
import edu.chalmers.pickuapp.app.model.Date;
import edu.chalmers.pickuapp.app.model.RouteData;



public class DriverSetRouteActivity extends ChildActivity {

    private EditText originEditText;
    private EditText destinationEditText;
    private TextView originTimeView;
    private TextView originDateView;

    private Date startDate;
    private Date stopDate;
    private Coordinate origin;
    private Coordinate destination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_route);

        originEditText = (EditText) findViewById(R.id.set_from_input);
        destinationEditText= (EditText) findViewById(R.id.set_to_input);
        originTimeView = (TextView) findViewById(R.id.set_from_time);
        originDateView = (TextView) findViewById(R.id.set_from_date);

        startDate = new Date();
        stopDate = new Date();
        origin = new Coordinate(60, 60);
        destination = new Coordinate(70, 70);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.i("PickUApp", "activityReturnResult" + resultCode + " " + RESULT_OK + " " + RESULT_CANCELED + " " + RESULT_FIRST_USER);

        if (resultCode == RESULT_OK) {
            double[] latLon = data.getDoubleArrayExtra(MapsActivity.INTENT_CORDS_KEY);
            if (requestCode == 1) {
                originEditText.setText(data.getStringExtra(MapsActivity.INTENT_ADRESS_STRING_KEY));
                origin.setLatitude(latLon[0]);
                origin.setLongitude(latLon[1]);

            } else if (requestCode == 2) {
                destinationEditText.setText(data.getStringExtra(MapsActivity.INTENT_ADRESS_STRING_KEY));
                destination.setLatitude(latLon[0]);
                destination.setLongitude(latLon[1]);
            }
        }
    }

    @Override
    public void processEvent(Event event) {
        if(event instanceof SetDefaultRouteDataEvent) {
            Log.i("PickUApp", "Recieved SetDefaultRouteDataEvent in DriverSetRouteActivity");
            RouteData routeData = ((SetDefaultRouteDataEvent)event).routeData;
            Date date = routeData.getStartDate();
            originDateView.setText(date.year + "/" + date.month + "/" + date.day);
            originTimeView.setText(date.hour + ":" + date.minute);
        }
    }

    public void pickOrigin(View view){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivityForResult(intent, 1);
    }

    public void pickDestination(View view){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivityForResult(intent, 2);
    }

    public void pickStartDate(View view){
        DialogFragment newFragment = new DatePickerFragment(view, startDate);
        newFragment.show(getFragmentManager(), "datePicker");
    }

    public void pickStartTime(View view){
        DialogFragment newFragment = new TimePickerFragment(view, startDate);
        newFragment.show(getFragmentManager(), "timePicker");
    }

    public void pickStopDate(View view){
        DialogFragment newFragment = new DatePickerFragment(view, stopDate);
        newFragment.show(getFragmentManager(), "datePicker");
    }

    public void pickStopTime(View view){
        DialogFragment newFragment = new TimePickerFragment(view, stopDate);
        newFragment.show(getFragmentManager(), "timePicker");
    }

    public void done(View view){
        EventBus.INSTANCE.reportEvent(new SetRouteEvent(new RouteData(origin,destination, startDate, stopDate)));
        EventBus.INSTANCE.reportEvent(new ForwardClickedEvent());
    }




}
