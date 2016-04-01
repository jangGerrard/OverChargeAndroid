package com.jang.overcharge.domain;

import android.os.Parcel;
import android.os.Parcelable;

import com.skp.Tmap.TMapPoint;

import java.io.Serializable;

/**
 * Created by Jang on 2016-03-31.
 */
public class SearchItem implements Serializable{


    private double longi;
    private double lati;
    private String POIName;

    public SearchItem(double longi, double lati) {
        this.longi = longi;
        this.lati = lati;
    }

    public SearchItem(double longi, double lati ,String POIName){
        this.POIName = POIName;
        this.longi = longi;
        this.lati = lati;
    }

    public String getPOIName() {
        return POIName;
    }

    public void setPOIName(String POIName) {
        this.POIName = POIName;
    }

    public double getLongi() {
        return longi;
    }

    public void setLongi(double longi) {
        this.longi = longi;
    }

    public double getLati() {
        return lati;
    }

    public void setLati(double lati) {
        this.lati = lati;
    }
}
