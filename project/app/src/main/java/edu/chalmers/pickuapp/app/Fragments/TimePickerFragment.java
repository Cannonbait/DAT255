package edu.chalmers.pickuapp.app.Fragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import edu.chalmers.pickuapp.app.model.Date;

import java.util.Calendar;

/**
 * Created by cannonbait on 2014-10-14.
 */
public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    private final TextView view;
    private final Date date;

    public TimePickerFragment(View view, Date date){
        super();
        this.view = (TextView)view;
        this.date = date;
    }

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

        this.view.setText(String.format("%02d:%02d", hourOfDay, minute));
        date.hour = hourOfDay;
        date.minute = minute;
    }
}