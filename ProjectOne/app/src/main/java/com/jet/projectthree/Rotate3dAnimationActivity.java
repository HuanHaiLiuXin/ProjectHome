package com.jet.projectthree;

import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.jet.projectone.BaseActivity;
import com.jet.projectone.R;

/**
 * 作者:幻海流心
 * GitHub:https://github.com/HuanHaiLiuXin
 * 邮箱:wall0920@163.com
 * 2018/2/2 17:11
 */

public class Rotate3dAnimationActivity extends BaseActivity {
    private ImageView iv;

    @Override
    public int getContentView() {
        return R.layout.activity_rotate3danimation;
    }

    @Override
    public void initViews() {
        iv = findViewById(R.id.iv);
    }

    public void rotateAnimation(View view) {
        int width = getWindowManager().getDefaultDisplay().getWidth();
        int height = getWindowManager().getDefaultDisplay().getHeight();
        Rotate3dAnimation animation = new Rotate3dAnimation(0,360,width/2,height/2,240,false);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.setDuration(2000);
        animation.setFillAfter(false);
        iv.startAnimation(animation);
    }





}
