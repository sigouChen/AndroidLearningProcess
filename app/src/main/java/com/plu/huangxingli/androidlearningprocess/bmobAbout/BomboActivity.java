package com.plu.huangxingli.androidlearningprocess.bmobAbout;

import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.renderscript.BaseObj;
import android.util.Log;
import android.widget.Toast;

import com.plu.huangxingli.androidlearningprocess.BaseActivity;
import com.plu.huangxingli.androidlearningprocess.Utils.PluLogUtil;

import java.io.File;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.UploadFileListener;

/**
 * Created by lily on 16-1-19.
 */
public class BomboActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 初始化 Bmob SDK
        // 使用时请将第二个参数Application ID替换成你在Bmob服务器端创建的Application ID
        Bmob.initialize(this, "b4b90f69bd12688d5cbf7c55876094ab");
        String sdcardPath=Environment.getExternalStorageDirectory().getAbsolutePath();

        String picPath = sdcardPath+"/360/lily.apatch";
        BmobFile bmobFile = new BmobFile(new File(picPath));

        bmobFile.uploadblock(BomboActivity.this, new UploadFileListener() {

            @Override
            public void onSuccess() {
                // TODO Auto-generated method stub
                //bmobFile.getUrl()---返回的上传文件的地址（不带域名）
                //bmobFile.getFileUrl(context)--返回的上传文件的完整地址（带域名）
                Toast.makeText(BomboActivity.this,"上传文件成功",Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onProgress(Integer value) {
                // TODO Auto-generated method stub
                PluLogUtil.log(BomboActivity.class,"----progress is "+value);
                // 返回的上传进度（百分比）
            }

            @Override
            public void onFailure(int code, String msg) {
                // TODO Auto-generated method stub
                Toast.makeText(BomboActivity.this,"上传文件失败",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
