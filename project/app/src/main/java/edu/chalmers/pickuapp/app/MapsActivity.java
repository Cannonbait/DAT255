package edu.chalmers.pickuapp.app;

import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import edu.chalmers.pickuapp.app.utils.GoogleMapsHelper;

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
    public static final String INTENT_ADRESS_STRING_KEY = "adress";
    public static final String INTENT_CORDS_KEY = "cords";
    private LatLng latLng;

    private String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Intent intent = getIntent();
        double[] posArr = intent.getDoubleArrayExtra(INTENT_START_CORDS_KEY);

        final GoogleMap map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        map.getUiSettings().setMyLocationButtonEnabled(true);
        map.setMyLocationEnabled(true);
        if (posArr != null ) {
        // Show start position gotten from intent
            LatLng markerPos = new LatLng(posArr[0], posArr[1]);
            map.addMarker(new MarkerOptions().position(markerPos).title("Origin").snippet("Place to pick up hitchhiker."));
			if(posArr.length > 2) {
				//Show position of destination with a marker
				LatLng markerPosDestination = new LatLng(posArr[2], posArr[3]);
				map.addMarker(new MarkerOptions().position(markerPosDestination).title("Destination").snippet("Place to drop of hitchhiker."));
			}

            CameraUpdate cameraPos = CameraUpdateFactory.newLatLngZoom(markerPos, 5);
            map.animateCamera(cameraPos);
        } else { //No position available from intent. Act as a selector of position

            map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {
                    MapsActivity.this.latLng = latLng;

                    address = GoogleMapsHelper.getAdressfromCoord(latLng);

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
            returnIntent.putExtra(INTENT_ADRESS_STRING_KEY, address);
            setResult(RESULT_OK, returnIntent);
        }

        super.onBackPressed();
    }
}
