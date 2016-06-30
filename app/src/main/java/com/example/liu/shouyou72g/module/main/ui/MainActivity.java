package com.example.liu.shouyou72g.module.main.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.LinearLayout;

import com.example.liu.shouyou72g.R;
import com.example.liu.shouyou72g.base.BaseActivity;
import com.example.liu.shouyou72g.common.custom.BottomMenu;
import com.example.liu.shouyou72g.module.guess.ui.GuessFragment;
import com.example.liu.shouyou72g.module.home.ui.HomeFragment;
import com.example.liu.shouyou72g.module.me.ui.MeFragment;
import com.example.liu.shouyou72g.module.money.ui.MoneyFragment;
import com.example.liu.shouyou72g.module.shop.ui.ShopFragment;

import java.util.HashMap;

public class MainActivity extends BaseActivity {


    private BottomMenu bm_main1;
    private BottomMenu bm_main2;
    private BottomMenu bm_main3;
    private BottomMenu bm_main4;
    private BottomMenu bm_main5;
    private boolean isMenuChoose;
    private LinearLayout ll_main;
    private HomeFragment homeFragment;
    private MoneyFragment moneyFragment;
    private GuessFragment guessFragment;
    private ShopFragment shopFragment;
    private MeFragment meFragment;
    private BottomMenu[] arry_bm = {bm_main1, bm_main2, bm_main3, bm_main4, bm_main5};
    private Fragment[] fragment = {homeFragment, moneyFragment, guessFragment, shopFragment, meFragment};


    //可以用map实现底部按钮和fragment的映射
    private HashMap<BottomMenu, Fragment> bm_fm;
    private Fragment curfragment;
    Fragment lastFragment;

    @Override
    protected void findViews() {
        //找到5个menu控件的父容器
        ll_main = (LinearLayout) findViewById(R.id.ll_main);

        arry_bm[0] = (BottomMenu) findViewById(R.id.bm_main1);
        arry_bm[1] = (BottomMenu) findViewById(R.id.bm_main2);
        arry_bm[2] = (BottomMenu) findViewById(R.id.bm_main3);
        arry_bm[3] = (BottomMenu) findViewById(R.id.bm_main4);
        arry_bm[4] = (BottomMenu) findViewById(R.id.bm_main5);

    }

    @Override
    protected int setViewId() {
        return R.layout.activity_main;
    }


    public void onChoose(View v) {
        BottomMenu cursel_bm_menu = (BottomMenu) v;//当前点击的menu
        if (!cursel_bm_menu.equals(arry_bm[0])) {
            arry_bm[0].unSelect();
            cursel_bm_menu.onSelect();

            /*//得到选中的view在父容器中的位置下标
            int indexOfChild = ((ViewGroup) cursel_bm_menu.getParent()).indexOfChild(cursel_bm_menu);
            Toast.makeText(MainActivity.this,""+indexOfChild,Toast.LENGTH_SHORT).show();
            //添加fragment
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fl_main,fragment[indexOfChild]);
            ft.commit();*/
            //得到BottomMenu对应的Fragment
            curfragment = bm_fm.get(cursel_bm_menu);
            //添加Fragment到framlayout上
            addFragment(curfragment,cursel_bm_menu);
            arry_bm[0] = cursel_bm_menu;
        }

    }

    private void addFragment(Fragment curfragment, BottomMenu cursel_bm_menu) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        switch (cursel_bm_menu.getId()){
            case R.id.bm_main1:
                if (!(lastFragment instanceof HomeFragment)) {
                    ft.hide(lastFragment);
                }
                showFragment(ft,homeFragment);
                break;
            case R.id.bm_main2:
                if (!(lastFragment instanceof MoneyFragment)) {
                    ft.hide(lastFragment);
                }
                showFragment(ft,moneyFragment);
                break;
            case R.id.bm_main3:
                if (!(lastFragment instanceof GuessFragment)) {
                    ft.hide(lastFragment);
                }
                showFragment(ft,guessFragment);
                break;
            case R.id.bm_main4:
                if (!(lastFragment instanceof ShopFragment)) {
                    ft.hide(lastFragment);
                }
                showFragment(ft,shopFragment);
                break;
            case R.id.bm_main5:
                if (!(lastFragment instanceof MeFragment)) {
                    ft.hide(lastFragment);
                }
                showFragment(ft,meFragment);
                break;
            default: break;
        }
        ft.commit();


    }

    private void showFragment(FragmentTransaction ft, Fragment fragment) {
        ft.show(fragment);
        lastFragment=fragment;
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void loadDatas() {

    }

    @Override
    protected void init() {

        homeFragment = new HomeFragment();
        moneyFragment = new MoneyFragment();
        guessFragment = new GuessFragment();
        shopFragment = new ShopFragment();
        meFragment = new MeFragment();
        lastFragment =homeFragment;

        bm_fm=new HashMap<>();
        bm_fm.put(arry_bm[0], homeFragment);
        bm_fm.put(arry_bm[1], moneyFragment);
        bm_fm.put(arry_bm[2], guessFragment);
        bm_fm.put(arry_bm[3], shopFragment);
        bm_fm.put(arry_bm[4], meFragment);

        //默认选中第一个,并显示第一个fragment
        arry_bm[0].onSelect();

        //开启FragmentTransaction，先把5个fragment添加进来，并设置隐藏
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fl_main, this.homeFragment);
        ft.show(this.homeFragment);
        ft.add(R.id.fl_main, moneyFragment);
        ft.hide(moneyFragment);
        ft.add(R.id.fl_main, guessFragment);
        ft.hide(guessFragment);
        ft.add(R.id.fl_main, shopFragment);
        ft.hide(shopFragment);
        ft.add(R.id.fl_main, meFragment);
        ft.hide(meFragment);
        ft.commit();



    }
}
