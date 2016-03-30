package com.jang.overcharge.search;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.jang.overcharge.R;
import com.jang.overcharge.domain.SearchItem;
import com.jang.overcharge.listview.SearchAdapter;
import com.skp.Tmap.TMapData;
import com.skp.Tmap.TMapPOIItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jang on 2016-03-31.
 */
public class SearchViewImpl implements SearchView.OnQueryTextListener, SearchView.OnCloseListener {

    private Context context ;
    private ListView listView;
    private ArrayAdapter<SearchItem> arrayAdapter;

    public SearchViewImpl(Context context, ListView listView , ArrayAdapter<SearchItem> arrayAdapter) {
        this.arrayAdapter = arrayAdapter;
        this.context = context;
        this.listView = listView;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
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
        Log.d("searchImpl", "here");
        TMapData tMapData = new TMapData();
        tMapData.findTitlePOI(str, new TMapData.FindTitlePOIListenerCallback() {
            @Override
            public void onFindTitlePOI(ArrayList<TMapPOIItem> poiItem) {
                Log.d("searchImpl", "in callback func");
                List<SearchItem> items = new ArrayList<SearchItem> ();
                for(int i = 0 ; i < poiItem.size(); i++ ){
                    TMapPOIItem item = poiItem.get(i);
                    Log.d("POI LOG", "POI Name: " + item.getPOIName().toString() + ", " +
                            "Address: " + item.getPOIAddress().replace("null", "") + ", " +
                            "Point: " + item.getPOIPoint().toString());
                    items.add(new SearchItem(item.getPOIPoint()));

                }

                arrayAdapter = new SearchAdapter(context, R.layout.search_item);
                arrayAdapter.addAll(items);


                final Handler handler = new Handler();


                new Thread(new Runnable() {
                public void run() {
                    handler.post(new Runnable() {
                        public void run() {
                            //txtView.setText("이제는 됩니다.");
                            listView.setAdapter(arrayAdapter);
                        }
                    });
                }
                }).start();



            }
        });
    }
}
