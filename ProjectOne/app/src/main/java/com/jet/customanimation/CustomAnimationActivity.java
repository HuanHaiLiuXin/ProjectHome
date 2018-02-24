package com.jet.customanimation;

import android.view.View;

import com.jet.project1.BaseActivity;
import com.jet.project1.R;
import com.mrzk.flipcards.MainActivity;

/**
 * 作者:幻海流心
 * GitHub:https://github.com/HuanHaiLiuXin
 * 邮箱:wall0920@163.com
 * 2018/2/23 20:50
 */

public class CustomAnimationActivity extends BaseActivity {
    @Override
    public int getContentView() {
        return R.layout.activity_customanimation;
    }

    @Override
    public void initViews() {

    }

    public void goFlipCards3D(View view) {
        jump(MainActivity.class);
    }

    public void goCopyFlip3D(View view) {
        jump(CopyFlipAnimationActivity.class);
    }

    public void goMyCustomAnimation(View view) {
        jump(MyCustomAnimationActivity.class);
    }
}
