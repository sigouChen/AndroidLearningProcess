package com.plu.huangxingli.androidlearningprocess.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.plu.huangxingli.androidlearningprocess.R;

/**
 * Created by lily on 16-2-2.
 */
public class TabHostFragment1 extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.tab,null);
        TextView textView= (TextView) view.findViewById(R.id.textview);
        Bundle bundle=getArguments();
        String content= (String) bundle.get("content");
        textView.setText(content);
        return view;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }
}
