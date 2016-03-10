package com.plu.huangxingli.androidlearningprocess.DrawerAbout;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.plu.huangxingli.androidlearningprocess.BaseActivity;
import com.plu.huangxingli.androidlearningprocess.R;

import java.util.ArrayList;

/**
 * Created by lenovo on 2016/1/5.
 */
public class DrawerLayoutActivity extends BaseActivity {


    ActionBarDrawerToggle mActionBarDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);
        final DrawerLayout drawerLayout= (DrawerLayout) findViewById(R.id.drawer_layout);
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mActionBarDrawerToggle = new ActionBarDrawerToggle
                (
                        this,
                        drawerLayout,
                        toolbar
                        ,
                        R.string.toggle_open,
                        R.string.toggle_close
                )
        {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset)
            {
                // Disables the burger/arrow animation by default
                super.onDrawerSlide(drawerView, 0);
            }
        };
        // mActionBarDrawerToggle.syncState();
        drawerLayout.setDrawerListener(mActionBarDrawerToggle);
        ArrayList<String> items=new ArrayList<>();
        for (int i=0;i<10;i++){
            items.add("item "+i);
        }

        ListView listview= (ListView) findViewById(R.id.listview_drawer);
        final ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,items);
        listview.setAdapter(adapter);
        final android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
        // final FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        ViewGroup.LayoutParams layoutParams=listview.getLayoutParams();
        layoutParams.width=350;
        listview.setLayoutParams(layoutParams);
        Bundle bundle=new Bundle();
        bundle.putInt("pos", 0);
        ContentFragment contentFragment=new ContentFragment();
        contentFragment.setArguments(bundle);
        fragmentManager.beginTransaction().add(R.id.fragment_container, contentFragment).commit();
        // drawerLayout.setLayoutParams(new ViewGroup.LayoutParams(300, ViewGroup.LayoutParams.MATCH_PARENT));

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle=new Bundle();
                bundle.putInt("pos", position);
                ContentFragment contentFragment=new ContentFragment();
                contentFragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.fragment_container, contentFragment).commit();
                drawerLayout.closeDrawer(GravityCompat.START);
                // drawerLayout.closeDrawers();

            }
        });
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mActionBarDrawerToggle.syncState();

    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mActionBarDrawerToggle.onConfigurationChanged(newConfig);
    }
}