package com.jang.overcharge.mapviewer.tmap;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

import com.jang.overcharge.R;
import com.skp.Tmap.TMapView;

public class TmapActivity extends AppCompatActivity {

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
        TMapView tmapView = new TMapView(this);

        tmapView.setSKPMapApiKey("3ad22c95-c92e-3bee-914d-86106ac81679");

        tmapView.setLanguage(TMapView.LANGUAGE_KOREAN);

        tmapView.setIconVisibility(true);

        tmapView.setZoomLevel(10);

        tmapView.setMapType(TMapView.MAPTYPE_STANDARD);

        tmapView.setCompassMode(true);

        tmapView.setTrackingMode(true);

        relativeLayout.addView(tmapView);

        setContentView(relativeLayout);
    }

}
