package com.plu.huangxingli.androidlearningprocess.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

import com.plu.huangxingli.androidlearningprocess.BaseActivity;
import com.plu.huangxingli.androidlearningprocess.R;
import com.plu.huangxingli.androidlearningprocess.Utils.PluLogUtil;
import com.plu.huangxingli.androidlearningprocess.fragment.TabHostFragment1;

/**
 * Created by lily on 16-2-2.
 */
public class TabHostFragmentTest extends BaseActivity {

    Class[] tabs=new Class[]{TabHostFragment1.class,TabHostFragment1.class,TabHostFragment1.class};
    LayoutInflater inflater;

    String[] tabContent={"tab1","tab2","tab3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_tab_host);
        inflater=LayoutInflater.from(this);
        FragmentTabHost fragmentTabHost= (FragmentTabHost) findViewById(android.R.id.tabhost);
        fragmentTabHost.setup(getApplicationContext(),getSupportFragmentManager(),R.id.fragment_container);

       // ViewPager viewPager;
        for (int i=0;i<tabContent.length;i++){
            Bundle bundle=new Bundle();
            bundle.putString("content", "content " + i);

            TabHost.TabSpec tabSpec=fragmentTabHost.newTabSpec(tabContent[i]).setIndicator(getIndicator(i));
            fragmentTabHost.addTab(tabSpec,tabs[i],bundle);

        }
        fragmentTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                PluLogUtil.log(TabHostFragmentTest.class,"----onTabChange id is "+tabId);
            }
        });


    }

    private View getIndicator(int i) {
        View indicator=inflater.inflate(R.layout.fragment_indicator,null);
        TextView textView= (TextView) indicator.findViewById(R.id.tv_indicator);
        textView.setText("indicator "+i);

        return indicator;
    }
}
