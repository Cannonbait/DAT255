package edu.chalmers.pickuapp.app;

import android.content.*;
import android.os.*;
import android.support.v4.app.*;
import android.util.*;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

/**
 * Created by Tejp on 2014-10-13.
 *
 *
 * How to use this activity to display a position:
 *
 * Intent intent = new Intent(this, MapsActivity.class);
 * intent.putExtra(MapsActivity.INTENT_START_CORDS_KEY, new double[]{50.0, 30.0}); //change theese coordinates accordingly
 * startActivity(intent);
 */
public class MapsActivity extends FragmentActivity {

    public static final String INTENT_START_CORDS_KEY = "start_cords";
    public static final String INTENT_CORDS_KEY = "coords";
    private LatLng latLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Intent intent = getIntent();
        double[] posArr = intent.getDoubleArrayExtra(INTENT_START_CORDS_KEY);

        final GoogleMap map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        if (posArr != null ) {
            map.addMarker(new MarkerOptions().position(new LatLng(posArr[0], posArr[1])));
        } else {
            map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {
                    MapsActivity.this.latLng = latLng;
                    map.clear();
                    map.addMarker(new MarkerOptions().position(latLng));
                }
            });
        }

    }

    @Override
    public void onBackPressed() {
        if (latLng != null) {
            Intent returnIntent = getIntent();
            returnIntent.putExtra(MapsActivity.INTENT_CORDS_KEY, new double[]{latLng.latitude, latLng.longitude});
            setResult(RESULT_OK, returnIntent);
        }

        super.onBackPressed();
    }
}
