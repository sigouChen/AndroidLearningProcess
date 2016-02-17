package com.plu.huangxingli.androidlearningprocess.mvp;

import com.plu.huangxingli.androidlearningprocess.Utils.PluLogUtil;

import java.util.ArrayList;

/**
 * Created by lily on 16-2-17.
 */
public class DataPresenter {

    ShowDataView showDataView;

    public DataPresenter(ShowDataView showDataView) {
        this.showDataView = showDataView;
    }

    public ArrayList<String> getListData(){
        ArrayList<String> dataList=new ArrayList<>();
        for (int i=0;i<100;i++){
            dataList.add("item "+i);
        }
        if (showDataView!=null) {
            showDataView.showList(dataList);
        }
        return dataList;
    }

}
