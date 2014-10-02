package edu.chalmers.pickuapp.app;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TimePicker;
import edu.chalmers.pickuapp.app.events.*;
import edu.chalmers.pickuapp.app.model.*;
import java.util.Calendar;


public class MainActivity extends FragmentActivity implements EventListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);

        Model model = new Model();
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

	public void pickedDriver(View view){
		EventBus.INSTANCE.reportEvent(new PickedDriverEvent());
	}

	public void pickedHitchhiker(View view) {
        //THIS OPENS A TIMEPICKER WHEN YOU PRESS HITCHHIKER
        //KOMMENTERA BORT OM DU VILL GÖRA ANNAT MED METODEN :)
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");

		EventBus.INSTANCE.reportEvent(new PickedHitchhikerEvent());
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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


    //Class to pick the date and time in setRoute
    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user
        }
    }

	@Override
	public void onEvent(Event event){

	}
}//end MainActivity