package edu.chalmers.pickuapp.app;

import android.app.*;
import android.os.Bundle;
import android.text.format.*;
import android.util.*;
import android.view.*;
import android.widget.*;
import com.google.android.gms.maps.*;
import edu.chalmers.pickuapp.app.model.*;

import java.util.*;


public class HitchhikerSetRouteActivity extends Activity {

    private EditText minute;
    private EditText hour;
    private EditText day;
    private EditText month;
    private EditText year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hitchhiker_set_route);

        minute = (EditText)findViewById(R.id.set_route_minute);
        hour = (EditText)findViewById(R.id.set_route_hour);
        day = (EditText)findViewById(R.id.set_route_day);
        month = (EditText)findViewById(R.id.set_route_month);
        year = (EditText)findViewById(R.id.set_route_year);
    }

    public void setRouteEditDate(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");

        Log.i("PickUApp", "Date");
    }
    public void setRouteEditTime(View view) {

        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");

        Log.i("PickUApp", "Time");
    }
    public void setRouteEditFrom(View view) {
        Log.i("PickUApp", "From");
    }
    public void setRouteEditTo(View view) {
        Log.i("PickUApp", "To");
    }
    public void doneWithPicking(View view) {
        Log.i("PickUApp",
                String.format("%s:%s %s/%s-%s",
                        minute.getText(),
                        hour.getText(),
                        day.getText(),
                        month.getText(),
                        year.getText()
                )
        );
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

    public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

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

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            HitchhikerSetRouteActivity.this.hour.setText(hourOfDay + "");
            HitchhikerSetRouteActivity.this.minute.setText(minute + "");
        }
    }

    public class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            HitchhikerSetRouteActivity.this.day.setText(day + "");
            HitchhikerSetRouteActivity.this.month.setText(month + "");
            HitchhikerSetRouteActivity.this.year.setText(year + "");
        }
    }
}
