package com.jang.overcharge;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.jang.overcharge.domain.SearchItem;
import com.jang.overcharge.domain.TestObj;
import com.jang.overcharge.listview.SearchAdapter;
import com.jang.overcharge.search.SearchViewImpl;
import com.jang.overcharge.singleton.InfoSingleton;
import com.skp.Tmap.TMapData;
import com.skp.Tmap.TMapPOIItem;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity
        implements SearchView.OnQueryTextListener, SearchView.OnCloseListener{

    private SearchView startSearchView;
    private SearchView endSearchView;
    private ListView searchResultListView;
    private SearchAdapter searchAdapter;
    private Button searchOnMapBtn;

    public static SearchItem startP;
    public static SearchItem endP;

    List<SearchItem> items = new ArrayList<SearchItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
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

        initViews();


    }

    private void initViews(){
        startSearchView = (SearchView)findViewById(R.id.start_searchView);
        endSearchView = (SearchView)findViewById(R.id.end_searchView);
        searchResultListView = (ListView)findViewById(R.id.search_result_listView);
        searchOnMapBtn = (Button)findViewById(R.id.searchOnMapBtn);

        //SearchViewImpl searchViewImpl = new SearchViewImpl(getApplicationContext(), searchResultListView, searchAdapter );

        startSearchView.setOnQueryTextListener(this);
        startSearchView.setOnCloseListener(this);
        endSearchView.setOnQueryTextListener(this);
        endSearchView.setOnCloseListener(this);
        searchOnMapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("startPoint", startP);
                intent.putExtra("endPoint",endP);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        if(startSearchView.isActivated() ) {
            Toast.makeText(getApplicationContext(), "startSearchView is Activatied", Toast.LENGTH_SHORT).show();
            Log.d("SearchActivity","startSearchView activiated");
        }
        searchPointUsingPOIData(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        searchPointUsingPOIData(query);
        return false;
    }

    @Override
    public boolean onClose() {

        return false;
    }

    private void searchPointUsingPOIData(String str){

        if(startSearchView.isActivated() ) {
            Toast.makeText(getApplicationContext(), "startSearchView is Activatied", Toast.LENGTH_SHORT).show();
            Log.d("SearchActivity","startSearchView activiated");
        }


        TMapData tMapData = new TMapData();
        tMapData.findTitlePOI(str, new TMapData.FindTitlePOIListenerCallback() {
            @Override
            public void onFindTitlePOI(ArrayList<TMapPOIItem> poiItem) {
                items.clear();
                for (int i = 0; i < poiItem.size(); i++) {
                    TMapPOIItem item = poiItem.get(i);
                    items.add(new SearchItem(item.getPOIPoint().getLongitude(), item.getPOIPoint().getLatitude() , item.getPOIName() ));

                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable(){
                            @Override
                            public void run() {
                                searchAdapter = new SearchAdapter(getApplicationContext(), R.layout.search_item);
                                searchAdapter.addAll(items);
                                searchResultListView.setAdapter(searchAdapter);
                            }
                        });
                    }
                }).start();
            }
        });
    }

}
