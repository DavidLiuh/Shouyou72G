package com.example.liu.shouyou72g.module.money.ui;

import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.example.liu.shouyou72g.R;
import com.example.liu.shouyou72g.base.BaseFragment;
import com.example.liu.shouyou72g.base.ListviewCallback;
import com.example.liu.shouyou72g.common.adapter.CommonAdapter;
import com.example.liu.shouyou72g.common.adapter.ViewHolder;
import com.example.liu.shouyou72g.module.money.bean.TaskGameInfo;
import com.example.liu.shouyou72g.module.money.dao.MoneyDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liu on 2016/6/29.
 */
public class MoneyFragment extends BaseFragment {

    private boolean mbLoaded;
    private PopupWindow mpwLoad;
    private View viewPw;
    private View vmonFragment;
    private ListView lv_fm_money;
    private List<TaskGameInfo.InfoBean> mListGames;
    private CommonAdapter<TaskGameInfo.InfoBean> mAdapter;
    private View heaview;

    /**
     * 点击赚钱，显示加载数据的对话框
     * 如果初始化好了，让对话框消失
     */
    public void showLoadDialog() {
        if (!mbLoaded && (mpwLoad == null)) {
            // TODO: 2016/6/29显示对话框
            PopupWindow pw = new PopupWindow(viewPw, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, false);
            pw.showAtLocation(vmonFragment, Gravity.CENTER, 0, 0);
        }
    }

    @Override
    protected int setViewId() {
        return R.layout.layout_fm_main_money;
    }

    @Override
    protected void findViews(View view) {
        vmonFragment = view;
        lv_fm_money = (ListView) view.findViewById(R.id.lv_fm_money);

    }

    @Override
    protected void init() {
        viewPw = LayoutInflater.from(this.getActivity()).inflate(R.layout.layout_pw_load, null);
        //给listview 添加头部布局
        heaview = LayoutInflater.from(getActivity()).inflate(R.layout.lay_mon_list_head, null);
        lv_fm_money.addHeaderView(heaview);

        //准备适配器的数据并设置适配器
        mListGames = new ArrayList<>();
        mAdapter = new CommonAdapter<TaskGameInfo.InfoBean>(getActivity(), mListGames, R.layout.layout_moneylist) {

            @Override
            public void convert(ViewHolder helper, int position, TaskGameInfo.InfoBean item) {
                helper.setText(R.id.tv_task_money, item.getPlatform_name());
                helper.setRating(R.id.rb_task_money, Integer.parseInt(item.getRank()));
                helper.setImageByUrl(R.id.iv_logo_money, item.getAd_img(), getActivity());
            }
        };
        lv_fm_money.setAdapter(mAdapter);

    }

    @Override
    protected void initEvents() {
        LinearLayout game_task = (LinearLayout) heaview.findViewById(R.id.game_task);
        game_task.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),MyGame.class));

            }
        });

    }

    @Override
    protected void loadDatas() {
        MoneyDao.getMoneyData(new ListviewCallback() {

            @Override
            public void updateListview(Object obj) {
                //对更新的数据做处理
                List<TaskGameInfo.InfoBean> datas = (List<TaskGameInfo.InfoBean>) obj;
                mListGames.addAll(datas);
                //                通知适配器数据已改变,数据下载完更换弹出窗显示标志
                mAdapter.notifyDataSetChanged();
                mbLoaded = true;
                if (mpwLoad != null) {
                    mpwLoad.dismiss();
                    mpwLoad=null;
                }
            }
        });
    }
}
