package com.jet.project1;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * 作者:幻海流心
 * GitHub:https://github.com/HuanHaiLiuXin
 * 邮箱:wall0920@163.com
 * 2018/1/22 15:30
 */

public class ScaleTransformer implements ViewPager.PageTransformer{
    @Override
    public void transformPage(@NonNull View page, float position) {
        if(position < -1 || position > 1){
            page.setScaleX(0.70f);
            page.setScaleY(0.70f);
        }else{
            float scale = 0.70f + 0.30f * (1 - Math.abs(position));
            page.setScaleX(scale);
            page.setScaleY(scale);
            page.setVisibility(View.VISIBLE);
        }
    }
}
