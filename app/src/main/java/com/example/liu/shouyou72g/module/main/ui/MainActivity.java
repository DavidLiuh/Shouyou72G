package com.example.liu.shouyou72g.module.main.ui;

import android.view.View;

import com.example.liu.shouyou72g.R;
import com.example.liu.shouyou72g.base.BaseActivity;
import com.example.liu.shouyou72g.common.custom.BottomMenu;

public class MainActivity extends BaseActivity {


    private BottomMenu bm_main1;
    private BottomMenu bm_main2;
    private BottomMenu bm_main3;
    private BottomMenu bm_main4;
    private BottomMenu bm_main5;
    private BottomMenu[] arry_bm;
    @Override
    protected void findViews() {
        /*arry_bm[0]= (BottomMenu) findViewById(R.id.bm_main1);
        arry_bm[1] = (BottomMenu) findViewById(R.id.bm_main2);
        arry_bm[2] = (BottomMenu) findViewById(R.id.bm_main3);
        arry_bm[3] = (BottomMenu) findViewById(R.id.bm_main4);
        arry_bm[4] = (BottomMenu) findViewById(R.id.bm_main5);*/
        mLastSelMenu= (BottomMenu) findViewById(R.id.bm_main1);

    }

    @Override
    protected int setViewId() {
        return R.layout.activity_main;
    }

    BottomMenu mLastSelMenu=null;

    public void onChoose(View v){

        BottomMenu bm_menu = (BottomMenu) v;
        bm_menu.onSelect();
    }
    @Override
    protected void initEvents() {

    }

    @Override
    protected void loadDatas() {

    }

    @Override
    protected void init() {
        //弹出一个poupwindow

    }
}
