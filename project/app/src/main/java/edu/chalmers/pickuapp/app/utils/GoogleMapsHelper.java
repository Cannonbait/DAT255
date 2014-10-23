package edu.chalmers.pickuapp.app.utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import com.google.android.gms.maps.model.LatLng;
import edu.chalmers.pickuapp.app.model.Coordinate;

import java.io.IOException;
import java.util.List;

/**
 * Created by tejp on 17/10/14.
 *
 * make sure GoogleMapsHelperInit has been called before using this class.
 */
public enum GoogleMapsHelper {
    INSTANCE;

    private static Geocoder geocoder;
    private static LocationManager lm;

    public static void initGoogleMapsHelper(Context context) {
        geocoder = new Geocoder(context); //new Geocoder(this, Locale.getDefault());
        lm = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
    }

    /**
     *
     * @param latLng map coordinate
     * @return address att location with as much specification as possible by google
     */
    public static String getAdressfromCoord(LatLng latLng) {
        return getAdressfromCoord(latLng.latitude, latLng.longitude);
    }

    /**
     *
     * @param latitude
     * @param longitude
     * @return address att location with as much specification as possible by google
     */
    public static String getAdressfromCoord(double latitude, double longitude) {
        String address;
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);

            if (addresses != null && !addresses.isEmpty()) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder();
                int addressLineIndex = returnedAddress.getMaxAddressLineIndex();

                int addressLinesToShow = 2;
                //              To get address in limited lines
                if (addressLineIndex < 2) {
                    addressLinesToShow = addressLineIndex;
                }
                for (int p = 0; p < addressLinesToShow; p++) {
                    strReturnedAddress
                            .append(returnedAddress.getAddressLine(p)).append(
                            "\n");
                }
                address = strReturnedAddress.toString().trim();
            } else {
                address = "Address not available";

            }
        } catch (IOException e) {
            return null;
        }
        return address;
    }

    public static Location getMyLocation() {
        return lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    }
}
