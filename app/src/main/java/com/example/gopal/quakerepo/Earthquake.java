package com.example.gopal.quakerepo;

import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Created by Gopal on 10/24/2018.
 */

public class Earthquake extends ArrayList<Earthquake> {
    private double mMagnitude;
    private long mTime;
    private String mLocation;
    private String mUrl;
    public Earthquake(double magnitude, long time, String location, String url){
        mMagnitude = magnitude;
        mTime = time;
        mLocation = location;
        mUrl = url;
    }
    public double getMagnitude() {return mMagnitude;}

    public long getTime() {return mTime;}

    public String getLocation() {return mLocation;}

    public String getUrl() {return mUrl;}

}
