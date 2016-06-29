package com.example.liu.shouyou72g.module.main.ui;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.example.liu.shouyou72g.R;
import com.example.liu.shouyou72g.base.BaseActivity;
import com.example.liu.shouyou72g.common.constant.Constant;
import com.se7en.utils.SystemUtil;

import java.util.ArrayList;
import java.util.List;

public class WelcomActivity extends BaseActivity {


    private ViewPager vp_welcom;
    private List<ImageView> list_welcome_vp;
    private Button btn_vp_welcom;

    private int appcurVersion;
    private int appoldVersion;
    private ImageView iv_logo;
    private ImageView iv_about;
    private boolean bIsLogin=false;

    @Override
    protected int setViewId() {
        return R.layout.activity_welcom;
    }
    @Override
    protected void findViews() {
        vp_welcom = (ViewPager) findViewById(R.id.vp_welcom);
        btn_vp_welcom = (Button) findViewById(R.id.btn_vp_welcom);

        iv_logo = (ImageView) findViewById(R.id.iv_logo);
        iv_about = (ImageView) findViewById(R.id.iv_about);
    }

    @Override
    protected void init() {
        SystemUtil.getSharedBoolean(Constant.LOGIN_FLAG,false);
        appcurVersion = SystemUtil.getSystemVersionCode();
        appoldVersion = SystemUtil.getSharedInt(Constant.VERSION_STRING, -1);
        if (appoldVersion == -1||appcurVersion>appoldVersion) {
            list_welcome_vp = new ArrayList<>();
            addImageview(R.mipmap.bg_guide_01);
            addImageview(R.mipmap.bg_guide_02);
            addImageview(R.mipmap.bg_guide_03);
            addImageview(R.mipmap.bg_guide_04);
            vp_welcom.setAdapter(new PagerAdapter() {

                @Override
                public int getCount() {
                    return list_welcome_vp != null ? list_welcome_vp.size() : 0;
                }

                @Override
                public Object instantiateItem(ViewGroup container, int position) {
                    container.addView(list_welcome_vp.get(position));
                    return list_welcome_vp.get(position);
                }

                @Override
                public void destroyItem(ViewGroup container, int position, Object object) {
                    container.removeView((View) object);
                }

                @Override
                public boolean isViewFromObject(View view, Object object) {
                    return view == object;
                }
            });
        } else {
            iv_logo.setVisibility(View.VISIBLE);

            showTextLogoAnim();//启动左侧飞入的动画
        }

    }


    @Override
    protected void initEvents() {

        vp_welcom.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == list_welcome_vp.size()-1) {
                    btn_vp_welcom.setVisibility(View.VISIBLE);
                } else {
                    btn_vp_welcom.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        btn_vp_welcom.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //是否是第一次
                SystemUtil.setSharedInt(Constant.VERSION_STRING,appcurVersion);
                btn_vp_welcom.setVisibility(View.GONE);
                vp_welcom.setVisibility(View.GONE);
                showTextLogoAnim();
                startActivity(new Intent(WelcomActivity.this,RBActivity.class));
                finish();
            }
        });

    }

    @Override
    protected void loadDatas() {

    }


    private void addImageview(int resId) {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(resId);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        list_welcome_vp.add(imageView);
    }

    protected void showTextLogoAnim(){
        TranslateAnimation tranAnim = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,-1.0f
                ,Animation.RELATIVE_TO_PARENT,0f
                ,Animation.RELATIVE_TO_PARENT,0
                ,Animation.RELATIVE_TO_PARENT,0);
        tranAnim.setDuration(2000);
        tranAnim.setInterpolator(new OvershootInterpolator());//向前冲再回来
        iv_logo.startAnimation(tranAnim);
        tranAnim.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                showIconAnim();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void showIconAnim() {
        iv_about.setVisibility(View.VISIBLE);
        TranslateAnimation tranAnim = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,0
                ,Animation.RELATIVE_TO_PARENT,0
                ,Animation.RELATIVE_TO_PARENT,-1.0f
                ,Animation.RELATIVE_TO_PARENT,0f);
        tranAnim.setDuration(2000);
        tranAnim.setInterpolator(new BounceInterpolator());//向前冲再回来
        iv_about.startAnimation(tranAnim);
        tranAnim.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }
            //如果已经勾选过记住密码，且登陆成功，跳过红包页面
            @Override
            public void onAnimationEnd(Animation animation) {
                if (!bIsLogin) {
                    startActivity(new Intent(WelcomActivity.this, RBActivity.class));
                } else {
                    startActivity(new Intent(WelcomActivity.this, MainActivity.class));
                }
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
