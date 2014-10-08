package edu.chalmers.pickuapp.app;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.*;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.format.DateFormat;
import android.util.*;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import edu.chalmers.pickuapp.app.events.*;
import edu.chalmers.pickuapp.app.events.EventListener;
import edu.chalmers.pickuapp.app.model.*;

import java.util.*;


public class MainActivity extends FragmentActivity implements EventListener{

    private static Model model = new Model();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);

        EventBus.INSTANCE.registerListener(this);
    }

    public void pickedDriver(View view){
		EventBus.INSTANCE.reportEvent(new PickedDriverEvent());
    }

	public void pickedHitchhiker(View view) {
		EventBus.INSTANCE.reportEvent(new PickedHitchhikerEvent());
	}

    public void driverAbortMatchmaking(View view) {
        EventBus.INSTANCE.reportEvent(new DriverAbortMatchmakingEvent());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

	@Override
	public void onEvent(Event event){
        if (event instanceof ChangeViewEvent) {
            ChangeViewEvent e = (ChangeViewEvent)event;
            Class clazz = e.sequenceClass == HitchhikerSetRoute.class ? HitchhikerSetRouteActivity.class : DriverSetRouteActivity.class ;
            Intent intent = new Intent(this, clazz);
            startActivity(intent);
            finish();
        }
	}


}//end MainActivity