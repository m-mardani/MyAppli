package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Formatter;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements LocationListener {

    Switch aSwitch;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        aSwitch = findViewById(R.id.switch1);
        textView = findViewById(R.id.Textview);


        //check for GPS permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1000);
        }else {
            //Start the Program if the Permission is granted
            dostuff();
        }
        this.updataSpeed(null);

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                MainActivity.this.updataSpeed(null);
            }
        });

    }



    private void attack(){
        // Add Comment
        //Add New Comment
        //Secound Comment
    }


    @Override
    public void onLocationChanged(@NonNull Location location) {
        if (location != null){
            CLocation myLocation = new CLocation(location , this.useMetricUnits());
            this.updataSpeed(myLocation);
        }

    }


    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }

    @SuppressLint("MissingPermission")
    private void dostuff() {
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (locationManager != null){
            locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 0,0,this);
        }
        Toast.makeText(this, "Waiting for GPS Connection ", Toast.LENGTH_SHORT).show();

    }
    private void updataSpeed(CLocation location){
        float nCurrentSpeed = 0;
        if (location != null){
            location.setbUseMetricUnits(this.useMetricUnits());
            nCurrentSpeed = location.getSpeed();

        }

        Formatter fmt = new Formatter(new StringBuilder());
        fmt.format(Locale.US , "%5.1f" , nCurrentSpeed);
        String strCurrentSpeed = fmt.toString();
        strCurrentSpeed = strCurrentSpeed.replace(" ", "0");

        if (this.useMetricUnits()){
            textView.setText(strCurrentSpeed + " km/h");
        }else {
            textView.setText(strCurrentSpeed + " miles/h");
        }

    }

    private boolean useMetricUnits() {
        return aSwitch.isChecked();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode ==  1000){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                dostuff();
            }else {
                finish();
            }
        }
    }
}
