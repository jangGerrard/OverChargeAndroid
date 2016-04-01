package com.jang.overcharge.listview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.jang.overcharge.R;
import com.jang.overcharge.SearchActivity;
import com.jang.overcharge.domain.SearchItem;

/**
 * Created by Jang on 2016-03-31.
 */
public class SearchAdapter extends ArrayAdapter<SearchItem> {

    private Context context;

    CustomHolder holder = null;

    TextView poiName;
    TextView lat;
    TextView longi;
    CheckBox checkBox;

    private class CustomHolder{
        TextView poiName;
        TextView lat;
        TextView longi;
        CheckBox checkBox;
    }

    public SearchAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
    }

    public SearchAdapter(Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
        this.context = context;
    }

    public long getItemId(int position){return position;}

    public View getView(int position, View convertView, ViewGroup parent){
        final int pos = position;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.search_item, parent, false);

            poiName = (TextView) convertView.findViewById(R.id.poiName_tv);
            lat = (TextView)convertView.findViewById(R.id.lati_tv);
            longi = (TextView)convertView.findViewById(R.id.longi_tv);
            checkBox = (CheckBox)convertView.findViewById(R.id.search_item_checkbox);

            holder = new CustomHolder();
            holder.poiName = poiName;
            holder.lat = lat;
            holder.longi = longi;
            holder.checkBox = checkBox;
            convertView.setTag(holder);

        } else {
            holder = (CustomHolder) convertView.getTag();
            poiName = holder.poiName;
            lat = holder.lat;
            longi = holder.longi;
            checkBox = holder.checkBox;
        }

        poiName.setText(getItem(position).getPOIName()+"");
        lat.setText(getItem(position).getLati()+ "");
        longi.setText(getItem(position).getLongi()+"");

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s_position = String.valueOf(pos);
                Toast.makeText(context, s_position, Toast.LENGTH_SHORT).show();

                if (SearchActivity.startP == null) {
                    SearchActivity.startP = (SearchItem) getItem(pos);
                    Log.d("SearchActivity", "StartP");
                } else if (SearchActivity.endP == null) {
                    SearchActivity.endP = (SearchItem) getItem(pos);
                    Log.d("SearchActivity", "endP");
                } else {
                    Log.d("SearchActivity", "InItemClicked unCatched exception");
                }

            }
        });

        return convertView;
    }

}
