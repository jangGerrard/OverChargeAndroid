package com.jang.overcharge.domain;

import com.skp.Tmap.TMapPoint;

/**
 * Created by Jang on 2016-03-31.
 */
public class SearchItem {


    private TMapPoint point;

    public SearchItem(TMapPoint point){
        this.point = point;
    }

    public TMapPoint getPoint() {
        return point;
    }

    public void setPoint(TMapPoint point) {
        this.point = point;
    }
}
