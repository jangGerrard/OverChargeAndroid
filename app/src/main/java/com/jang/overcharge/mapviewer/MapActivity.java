package com.jang.overcharge.mapviewer;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.jang.overcharge.singleton.InfoSingleton;
import com.jang.overcharge.R;
import com.nhn.android.maps.NMapActivity;
import com.nhn.android.maps.NMapController;
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.nmapmodel.NMapError;

public class MapActivity extends NMapActivity {

    //private MapContainerView mMapContainerView;

    private NMapView mMapView;
    private NMapController mMapController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        mMapView = (NMapView)findViewById(R.id.mapView);

        mMapView.setClientId(InfoSingleton.getInstance().getClientId());

        mMapView.setClickable(true);

//        mMapView.setOnMapStateChangeListener(onMapViewStateChangeListener);
//        mMapView.setOnMapViewTouchEventListener(onMapViewTouchEventListener);
//
        mMapController = mMapView.getMapController();
    }

    public void onMapInitHandler(NMapView mapView , NMapError errorInfo){
        if(errorInfo == null  ){
            Toast.makeText(getApplicationContext(), "no error", Toast.LENGTH_LONG).show();
            mMapController.setMapCenter(new NGeoPoint(126.978371, 37.566091), 11);
        } else {
            Log.e("MAP_ERROR", "onMapInitHandler : error = " + errorInfo.toString());
        }
    }


}
