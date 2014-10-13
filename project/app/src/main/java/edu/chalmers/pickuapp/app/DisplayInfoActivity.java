package edu.chalmers.pickuapp.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.*;
import android.widget.TextView;
import edu.chalmers.pickuapp.app.events.DisplayInfoOKEvent;
import edu.chalmers.pickuapp.app.events.Event;
import edu.chalmers.pickuapp.app.events.EventBus;
import edu.chalmers.pickuapp.app.events.MeetupEvent;
import edu.chalmers.pickuapp.app.model.Coordinate;
import edu.chalmers.pickuapp.app.model.Date;


public class DisplayInfoActivity extends ChildActivity {

    private Coordinate meetupPoint;
    private Date date;
    private TextView meetupTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_info);
        meetupTime = (TextView) findViewById(R.id.meetup_info_time);
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
    }

    @Override
    public void processEvent(Event e){
        if(e instanceof MeetupEvent){
            meetupPoint = ((MeetupEvent) e).getMeetupPoint();
            date = ((MeetupEvent) e).getDate();
            setMeetupTimeText();
        }
    }
    public void setMeetupTimeText(){
        String dateText = date.day + "/" + date.month;
        String timeText = date.hour + ":" + date.minute;
        meetupTime.setText(dateText + "\n" + timeText);

    }

}
