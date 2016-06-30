package com.example.liu.shouyou72g.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by liu on 2016/6/27.
 */
public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view=inflater.inflate(setViewId(),container,false);

        findViews(view);
        init();
        initEvents();
        loadDatas();
        return view;
    }

    protected abstract int setViewId();

    protected abstract void findViews(View view);

    protected abstract void init();

    protected abstract void initEvents();

    protected abstract void loadDatas();


}
