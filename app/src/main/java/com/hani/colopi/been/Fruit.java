package com.hani.colopi.been;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.hani.colopi.BR;

public class Fruit extends BaseObservable {

    @Bindable
    public String mName;
    private int mColor;

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
        notifyPropertyChanged(BR.name);
        notifyPropertyChanged(BR.mColor);
    }

    @Bindable
    public int getmColor() {
        return mColor;
    }

    public void setmColor(int mColor) {
        this.mColor = mColor;
    }
}
