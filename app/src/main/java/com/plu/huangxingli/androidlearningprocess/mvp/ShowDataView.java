package com.plu.huangxingli.androidlearningprocess.mvp;

import java.util.ArrayList;

/**
 * Created by lily on 16-2-17.
 */
public interface ShowDataView extends MvpView{
    public void showList(ArrayList<String> listData);
}
