package com.walid.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.walid.autolayout.AutoLayoutActivity;

public class RegisterActivity extends AutoLayoutActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

}