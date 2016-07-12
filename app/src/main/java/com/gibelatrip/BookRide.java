package com.gibelatrip;

import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.gibelatrip.adapter.GeocoderAdapter;
import com.mapbox.geocoder.service.models.GeocoderFeature;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.constants.Style;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.location.LocationListener;
import com.mapbox.mapboxsdk.location.LocationServices;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

public class BookRide extends AppCompatActivity {

    private Button btnTime, btnFare, btnBook;
    private EditText contactNumber, messageToDriver, promoCode;
    private AutoCompleteTextView PickUp, Destination;

    private MapView mMapView;
    private MapboxMap map;
    private LocationServices locationServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_ride);

        PickUp = (AutoCompleteTextView) findViewById(R.id.pick_up);
        Destination = (AutoCompleteTextView) findViewById(R.id.drop_off);
        btnTime = (Button) findViewById(R.id.time);
        btnFare = (Button) findViewById(R.id.fare);

        btnBook = (Button) findViewById(R.id.book_trip);

        mMapView = (MapView) findViewById(R.id.mapview);
        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(MapboxMap mapboxMap) {
                map = mapboxMap;
                mapboxMap.setStyleUrl(Style.LIGHT);
                setLocation(mapboxMap);

            }
        });

        final GeocoderAdapter adapter = new GeocoderAdapter(this);
        PickUp.setLines(1);
        PickUp.setAdapter(adapter);
        PickUp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GeocoderFeature result = adapter.getItem(position);
                PickUp.setText(result.getText());
                //updateMap(result.getLatitude(), result.getLongitude());
            }
        });


        Destination.setLines(1);
        Destination.setAdapter(adapter);
        Destination.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GeocoderFeature result = adapter.getItem(position);
                Destination.setText(result.getText());
                //updateMap(result.getLatitude(), result.getLongitude());
            }
        });

    }

    public void setLocation(final MapboxMap mapboxMap) {
        locationServices.addLocationListener(new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if (location != null) {
                    // Move the map camera to where the user location is
                    mapboxMap.setCameraPosition(new CameraPosition.Builder()
                            .target(new LatLng(location))
                            .zoom(16)
                            .build());
                    //setCarsInMap(mapboxMap, location);
                }
            }
        });
    }

}
