package com.example.logan.bikerackfinder;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        float zoomLevel = 16; //This goes up to 21
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-36.852705, 174.763672), zoomLevel));

        ArrayList<BikeRack> bikeRacks = getBikeRacks();

        AddMarkers(bikeRacks);
    }

    private ArrayList<BikeRack> getBikeRacks(){
        ArrayList<BikeRack> bikeRacks = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(loadJSONFromAsset());
            String uRacks = jsonObject.getJSONArray("URacks").toString();
            Gson gson = new Gson();
            bikeRacks = gson.fromJson(uRacks, new TypeToken<ArrayList<BikeRack>>(){}.getType());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return bikeRacks;
    }

    private void AddMarkers(ArrayList<BikeRack> bikeRacks){
        for(int i = 0; i < bikeRacks.size();i++){
            BikeRack current = bikeRacks.get(i);
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(new LatLng(current.getLatitude(),current.getLongitude()));
            markerOptions.title(current.getTitle());
            markerOptions.snippet(current.getNumberOfRacks() + " " + (current.getIsSheltered()?"sheltered":"unsheltered") + " " + current.getType() + " Racks");
            mMap.addMarker(markerOptions);
        }
    }

    private String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}