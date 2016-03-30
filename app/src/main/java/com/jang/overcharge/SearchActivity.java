package com.jang.overcharge;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;

import com.jang.overcharge.listview.SearchAdapter;
import com.jang.overcharge.search.SearchViewImpl;

public class SearchActivity extends AppCompatActivity {

    private SearchView startSearchView;
    private SearchView endSearchView;
    private ListView searchResultListView;
    private SearchAdapter searchAdapter;

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

        SearchViewImpl searchViewImpl = new SearchViewImpl(getApplicationContext(), searchResultListView, searchAdapter );
        startSearchView.setOnQueryTextListener(searchViewImpl);
        startSearchView.setOnCloseListener(searchViewImpl);
        endSearchView.setOnQueryTextListener(searchViewImpl);
        endSearchView.setOnCloseListener(searchViewImpl);

    }

}
