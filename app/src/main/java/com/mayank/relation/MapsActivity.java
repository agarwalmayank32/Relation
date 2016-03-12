package com.mayank.relation;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Firebase firebase;
    String latitude,longitude;
    double lat,lon;
    int value;
    String place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Firebase.setAndroidContext(getApplicationContext());
        firebase = new Firebase("https://relationalmanac1.firebaseio.com/");

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

        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            value = Integer.parseInt(extras.getString("KEY"));
            firebase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    place=String.valueOf(dataSnapshot.child(String.valueOf(value)).child("Name").getValue());
                    latitude=String.valueOf(dataSnapshot.child(String.valueOf(value)).child("Latitude").getValue());
                    longitude=String.valueOf(dataSnapshot.child(String.valueOf(value)).child("Longitude").getValue());
                    lat=Double.parseDouble(latitude);
                    lon=Double.parseDouble(longitude);
                    LatLng place1 = new LatLng(lat, lon);
                    mMap.addMarker(new MarkerOptions().position(place1).title(place));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(place1,13));
                }
                @Override
                public void onCancelled(FirebaseError firebaseError) {
                    Toast.makeText(MapsActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
