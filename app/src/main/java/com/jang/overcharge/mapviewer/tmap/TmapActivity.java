package com.jang.overcharge.mapviewer.tmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.jang.overcharge.R;
import com.skp.Tmap.TMapData;
import com.skp.Tmap.TMapMarkerItem;
import com.skp.Tmap.TMapPOIItem;
import com.skp.Tmap.TMapPoint;
import com.skp.Tmap.TMapPolyLine;
import com.skp.Tmap.TMapView;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

public class TmapActivity extends AppCompatActivity implements TMapView.OnLongClickListenerCallback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tmap);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        RelativeLayout relativeLayout = new RelativeLayout(this);
        final TMapView tmapView = new TMapView(this);

        tmapView.setSKPMapApiKey("3ad22c95-c92e-3bee-914d-86106ac81679");

        tmapView.setLanguage(TMapView.LANGUAGE_KOREAN);

        tmapView.setIconVisibility(true);

        tmapView.setZoomLevel(10);

        tmapView.setMapType(TMapView.MAPTYPE_STANDARD);

        tmapView.setCompassMode(false);

        tmapView.setTrackingMode(false);

        relativeLayout.addView(tmapView);

        setContentView(relativeLayout);

        TMapPoint point1 = tmapView.getCenterPoint();
        TMapPoint point2 = new TMapPoint(36.570841, 126.985302);

        TMapData tmapdata = new TMapData();

        tmapdata.findPathDataWithType(TMapData.TMapPathType.CAR_PATH, point1, point2, new TMapData.FindPathDataListenerCallback() {
            @Override
            public void onFindPathData(TMapPolyLine polyLine) {
                tmapView.addTMapPath(polyLine);
                Log.d("tag : ", "distance : " + polyLine.getDistance());
            }
        });


    }

    @Override
    public void onLongPressEvent(ArrayList<TMapMarkerItem> arrayList, ArrayList<TMapPOIItem> arrayList1, TMapPoint tMapPoint) {

        Log.d("tag : ", "TmapMarkerList size : " + arrayList.size());
        Log.d("tag : ", "point : " + tMapPoint.getLongitude() + ", "+ tMapPoint.getLatitude());
        TMapMarkerItem  markerItem = new TMapMarkerItem();
        markerItem.setTMapPoint(tMapPoint);
        arrayList.add(markerItem);

    }
}
