package com.example.liu.shouyou72g.common.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.liu.shouyou72g.R;

/**
 * Created by liu on 2016/6/28.
 */
public class BottomMenu extends LinearLayout {

    private ImageView iv_main_menu;
    private TextView tv_main_menu;
    private int nor;
    private int pre;

    public BottomMenu(Context context) {
        super(context);
    }

    public BottomMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        //把自定义布局加载进来
        View view = LayoutInflater.from(context).inflate(R.layout.layout_bottomnemu, this, true);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BottomMenu);
        String titleStr = typedArray.getString(R.styleable.BottomMenu_titleString);
        nor = typedArray.getResourceId(R.styleable.BottomMenu_normalPic, -1);
        pre = typedArray.getResourceId(R.styleable.BottomMenu_pressPic, -1);

        tv_main_menu = (TextView) view.findViewById(R.id.tv_main_menu);
        iv_main_menu = (ImageView) view.findViewById(R.id.iv_main_menu);
        tv_main_menu.setText(titleStr);
        iv_main_menu.setImageResource(nor);
        typedArray.recycle();


    }

    boolean bSelect = false;

    //点击菜单时调用，切图，设动画
    public void onSelect() {
        if (bSelect) {
            return;
        } else {
            bSelect = true;
            //切图，设为选中
            iv_main_menu.setImageResource(pre);
            //让标题执行平移动画，往下消失
            TranslateAnimation tranAnim = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 1.0f);
            tranAnim.setFillAfter(true);
            tranAnim.setDuration(200);
            tv_main_menu.startAnimation(tranAnim);
            //让图片执行缩放
            ScaleAnimation scaleAnim = new ScaleAnimation(1, 1.5f, 1, 1.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0);
            scaleAnim.setDuration(200);
            scaleAnim.setFillAfter(true);
            iv_main_menu.startAnimation(scaleAnim);
        }
    }

    public void unSelect() {

        bSelect = false;
        //切图，设为未选中
        iv_main_menu.setImageResource(nor);
        //让标题执行平移动画，往上出现
        TranslateAnimation tranAnim = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0f);
        tranAnim.setFillAfter(true);
        tranAnim.setDuration(200);
        tv_main_menu.startAnimation(tranAnim);
        //让图片执行缩放
        ScaleAnimation scaleAnim = new ScaleAnimation(1.5f, 1f, 1.5f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0);
        scaleAnim.setDuration(200);
        scaleAnim.setFillAfter(true);
        iv_main_menu.startAnimation(scaleAnim);
    }


}
