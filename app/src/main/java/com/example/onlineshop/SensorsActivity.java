package com.example.onlineshop;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.Location;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnSuccessListener;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;
import java.util.stream.Collectors;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class SensorsActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors);

        listSensors();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void listSensors() {
        final ListView listView = findViewById(R.id.lvSensors);

        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<String> sensorsList = sensorManager.getSensorList(Sensor.TYPE_ALL)
                .stream()
                .map(s -> s.getName())
                .collect(Collectors.toList());

        ArrayAdapter arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sensorsList);

        listView.setAdapter(arrayAdapter);
    }

    private void showLocation() {
        this.requestLocationPermissions();

        Button locationButton = (Button) findViewById(R.id.btnLocation);
        final FusedLocationProviderClient client = LocationServices.getFusedLocationProviderClient(this);
        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(SensorsActivity.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(SensorsActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }

                client.getLastLocation().addOnSuccessListener(SensorsActivity.this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null)
                        {
                            String longLat = "Longitute " + location.getLongitude() + " Latitude: " + location.getLatitude();
                            Toast.makeText(getApplicationContext(), longLat, Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }

    private void requestLocationPermissions(){
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, 1);
    }
}
