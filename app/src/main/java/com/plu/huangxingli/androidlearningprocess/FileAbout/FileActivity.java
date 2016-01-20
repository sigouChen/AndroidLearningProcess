package com.plu.huangxingli.androidlearningprocess.FileAbout;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.plu.huangxingli.androidlearningprocess.BaseActivity;

import java.io.File;
import java.io.IOException;

/**
 * Created by lily on 16-1-20.
 */
public class FileActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        File file= Environment.getExternalStorageDirectory();
        String sdcardPath=file.getAbsolutePath();
        Log.v("PLU","  SDCARD PATH IS "+sdcardPath);
        File pathFile=new File(file,"test0120.txt");
        /*if (!pathFile.exists()){
            pathFile.mkdirs();
            Log.v("PLU","----path dir not exist");
        }else{
            Log.v("PLU","----PATH DIR EXIST PATH IS "+pathFile.getAbsolutePath());
        }
        File destFile=new File(pathFile,"gun.txt");*/
        try {
            pathFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
