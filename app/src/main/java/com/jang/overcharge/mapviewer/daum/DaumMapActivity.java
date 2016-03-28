package com.jang.overcharge.mapviewer.daum;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.jang.overcharge.R;
import com.jang.overcharge.singleton.InfoSingleton;

import net.daum.mf.map.api.MapView;

public class DaumMapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daum_map);
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

        MapView mapView = new MapView(this  );
        mapView.setDaumMapApiKey(InfoSingleton.getInstance().getDaumApiKey());

        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.map_view );
        mapViewContainer.addView(mapView);

    }

}
