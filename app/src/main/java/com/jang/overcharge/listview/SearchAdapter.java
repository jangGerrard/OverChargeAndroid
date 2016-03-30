package com.jang.overcharge.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.jang.overcharge.R;
import com.jang.overcharge.domain.SearchItem;

/**
 * Created by Jang on 2016-03-31.
 */
public class SearchAdapter extends ArrayAdapter<SearchItem> {

    private Context context;

    CustomHolder holder = null;

    TextView lat;
    TextView longi;
    CheckBox checkBox;

    private class CustomHolder{
        TextView lat;
        TextView longi;
        CheckBox checkBox;
    }

    public SearchAdapter(Context context, int resource) {
        super(context, resource);
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

            lat = (TextView)convertView.findViewById(R.id.lati_tv);
            longi = (TextView)convertView.findViewById(R.id.longi_tv);
            checkBox = (CheckBox)convertView.findViewById(R.id.search_item_checkbox);

            holder = new CustomHolder();
            holder.lat = lat;
            holder.longi = longi;
            holder.checkBox = checkBox;

            convertView.setTag(holder);

        } else {
            holder = (CustomHolder) convertView.getTag();
            lat = holder.lat;
            longi = holder.longi;
            checkBox = holder.checkBox;
        }

        lat.setText(getItem(position).getPoint().getLatitude() + "");
        longi.setText(getItem(position).getPoint().getLongitude()+"");

        return convertView;
    }

}
