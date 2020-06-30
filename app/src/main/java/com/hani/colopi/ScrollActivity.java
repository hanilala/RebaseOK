package com.hani.colopi;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hani.colopi.test.Caculate;

public class ScrollActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);

        Caculate caculate = new Caculate();
        caculate.setType(Caculate.MSG_TYPE_1)
                .setType(Caculate.MSG_TYPE_2)
                .setType(Caculate.MSG_TYPE_4);
        caculate.checkWhichType();
        caculate.clearFlag();
    }
}
