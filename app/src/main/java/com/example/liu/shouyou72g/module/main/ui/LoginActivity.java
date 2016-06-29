package com.example.liu.shouyou72g.module.main.ui;

import android.content.Intent;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.liu.shouyou72g.R;
import com.example.liu.shouyou72g.base.BaseActivity;
import com.example.liu.shouyou72g.base.NetCallback;
import com.example.liu.shouyou72g.common.constant.Constant;
import com.example.liu.shouyou72g.common.net.HttpNet;
import com.example.liu.shouyou72g.module.home.bean.LoginInfo;
import com.google.gson.Gson;
import com.se7en.utils.SystemUtil;

import java.util.HashMap;

public class LoginActivity extends BaseActivity {


    private long lcurtime;
    private long mlExittime=100;
    private Button btn_login;
    private EditText et_phnum;
    private EditText et_pwdnum;

    private CheckBox cb_rememPwd;

    @Override
    protected void findViews() {
        btn_login = (Button) findViewById(R.id.btn_login);
        et_phnum = (EditText) findViewById(R.id.et_phnum);
        et_pwdnum = (EditText) findViewById(R.id.et_pwdnum);
        cb_rememPwd = (CheckBox) findViewById(R.id.cb_rememPwd);
    }

    @Override
    protected int setViewId() {
        return R.layout.activity_login;
    }



    @Override
    protected void initEvents() {
        btn_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this,"点击了登陆",Toast.LENGTH_SHORT).show();
                String et_phnum = LoginActivity.this.et_phnum.getText().toString();
                String et_pwdnum = LoginActivity.this.et_pwdnum.getText().toString();

                //base64对密码加密
                String strPsw = new String(Base64.encode(et_pwdnum.getBytes(), Base64.DEFAULT));
                //组装网络请求参数map
                HashMap<String, String> params = new HashMap<>();
                params.put("username",et_phnum);
                params.put("password",strPsw);

                HttpNet.doHttpRequest("POST", Constant.LOGIN_URL, params, new NetCallback() {

                    @Override
                    public void success(String strResult) {
                        Log.e("doHttpRequest", "success:网络访问 "+strResult);
                        doLoginInfo(strResult);
                    }

                    @Override
                    public void fail(String strResult) {
                        Log.e("doHttpRequest", "fail:网络访问 "+strResult);
                    }
                });
            }
        });
    }

    /**
     * 对登陆返回的信息处理
     * @param strResult
     */
    private void doLoginInfo(String strResult) {
        LoginInfo loginInfo = new Gson().fromJson(strResult, LoginInfo.class);
        String state = loginInfo.getState();
        if (state.equals("success")) {
            Log.e("loginInfo", "success: 成功登陆");
            if (cb_rememPwd.isChecked()) {
//                Log.e("doLoginInfo", "doLoginInfo: ");
                SystemUtil.setSharedBoolean(Constant.LOGIN_FLAG,true);
            } else {
                SystemUtil.setSharedBoolean(Constant.LOGIN_FLAG,false);

            }
            //登陆成功后显示主页面并销毁当前页面
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            finish();
        } else {
            Log.e("loginInfo", "success: 登陆失败");
        }
    }

    @Override
    protected void loadDatas() {

    }

    @Override
    protected void init() {

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK) {//如果点击回退
            lcurtime = System.currentTimeMillis();
            //连续两次按回退键的时间间隔<2S就退出程序
            //mlExittime一开始给个基准时间只要保证(lcurtime - mlExittime) > 2000就行
            if ((lcurtime - mlExittime) > 2000) {
                Toast.makeText(LoginActivity.this,"再按一次退出",Toast.LENGTH_SHORT).show();
                mlExittime=lcurtime;
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
