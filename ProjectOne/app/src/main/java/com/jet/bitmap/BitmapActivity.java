package com.jet.bitmap;

import android.view.View;

import com.jet.project1.BaseActivity;
import com.jet.project1.R;

/**
 * 作者:幻海流心
 * GitHub:https://github.com/HuanHaiLiuXin
 * 邮箱:wall0920@163.com
 * 2018/2/26 15:01
 */

public class BitmapActivity extends BaseActivity {
    @Override
    public int getContentView() {
        return R.layout.activity_bitmap;
    }

    @Override
    public void initViews() {

    }

    public void BitmapSetPixelsCopy(View view) {
        jump(BitmapSetPixelsActivity.class);
    }
}
