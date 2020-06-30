package com.hani.colopi.test;

import android.util.Log;

public class Caculate {



    public static final int MSG_TYPE_1 = 1;
    public static final int MSG_TYPE_2 = 1 << 1;
    public static final int MSG_TYPE_3 = 1 << 2;
    public static final int MSG_TYPE_4 = 1 << 3;

    private int mType;

    public Caculate setType(int type){
        mType |= type;
        return this;
    }

    public void checkWhichType(){
        if ((mType & MSG_TYPE_1) == MSG_TYPE_1){
            Log.w("lala","存在：MSG_TYPE_1");
        }
        if ((mType & MSG_TYPE_2) == MSG_TYPE_2){
            Log.w("lala","存在：MSG_TYPE_2");
        }
        if ((mType & MSG_TYPE_3) == MSG_TYPE_3){
            Log.w("lala","存在：MSG_TYPE_3");
        }
        if ((mType & MSG_TYPE_4) == MSG_TYPE_4){
            Log.w("lala","存在：MSG_TYPE_4");
        }

    }



    public void clearFlag(){

        Log.w("lala","开始清理：");

        mType &= (~MSG_TYPE_1);
        mType &= (~MSG_TYPE_2);
        mType &= (~MSG_TYPE_3);
        Log.w("lala","结束清理清理,再次检查：");
        checkWhichType();
    }


}
