package edu.chalmers.pickuapp.app;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import edu.chalmers.pickuapp.app.model.Coordinate;
import android.os.Bundle;
import android.view.*;
import android.widget.TextView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import edu.chalmers.pickuapp.app.events.*;
import edu.chalmers.pickuapp.app.model.Date;


public class DisplayInfoActivity extends ChildActivity {

    private Coordinate meetupPoint;
    private Coordinate dropOffPoint;
    private Date date;
    private TextView meetupTime;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_info);
        meetupTime = (TextView) findViewById(R.id.meetup_info_time);
        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        map.setMyLocationEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.display_info, menu);
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
    public void okButtonClicked(View view){
        EventBus.INSTANCE.reportEvent(new DisplayInfoOKEvent());
        EventBus.INSTANCE.reportEvent(new ForwardClickedEvent());
    }

    @Override
    public void processEvent(Event e){
        if(e instanceof MeetupEvent){
            meetupPoint = ((MeetupEvent) e).getRouteData().getOrigin();
			dropOffPoint = ((MeetupEvent) e).getRouteData().getDestination();
            date = ((MeetupEvent) e).getRouteData().getStartDate();
            setMeetupTimeText();
            showMeetupPoint();
        }
    }
    public void setMeetupTimeText(){
        String dateText = date.day + "/" + date.month;
        String timeText = date.hour + ":" + date.minute;
        meetupTime.setText(dateText + "\n" + timeText);

    }

    public void showMeetupPoint(){
        LatLng startPosition = new LatLng(meetupPoint.getLatitude(), meetupPoint.getLongitude());
        LatLng endPosition = new LatLng(dropOffPoint.getLatitude(), dropOffPoint.getLongitude());


        CameraUpdate location = CameraUpdateFactory.newLatLngZoom(startPosition, 10f);
        map.addMarker(new MarkerOptions().position(startPosition));
        map.addMarker(new MarkerOptions().position(endPosition));
        map.animateCamera(location);

    }

}
