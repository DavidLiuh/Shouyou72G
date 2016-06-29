package com.example.liu.shouyou72g.module.main.ui;

import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

import com.example.liu.shouyou72g.R;
import com.example.liu.shouyou72g.base.BaseActivity;

public class RBActivity extends BaseActivity implements View.OnClickListener {


    private PopupWindow popup_rb;
    private View mRBview;
    private Button btn_register;
    private Button btn_login;

    @Override
    protected void findViews() {

    }

    @Override
    protected int setViewId() {
        return R.layout.activity_rb;
    }

    @Override
    protected void initEvents() {
        btn_register.setOnClickListener(this);
        btn_login.setOnClickListener(this);
    }

    @Override
    protected void loadDatas() {

    }

    @Override
    protected void init() {
//        加载popupwindow布局
        mRBview = LayoutInflater.from(this).inflate(R.layout.layout_popup_rb, null);
        btn_register = (Button) mRBview.findViewById(R.id.btn_register);
        btn_login = (Button) mRBview.findViewById(R.id.btn_login);

    }

    /**
     * activity真正创建完毕
     * @param hasFocus
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Log.e("TAG", "onWindowFocusChanged: 进来了没");
        if (popup_rb == null) {
            setAlpha(0.5f);
            Log.e("TAG", "onWindowFocusChanged: ");
            //Toast.makeText(getApplicationContext(),"poo",Toast.LENGTH_SHORT).show();
            //创建并显示popupwindow
            popup_rb=new PopupWindow(mRBview, ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT,false);
            popup_rb.showAtLocation(getWindow().getDecorView(), Gravity.CENTER,0,0);
        }

    }


    //要在activity推出之前关闭窗口，leaked
    @Override
    protected void onDestroy() {
        if (popup_rb!=null) {
            popup_rb.dismiss();
            popup_rb=null;
        }
        super.onDestroy();
    }

    /**
     * 设置activity的透明度
     * @param fAlpha
     */
    protected void setAlpha(float fAlpha){
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha=fAlpha;//0-1，
        getWindow().setAttributes(params);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login://点击登录按钮
                startActivity(new Intent(RBActivity.this,LoginActivity.class));

                break;
            case R.id.btn_register://点击注册按钮
                startActivity(new Intent(RBActivity.this,RegisterActivity.class));
                break;
            default: break;
        }
        finish();
    }


}
