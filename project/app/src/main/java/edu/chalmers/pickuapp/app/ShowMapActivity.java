package edu.chalmers.pickuapp.app;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import edu.chalmers.pickuapp.app.events.Event;
import edu.chalmers.pickuapp.app.model.Coordinate;
import edu.chalmers.pickuapp.app.model.DriverResponse;

public class ShowMapActivity extends ChildActivity {

	private GoogleMap googleMap;
	private LatLng source;
	private LatLng destination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_map);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
		createMapIfNeeded();
    }

	@Override
	public void onResume(){
		createMapIfNeeded();
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.show_map, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_show_map, container, false);
            return rootView;
        }
    }

	private void createMapIfNeeded(){
		try {
			if (googleMap == null) {
				googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.google_map)).getMap();
			}
			//googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void processEvent(Event event){
		/* TODO- which Event to us?
		if(event instanceof RandomEvent){
			source = new LatLng(RandomEvent.getSourceCoordinate().getLatitude(), RandomEvent.getDestinationCoordinate().getLongitude());
			destination = new LatLng(RandomEvent.getSourceCoordinate().getLatitude() RandomEvent.getDestinationCoordinate().getLongitude());

			googleMap.addMarker(new MarkerOptions().title("source").position(source).snippet("Place to pick up HH"));
			googleMap.addMarker(new MarkerOptions().title("destination").position(destination).snippet("Place to drop of HH"));
		}
		*/
	}
}