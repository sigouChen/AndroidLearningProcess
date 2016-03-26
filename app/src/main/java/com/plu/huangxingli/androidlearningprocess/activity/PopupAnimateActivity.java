package com.plu.huangxingli.androidlearningprocess.activity;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.plu.huangxingli.androidlearningprocess.R;
import com.plu.huangxingli.androidlearningprocess.Utils.PluLogUtil;

import java.util.ArrayList;
import java.util.List;

public class PopupAnimateActivity extends AppCompatActivity {

    private PopupWindow popupWindow;

    private GridView mGridView;
    private PopupWindow giftCountPopupWindow1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_animate);
        final Button button= (Button) findViewById(R.id.button);
        LayoutInflater inflater=LayoutInflater.from(PopupAnimateActivity.this);
        final View popView=inflater.inflate(R.layout.popup_gift, null);
        final View giftCountView=inflater.inflate(R.layout.gift_count_pop,null);
        RelativeLayout relativeLayout= (RelativeLayout) findViewById(R.id.container);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupWindow != null) {
                    PluLogUtil.log("-----onCLick outer");
                    popupWindow.dismiss();
                    giftCountPopupWindow1.dismiss();
                }
            }
        });

        ViewPager viewpager= (ViewPager) popView.findViewById(R.id.viewpager);
        GridView gridView0= (GridView) inflater.inflate(R.layout.gridlayout_gift, viewpager, false);
        GridView gridView1= (GridView) inflater.inflate(R.layout.gridlayout_gift,viewpager,false);
        GridView gridView2= (GridView) inflater.inflate(R.layout.gridlayout_gift,viewpager,false);

        gridView0.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                giftCountPopupWindow1 = new PopupWindow(giftCountView,getResources().getDisplayMetrics().widthPixels,300);
                giftCountPopupWindow1.showAtLocation(button, Gravity.CENTER, 0, 0);


            }
        });
        List<View> giftViews=new ArrayList<>();
        giftViews.add(gridView0);
        giftViews.add(gridView1);
        giftViews.add(gridView2);
        ArrayList<String> itemList=new ArrayList<>();
        for (int i=0;i<8;i++){
            itemList.add("gift "+i);
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(PopupAnimateActivity.this,android.R.layout.simple_list_item_1,android.R.id.text1,itemList);
        gridView0.setAdapter(adapter);
        gridView1.setAdapter(adapter);
        gridView2.setAdapter(adapter);


        MyViewPagerAdapter myViewPagerAdapter=new MyViewPagerAdapter(giftViews);


        viewpager.setAdapter(myViewPagerAdapter);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popupWindow = new PopupWindow(popView,getResources().getDisplayMetrics().widthPixels,500);
                popupWindow.setAnimationStyle(R.style.popupAnimation);
                popupWindow.setFocusable(false);
               // popupWindow.setBackgroundDrawable(getDrawable(R.drawable.ic_launcher));
                popupWindow.setOutsideTouchable(true);
                popupWindow.showAtLocation(button, Gravity.BOTTOM, 0, 0);

            }
        });
    }

    static class MyViewPagerAdapter extends PagerAdapter{

        List<View> giftViews;

        public MyViewPagerAdapter(List<View> giftViews) {
            this.giftViews = giftViews;
        }

        @Override
        public int getCount() {
            return giftViews==null?0:giftViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(giftViews.get(position));
            return giftViews.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(giftViews.get(position));
           // super.destroyItem(container, position, object);
        }

    }
}
