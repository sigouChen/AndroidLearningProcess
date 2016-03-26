package com.plu.huangxingli.androidlearningprocess.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.plu.huangxingli.androidlearningprocess.R;
import com.plu.huangxingli.androidlearningprocess.app.App;

import java.util.ArrayList;

/**
 * Created by lily on 16-3-23.
 */
public class PicAdapter extends BaseAdapter {

    String[] imageThumbUrls;

    Context context;
    LayoutInflater inflater;
    ImageLoader mImageLoader;
    private final DisplayImageOptions.Builder builder;

    public PicAdapter(String[] imageThumbUrls,Context context) {
        this.imageThumbUrls = imageThumbUrls;
        this.context=context;
        inflater=LayoutInflater.from(context);
        mImageLoader= App.getInstance().getImageLoader();
        builder = new DisplayImageOptions.Builder()
                .bitmapConfig(Bitmap.Config.RGB_565)
                        // 设置图片的解码类型
                .showImageOnLoading(R.drawable.ic_launcher)
                .showImageForEmptyUri(R.drawable.ic_launcher) // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.ic_launcher) // 设置图片加载/解码过程中错误时候显示的图片
                .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
                .cacheOnDisk(true);

    }

    @Override
    public int getCount() {
        return imageThumbUrls.length;
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
        ViewHolder viewHolder;
        if (convertView==null){
            convertView=inflater.inflate(R.layout.pic,null);
            viewHolder=new ViewHolder();
            viewHolder.imageView= (ImageView) convertView.findViewById(R.id.imageview);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }

        mImageLoader.displayImage(imageThumbUrls[position],viewHolder.imageView,builder.build());



        return convertView;
    }

    static class ViewHolder{
        ImageView imageView;
    }
}
