package com.jang.overcharge.mapviewer.tmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
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
import java.util.List;
import java.util.logging.LogManager;

import javax.xml.parsers.ParserConfigurationException;

public class TmapActivity extends AppCompatActivity implements TMapView.OnLongClickListenerCallback{

    private LinearLayout linearLayout;

    private TextView startPointTv;
    private TextView endPointTv;
    private TextView distanceTv;

    private TMapView tmapView;
    private List<TMapMarkerItem> markerItems;
    private double distance;

    //해야 할 것은 무엇이냐면
    //우선 길이 그거 어떻게 변환 할지 생각해보고,
    //long click 할 때, 마커 추가하는거 되는지 보자
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

        viewsInit();
        mapViewInit();
    }

    private void viewsInit(){
        linearLayout = (LinearLayout) findViewById(R.id.tmap_layout);
        startPointTv = (TextView)findViewById(R.id.start_tv);
        endPointTv = (TextView)findViewById(R.id.end_tv);
        distanceTv = (TextView)findViewById(R.id.distance_tv);
        markerItems = new ArrayList<TMapMarkerItem>();
    }

    private void mapViewInit(){
        tmapView = new TMapView(this);
        tmapView.setSKPMapApiKey("3ad22c95-c92e-3bee-914d-86106ac81679");
        tmapView.setLanguage(TMapView.LANGUAGE_CHINESE);
        tmapView.setIconVisibility(true);
        tmapView.setZoomLevel(10);
        tmapView.setMapType(TMapView.MAPTYPE_STANDARD);
        tmapView.setCompassMode(false);
        tmapView.setTrackingMode(false);

        linearLayout.addView(tmapView);
    }

    private void searchPointUsingPOIData(String str){
        TMapData  tMapData = new TMapData();
        tMapData.findTitlePOI(str, new TMapData.FindTitlePOIListenerCallback() {
            @Override
            public void onFindTitlePOI(ArrayList<TMapPOIItem> poiItem) {
                for(int i = 0 ; i < poiItem.size(); i++ ){
                    TMapPOIItem item = poiItem.get(i);
                    Log.d("POI LOG","POI Name: " + item.getPOIName().toString() + ", " +
                            "Address: " + item.getPOIAddress().replace("null", "")  + ", " +
                            "Point: " + item.getPOIPoint().toString());

                }
            }
        });
    }

    @Override
    public void onLongPressEvent(ArrayList<TMapMarkerItem> arrayList, ArrayList<TMapPOIItem> arrayList1, TMapPoint tMapPoint) {

        Log.d("tag : ", "TmapMarkerList size : " + arrayList.size());
        Log.d("tag : ", "point : " + tMapPoint.getLongitude() + ", " + tMapPoint.getLatitude());
        //Toast.makeText(getBaseContext(), "point : " + tMapPoint.getLongitude() + ", " + tMapPoint.getLatitude(), Toast.LENGTH_LONG).show();
        startPointTv.setText("point : " + tMapPoint.getLongitude() + ", " + tMapPoint.getLatitude());
        TMapMarkerItem  markerItem = new TMapMarkerItem();
        markerItem.setTMapPoint(tMapPoint);
        //arrayList.add(markerItem);
        tmapView.addMarkerItem("id", markerItem);
        markerItems.add(markerItem);
        findPath();
    }

    private void findPath(){
        if(markerItems.size()< 2){
            return;
        }

        int arrSize = markerItems.size();

        TMapPoint startPoint = markerItems.get(arrSize - 1).getTMapPoint();
        TMapPoint endPoint = markerItems.get(arrSize - 2).getTMapPoint();
        startPointTv.setText("start point : " + startPoint.getLongitude() + ", " + startPoint.getLatitude());
        endPointTv.setText("end point : " + endPoint.getLongitude() + ", " + endPoint.getLatitude());

        TMapData tmapdata = new TMapData();

        tmapdata.findPathDataWithType(TMapData.TMapPathType.CAR_PATH, startPoint, endPoint, new TMapData.FindPathDataListenerCallback() {
            @Override
            public void onFindPathData(TMapPolyLine polyLine) {
                tmapView.addTMapPath(polyLine);

                distance = polyLine.getDistance();
                Log.d("tag : ", "distance : " + distance);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable(){
                            @Override
                            public void run() {
                                // 해당 작업을 처리함
                                //distanceTv.setText(distance+"");
                                distanceTv.setText("distance : "+distance/1000.0+"km");
                                Log.d("ui Thread", "UI Thread" + ", distance : " + distance);
                            }
                        });
                    }
                }).start();
            }
        });

    }
}
