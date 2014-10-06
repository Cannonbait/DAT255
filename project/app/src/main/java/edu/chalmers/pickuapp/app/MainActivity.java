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

    private static final String RESOURCE_ID_KEY = "layoutID";

    private static Model model = new Model();

    Map<Class<? extends Sequence>, Integer> sequenceViewresources = new HashMap<Class<? extends Sequence>, Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        int resourceID = intent.getIntExtra(RESOURCE_ID_KEY, R.layout.activity_mode);
        setContentView(resourceID);

        setupSequenceViewsresources();

        EventBus.INSTANCE.registerListener(this);
		/*
        new AsyncTask(){

            @Override
            protected Object doInBackground(Object[] objects) {
                AutomotiveFactory.createAutomotiveManagerInstance(new AutomotiveCertificate(new byte[0]),

                        new AutomotiveListener() {
                            @Override
                            public void receive(AutomotiveSignal automotiveSignal) {
                                Log.i("PickUApp", "Signal recieved");
                            }

                            @Override
                            public void timeout(int i) {

                            }

                            @Override
                            public void notAllowed(int i) {

                            }
                        }, new DriverDistractionListener() {
                    @Override
                    public void levelChanged(DriverDistractionLevel driverDistractionLevel) {
                        Log.i("PickUApp", "Level changed");
                    }
                }).register(AutomotiveSignalId.FMS_CURRENT_GEAR);
                return null;
            }
        }.execute();
        */

    }

    private void setupSequenceViewsresources() {
        sequenceViewresources.put(Mode.class, R.layout.activity_mode);
        sequenceViewresources.put(HitchhikerSetRoute.class, R.layout.hitchhiker_set_route);
    }

    public void pickedDriver(View view){
		EventBus.INSTANCE.reportEvent(new PickedDriverEvent());
    }

	public void pickedHitchhiker(View view) {
		EventBus.INSTANCE.reportEvent(new PickedHitchhikerEvent());
	}

    public void doneWithPicking(View view){
        Log.i("PickUApp", "tjobalahop");
        TimePicker timePicker = (TimePicker)findViewById(R.id.time_picker);
        DatePicker datePicker = (DatePicker)findViewById(R.id.date_picker);
        Log.i("PickUApp", String.format("%d:%d %d/%d-%d",
                timePicker.getCurrentHour(),
                timePicker.getCurrentMinute(),
                datePicker.getDayOfMonth(),
                datePicker.getMonth(),
                datePicker.getYear()) );
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
            int resourceID = sequenceViewresources.get(((ChangeViewEvent)event).sequenceClass);
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(RESOURCE_ID_KEY, resourceID);
            startActivity(intent);
            finish();

        }
	}


}//end MainActivity