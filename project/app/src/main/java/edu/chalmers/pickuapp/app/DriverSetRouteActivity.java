package edu.chalmers.pickuapp.app;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import edu.chalmers.pickuapp.app.Fragments.DatePickerFragment;
import edu.chalmers.pickuapp.app.Fragments.TimePickerFragment;
import edu.chalmers.pickuapp.app.events.*;
import edu.chalmers.pickuapp.app.model.Coordinate;
import edu.chalmers.pickuapp.app.model.Date;
import edu.chalmers.pickuapp.app.model.RouteData;
import edu.chalmers.pickuapp.app.utils.GoogleMapsHelper;


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

        destination = new Coordinate(70, 70);
        startDate = new Date();
        stopDate = new Date();
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
            RouteData routeData = ((SetDefaultRouteDataEvent)event).routeData;
            startDate = routeData.getStartDate();
            stopDate = routeData.getStopDate();
            origin = routeData.getOrigin();
            destination = routeData.getDestination();
            originDateView.setText(String.format("%d/%d/%d",startDate.year, startDate.month, startDate.day));
            originTimeView.setText(String.format("%d:%d", startDate.hour, startDate.minute));
            String locationString = GoogleMapsHelper.getAdressfromCoord(origin.getLatitude(), origin.getLongitude());
            originEditText.setText(locationString);
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
