package com.plu.huangxingli.androidlearningprocess.app;

import android.app.Application;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.cache.disc.impl.LimitedAgeDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LRULimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.plu.huangxingli.androidlearningprocess.R;
import com.plu.huangxingli.androidlearningprocess.Utils.PluLogUtil;

import java.io.File;

/**
 * Created by lily on 16-3-23.
 */
public class App extends Application {
    static App app;


    @Override
    public void onCreate() {
        super.onCreate();
        app=this;


    }

    public static App getInstance(){
        return app;
    }


    public void initImageLoader(){
        File cacheDir = StorageUtils.getOwnCacheDirectory(
                getInstance(), "lily/cache");
        ImageLoaderConfiguration.Builder builder1 = new ImageLoaderConfiguration.Builder(getInstance())
                .denyCacheImageMultipleSizesInMemory()
                .memoryCacheSizePercentage(50) //availableMemoryPercent，设置内存缓存使用占应最大内存的比例
                .memoryCache(new LRULimitedMemoryCache(5 * 1024 * 1024))  // 弱引用自动管理内存，达到上限时先remove最不常用的
//                .diskCache(new LruDiskCache(cacheDir, new Md5FileNameGenerator(), 50 * 1024 * 1024))
//                .memoryCacheExtraOptions(480, 800) // 设定内存图片缓存大小限制，不设置默认为屏幕的宽高
//                .memoryCacheSize(5 * 1024 * 1024) // 缓存到内存的最大数据
//                .memoryCache(new UsingFreqLimitedMemoryCache(10 * 1024 * 1024)) // 设定内存缓存为弱缓存
                        //.diskCacheFileCount(1000) // 缓存文件最大数量
                .diskCacheSize(1) // 缓存到文件的最大数据
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCache(new LimitedAgeDiskCache(cacheDir, 5))//设置图片的磁盘缓存时间为10min，缓存时间超过１０分钟就删除                    //    .diskCache(new UnlimitedDiskCache(cacheDir))
//                .writeDebugLogs() // LOG打包时要去掉
                .threadPoolSize(3)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .tasksProcessingOrder(QueueProcessingType.LIFO); // 线程优先级：last in first out



        ImageLoader imageLoader=ImageLoader.getInstance();
        imageLoader.init(builder1.build());
    }

    public ImageLoader getImageLoader(){
        File cacheDir = StorageUtils.getOwnCacheDirectory(
                getInstance(), "plu1/cache");
        PluLogUtil.log("----cache dir is "+cacheDir);
        ImageLoaderConfiguration.Builder builder1 = new ImageLoaderConfiguration.Builder(getInstance())
                .denyCacheImageMultipleSizesInMemory()
                .memoryCacheSizePercentage(50) //availableMemoryPercent，设置内存缓存使用占应最大内存的比例
                .memoryCache(new LRULimitedMemoryCache(5 * 1024 * 1024))  // 弱引用自动管理内存，达到上限时先remove最不常用的
//                .diskCache(new LruDiskCache(cacheDir, new Md5FileNameGenerator(), 50 * 1024 * 1024))
//                .memoryCacheExtraOptions(480, 800) // 设定内存图片缓存大小限制，不设置默认为屏幕的宽高
//                .memoryCacheSize(5 * 1024 * 1024) // 缓存到内存的最大数据
//                .memoryCache(new UsingFreqLimitedMemoryCache(10 * 1024 * 1024)) // 设定内存缓存为弱缓存
                        .diskCacheFileCount(40) // 缓存文件最大数量
                .diskCacheSize(1024) // 缓存到文件的最大数据 1m
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCache(new LimitedAgeDiskCache(cacheDir, 5))//设置图片的磁盘缓存时间为10min，缓存时间超过１０分钟就删除                    //    .diskCache(new UnlimitedDiskCache(cacheDir))
//                .writeDebugLogs() // LOG打包时要去掉
                .threadPoolSize(3)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .tasksProcessingOrder(QueueProcessingType.LIFO); // 线程优先级：last in first out



        ImageLoader imageLoader=ImageLoader.getInstance();
        imageLoader.init(builder1.build());
        return imageLoader;

    }
}
