package com.example.liu.shouyou72g.module.main.ui;

import android.content.Intent;
import android.os.CountDownTimer;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liu.shouyou72g.R;
import com.example.liu.shouyou72g.base.BaseActivity;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {


    private static  int MICOUNT = 12;
    private CheckBox cb_reg;
    private CheckBox cb_reg1;
    private CheckBox cb_reg2;
    private TextView tv_yan;

    @Override
    protected void findViews() {
        cb_reg = (CheckBox) findViewById(R.id.cb_reg);
        //让cb拥有超链接功能
        //cb_reg.setText(Html.fromHtml("我已接受<h1>注册协议</h1>"));
        cb_reg.setMovementMethod(new LinkMovementMethod());
        cb_reg1 = (CheckBox) findViewById(R.id.cb_reg1);

        cb_reg2 = (CheckBox) findViewById(R.id.cb_reg2);
        cb_reg2.setMovementMethod(new LinkMovementMethod());
        String strAgree="我已阅读并接受注册协议";
//        原始字符串封装到超链接字符串
        SpannableString spanStr = new SpannableString(strAgree);
        //点击连接时候监听，起始、终止位置，
        spanStr.setSpan(new ClickableSpan() {

            @Override
            public void onClick(View widget) {
                Toast.makeText(RegisterActivity.this,"点击超链接可以跳转",Toast.LENGTH_SHORT).show();
            }
        },6,strAgree.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        cb_reg2.setText(spanStr);//把超链接字符串设置到cb上

        tv_yan = (TextView) findViewById(R.id.tv_yan);
    }

    @Override
    protected int setViewId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initEvents() {
        cb_reg1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,ProtocalActivity.class));
            }
        });

        tv_yan.setOnClickListener(this);
    }

    @Override
    protected void loadDatas() {

    }

    @Override
    protected void init() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_yan:
                //启动倒计时 总时长，回调间隔时间
                Toast.makeText(RegisterActivity.this,"点击了",Toast.LENGTH_SHORT).show();
                new CountDownTimer(MICOUNT*1000,1000){

                    @Override
                    public void onTick(long millisUntilFinished) {
                        MICOUNT--;
                        tv_yan.setEnabled(false);
                        tv_yan.setText(MICOUNT+"");
                    }

                    @Override
                    public void onFinish() {
                        MICOUNT=12;
                        tv_yan.setEnabled(true);
                        tv_yan.setText("重新获取验证码");
                    }
                }.start();
                break;


            default: break;
        }
    }
}
