package com.plu.huangxingli.androidlearningprocess.hotFix;

import android.app.Application;
import android.os.Environment;
import android.util.Log;

import com.alipay.euler.andfix.patch.PatchManager;

import java.io.IOException;

/**
 * Created by lily on 16-1-19.
 */
public class MyApplication extends Application{

    private static final String APATCH_PATH = "/out.apatch";
    private PatchManager patchManager;
    @Override
    public void onCreate() {
        super.onCreate();
        patchManager = new PatchManager(this);
        patchManager.init("1.0");
        // load patch
        patchManager.loadPatch();
        // add patch at runtime
        try {
            // .apatch file path
            String patchFileString = Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + APATCH_PATH;
            Log.e("TAG", "patch file is " + patchFileString);
            patchManager.addPatch(patchFileString);
        } catch (IOException e) {
        }
    }

}
