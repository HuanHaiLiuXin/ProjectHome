package com.jet.canvas;

import android.view.View;

import com.jet.project1.BaseActivity;
import com.jet.project1.R;

/**
 * 作者:幻海流心
 * GitHub:https://github.com/HuanHaiLiuXin
 * 邮箱:wall0920@163.com
 * 2018/2/27 11:45
 */

public class CanvasActivity extends BaseActivity {
    @Override
    public int getContentView() {
        return R.layout.activity_canvas;
    }

    @Override
    public void initViews() {

    }

    public void goCanvasSetBitmap(View view) {
        jump(CanvasSetBitmapActivity.class);
    }
}
