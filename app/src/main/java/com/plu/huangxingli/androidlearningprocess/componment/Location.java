package com.plu.huangxingli.androidlearningprocess.componment;

import com.plu.huangxingli.androidlearningprocess.Utils.PluLogUtil;

/**
 * Created by lily on 16-1-18.
 */
public class Location implements Here{

    static final int GO=0;
    static final int RUN=1;
    Here here;

    public Location(int type) {
        switch (type){
            case GO:
                here =new GoHere();
                break;
            case RUN:
                here =new RunHere();
                break;
        }
    }




    @Override
    public boolean IsHere() {
        return here.IsHere();
    }

    @Override
    public void goHere() {
        here.goHere();
    }

    @Override
    public void leaveHere() {
        here.leaveHere();
    }

    @Override
    public void setDetectHereListener(DetectHereListener here) {
        this.here.setDetectHereListener(here);

    }

    public interface DetectHereListener{
        void onGoHere();
        void onLeaveHere();
    }
}



interface Here{

    boolean IsHere();
    void goHere();
    void leaveHere();
    void setDetectHereListener(Location.DetectHereListener detectHereListener);
   // public void setHereListener(DetectHereListener hereListener);

}

class GoHere implements Here{
    public boolean isHere=false;

    Location.DetectHereListener detectHereListener;

    @Override
    public boolean IsHere() {
        return isHere;
    }

    @Override
    public void goHere() {
        PluLogUtil.log(Location.class, "----go here");
        if (!IsHere()){
            detectHereListener.onGoHere();
            isHere=true;
        }else{

            PluLogUtil.log(Location.class,"---is here no need to goHere");
        }

    }

    @Override
    public void leaveHere() {
        PluLogUtil.log(Location.class, "  go leave here");
        if (IsHere()){
            detectHereListener.onLeaveHere();
            isHere=false;
        }else{
            PluLogUtil.log(Location.class,"----not here no necessary to leave here");
        }

    }

    @Override
    public void setDetectHereListener(Location.DetectHereListener detectHereListener) {
        this.detectHereListener=detectHereListener;
    }
}

class RunHere implements Here{
    public boolean isHere=false;

    Location.DetectHereListener detectHereListener;

    @Override
    public boolean IsHere() {
        return isHere;
    }

    @Override
    public void goHere() {
        PluLogUtil.log(Location.class,"----run to go here");
        if (IsHere()){
            detectHereListener.onLeaveHere();
            isHere=false;

        }else{
            PluLogUtil.log(Location.class,"----not here no necessary to leave here");
        }
    }

    @Override
    public void leaveHere() {
        PluLogUtil.log(Location.class,"  ---run to leave here");
        if (IsHere()){
            detectHereListener.onLeaveHere();
            isHere=false;
        }else{
            PluLogUtil.log(Location.class,"---not here ,no need to leave here");
        }
    }

    @Override
    public void setDetectHereListener(Location.DetectHereListener detectHereListener) {
        this.detectHereListener=detectHereListener;
    }
}




