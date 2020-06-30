package com.hani.colopi;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import androidx.databinding.ObservableInt;

import com.hani.colopi.databinding.AcitvityTestBinding;

public class TestActivity extends AppCompatActivity {

    private ObservableInt mNum = new ObservableInt();
    private AcitvityTestBinding mBinding;

    private TextView mTvNum;
    private TextView mTvBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.acitvity_test);
        mTvNum = mBinding.tvNum;
        mTvBtn = mBinding.tvBtn;
        mTvBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNum.set(11111);
            }
        });
        mNum.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                mTvNum.setText("更改后"+ mNum.get());
            }
        });
    }
}
