package com.huanhailiuxin.coolviewpager.transformer;

import android.support.annotation.NonNull;
import android.view.View;

import com.huanhailiuxin.coolviewpager.CoolViewPager;

/**
 * 作者:幻海流心
 * GitHub:https://github.com/HuanHaiLiuXin
 * 邮箱:wall0920@163.com
 * 2018/1/22 17:14
 */

public class CycleHorizontalTransformer implements CoolViewPager.PageTransformer{
    private float MIN_SCALE = 0.60f;
    private float MAX_SCALE = 1.00f;

    @Override
    public void transformPage(@NonNull View page, float position) {
        if(position < -2 || position > 2){
            page.setVisibility(View.GONE);
        }else{

        }
    }
}
