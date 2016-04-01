package com.jang.overcharge;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jang.overcharge.domain.SearchItem;
import com.jang.overcharge.mapviewer.MapActivity;
import com.jang.overcharge.mapviewer.daum.DaumMapActivity;
import com.jang.overcharge.mapviewer.tmap.TmapActivity;
import com.skp.Tmap.TMapData;
import com.skp.Tmap.TMapMarkerItem;
import com.skp.Tmap.TMapPOIItem;
import com.skp.Tmap.TMapPoint;
import com.skp.Tmap.TMapPolyLine;
import com.skp.Tmap.TMapView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , TMapView.OnLongClickListenerCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        viewsInit();
        mapViewInit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            ///won't use this in fucture
            Intent intent = new Intent(MainActivity.this, DaumMapActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            ///won't use this in fucture
            Intent intent = new Intent(MainActivity.this, MapActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_slideshow) {
            //main
            Intent intent = new Intent(MainActivity.this, TmapActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_manage) {
            Intent intent = new Intent(MainActivity.this, SearchActivity.class);
            startActivityForResult(intent, 10);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private LinearLayout linearLayout;

    private TextView startPointTv;
    private TextView endPointTv;
    private TextView distanceTv;

    private TMapView tmapView;
    private List<SearchItem> markerItems;
    private double distance;

    private void viewsInit(){
        linearLayout = (LinearLayout) findViewById(R.id.tmap_layout);
        startPointTv = (TextView)findViewById(R.id.start_tv);
        endPointTv = (TextView)findViewById(R.id.end_tv);
        distanceTv = (TextView)findViewById(R.id.distance_tv);
        markerItems = new ArrayList<SearchItem>();
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
        TMapData tMapData = new TMapData();
        tMapData.findTitlePOI(str, new TMapData.FindTitlePOIListenerCallback() {
            @Override
            public void onFindTitlePOI(ArrayList<TMapPOIItem> poiItem) {
                for(int i = 0 ; i < poiItem.size(); i++ ){
                    TMapPOIItem item = poiItem.get(i);
                    Log.d("POI LOG", "POI Name: " + item.getPOIName().toString() + ", " +
                            "Address: " + item.getPOIAddress().replace("null", "") + ", " +
                            "Point: " + item.getPOIPoint().toString());

                }
            }
        });
    }

    @Override
    public void onLongPressEvent(ArrayList<TMapMarkerItem> arrayList, ArrayList<TMapPOIItem> arrayList1, TMapPoint tMapPoint) {

        Log.d("tag : ", "TmapMarkerList size : " + arrayList.size());
        Log.d("tag : ", "point : " + tMapPoint.getLongitude() + ", " + tMapPoint.getLatitude());
        startPointTv.setText("point : " + tMapPoint.getLongitude() + ", " + tMapPoint.getLatitude());
        TMapMarkerItem  markerItem = new TMapMarkerItem();
        markerItem.setTMapPoint(tMapPoint);
        tmapView.addMarkerItem("id", markerItem);

        SearchItem searchItem = new SearchItem(markerItem.getTMapPoint().getLongitude(),markerItem.getTMapPoint().getLatitude());
        markerItems.add(searchItem);
        findPath();
    }

    private void findPath(){
        if(markerItems.size()< 2){
            return;
        }

        int arrSize = markerItems.size();

        SearchItem s = markerItems.get(arrSize - 1);
        TMapPoint startPoint = new TMapPoint(s.getLati(),s.getLongi());
        SearchItem e = markerItems.get(arrSize - 2);
        TMapPoint endPoint = new TMapPoint(e.getLati(),e.getLongi());
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
                                calculateFare(distance);
                            }
                        });
                    }
                }).start();
            }
        });

    }


    ///distance meter 단위
    public double calculateFare(double distance){
        double fare;
        if(distance / 1000.0 < 2.0){
            fare = 3000.0;
        } else {
            distance -= 2000.0;
            fare = ((distance / 144.0 ) * 100) + 3000;
        }
        Log.d("fare", "fare : "+fare);
        return fare;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 10) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(getApplicationContext(),"onActivityResult", Toast.LENGTH_SHORT).show();
                SearchItem start = (SearchItem) data.getSerializableExtra("startPoint");
                SearchItem end = (SearchItem) data.getSerializableExtra("endPoint");
                markerItems.add(start);
                markerItems.add(end);
                findPath();
            }
        }

    }
}
