package edu.chalmers.pickuapp.app;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.swedspot.automotiveapi.AutomotiveSignal;
import android.swedspot.automotiveapi.AutomotiveSignalId;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.swedspot.automotiveapi.AutomotiveFactory;
import com.swedspot.automotiveapi.AutomotiveListener;
import com.swedspot.vil.distraction.DriverDistractionLevel;
import com.swedspot.vil.distraction.DriverDistractionListener;
import com.swedspot.vil.policy.AutomotiveCertificate;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}
