package edu.chalmers.pickuapp.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import edu.chalmers.pickuapp.app.events.*;
import edu.chalmers.pickuapp.app.model.Date;
import edu.chalmers.pickuapp.app.model.RouteData;


public class DriverResponseActivity extends ChildActivity{

	private RouteData routeData;
	private Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_response);
		TextView timeAndDate = (TextView) findViewById(R.id.time_and_date);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.driver_response, menu);
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

	public void showMeetingPointOnMap(View view){
		//TODO- create activity or fragment to show map
		Intent intent = new Intent(this, MapsActivity.class);
		startActivityForResult(intent, 1);
	}

	public void yesSelected(View view){
		EventBus.INSTANCE.reportEvent(new DriverPicksUpHitchhikerEvent(routeData, date));
	}

	public void noKeepSearchingSelected(View view){
		EventBus.INSTANCE.reportEvent(new DriverDeclineKeepSearchEvent());
	}

	public void noStopSearchingSelected(View view){
		EventBus.INSTANCE.reportEvent(new DriverDeclineHitchhikerEvent());
	}

	@Override
	public void processEvent(Event e) {
		/* TODO- which event?
		if(e instanceof Whatever){
			Whatever wtf= new Whatever(((Whatever)e));
			routeData = wtf.getRouterData();
			date = wtf.getDate();
			timeAndDate.setText(String.format("%s:%s %s/%s-%s", date.hour, date.minute, date.day, date.month, date.year));
		}
		*/

	}
}