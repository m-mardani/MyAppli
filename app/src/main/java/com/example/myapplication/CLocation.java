package com.example.myapplication;

import android.location.Location;

public class CLocation extends Location {
    private Boolean  bUseMetricUnits = false;

    public CLocation (Location location){
        this(location,true);
    }

    public CLocation (Location location , boolean bUseMetricUnits){
        super (location);
        this.bUseMetricUnits = bUseMetricUnits;
    }

    public Boolean getbUseMetricUnits() {
        return this.bUseMetricUnits;
    }

    public void setbUseMetricUnits(Boolean bUseMetricUnits) {
        this.bUseMetricUnits = bUseMetricUnits;
    }

    @Override
    public float distanceTo(Location dest) {
        float nDistance  = super.distanceTo(dest);
        if (!this.bUseMetricUnits){
            nDistance = nDistance * 3.28083989501312f;
        }
        return nDistance;
    }

    @Override
    public double getAltitude() {
        double nAltitude  = super.getAltitude();
        if (!this.bUseMetricUnits){
            //Convert meters/second to miles/hour
            nAltitude = super.getSpeed() * 2.23693629f;
        }
        return nAltitude;
    }

    @Override
    public float getSpeed() {
        float nSpeed  = super.getSpeed()  * 3.6f;
        if (!this.bUseMetricUnits){
            nSpeed = nSpeed * 3.28083989501312f;
        }
        return nSpeed;
    }


    @Override
    public float getAccuracy() {
        float nAccuracy  = super.getAccuracy();
        if (!this.bUseMetricUnits){
            nAccuracy = nAccuracy * 3.28083989501312f;
        }
        return nAccuracy;
    }
}
