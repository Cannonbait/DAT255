package edu.chalmers.pickuapp.app.Fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import edu.chalmers.pickuapp.app.model.Date;

import java.util.Calendar;

/**
 * Created by cannonbait on 2014-10-14.
 */
public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    private final TextView view;
    private final Date date;

    public DatePickerFragment(View view, Date date){
        super();
        this.view = (TextView)view;
        this.date = date;
    }
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

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        this.view.setText(Integer.toString(year) + "/" + Integer.toString(month) + "/" + Integer.toString(day));
        date.year = year;
        date.month = month;
        date.day = day;
    }
}