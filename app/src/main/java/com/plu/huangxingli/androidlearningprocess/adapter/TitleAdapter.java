package com.plu.huangxingli.androidlearningprocess.adapter;

import android.content.Context;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.plu.huangxingli.androidlearningprocess.BaseActivity;
import com.plu.huangxingli.androidlearningprocess.R;

/**
 * Created by lily on 15-12-31.
 */
public class TitleAdapter extends BaseAdapter {

    ArrayMap<String,Class> lessonMap;
    LayoutInflater inflater;

    public TitleAdapter(ArrayMap<String,Class> lessonMap,Context context) {
        this.lessonMap = lessonMap;
        inflater=LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return lessonMap==null?0:lessonMap.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView=inflater.inflate(R.layout.title_item,parent,false);
        }
        TextView textView= (TextView) convertView.findViewById(R.id.textview_title);
        textView.setText(lessonMap.keyAt(position));
        return convertView;
    }
}
