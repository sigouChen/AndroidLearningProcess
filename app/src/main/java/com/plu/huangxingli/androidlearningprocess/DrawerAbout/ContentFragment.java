package com.plu.huangxingli.androidlearningprocess.DrawerAbout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.plu.huangxingli.androidlearningprocess.R;

/**
 * Created by lenovo on 2016/1/5.
 */
public class ContentFragment  extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle=getArguments();
        int pos=bundle.getInt("pos");
        View view=inflater.inflate(R.layout.content_fragment, null);
        TextView textView= (TextView) view.findViewById(R.id.textview);
        textView.setText(" i am pos "+pos);
        return view;
    }
}
