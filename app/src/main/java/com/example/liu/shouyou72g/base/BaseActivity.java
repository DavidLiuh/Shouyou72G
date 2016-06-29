package com.example.liu.shouyou72g.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by liu on 2016/6/27.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setViewId());
        findViews();
        init();
        initEvents();
        loadDatas();
    }

    protected abstract void findViews();

    protected abstract int setViewId();

    protected abstract void initEvents();

    protected abstract void loadDatas();

    protected abstract void init();
}
