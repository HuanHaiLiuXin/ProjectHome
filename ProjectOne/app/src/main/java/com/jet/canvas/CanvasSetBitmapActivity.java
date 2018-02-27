package com.jet.canvas;

import android.graphics.Bitmap;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.jet.project1.BaseActivity;
import com.jet.project1.R;

/**
 * 作者:幻海流心
 * GitHub:https://github.com/HuanHaiLiuXin
 * 邮箱:wall0920@163.com
 * 2018/2/27 11:34
 */

public class CanvasSetBitmapActivity extends BaseActivity {
    private CanvasSetBitmapView cv;
    private AppCompatImageView iv;

    @Override
    public int getContentView() {
        return R.layout.activity_canvas_setbitmap;
    }

    @Override
    public void initViews() {
        cv = findViewById(R.id.cv);
        iv = findViewById(R.id.iv);
        cv.setOnSetBitmapListener(new CanvasSetBitmapView.OnSetBitmapListener() {
            @Override
            public void setBitmap(Bitmap bitmap) {
                iv.setImageBitmap(bitmap);
            }
        });
    }

    public void showCurrCanvasBitmap(View view) {
        iv.setImageBitmap(cv.ori);
    }
}
