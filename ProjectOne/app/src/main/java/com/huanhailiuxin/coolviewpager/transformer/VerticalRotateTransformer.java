package com.huanhailiuxin.coolviewpager.transformer;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by 幻海流心 on 2018/1/2.
 */

public class VerticalRotateTransformer implements ViewPager.PageTransformer {
    private float MAX_ROTATE = 90F;

    @Override
    public void transformPage(View page, float position) {
        page.setTranslationX(page.getWidth() * -position);
        page.setTranslationY(page.getHeight() * position);
        page.setCameraDistance(10000F);
        if(position < -1){
            //不注掉,在LoopViewPager里面使用会出现显示异常
        }else if(position <= 0){
            page.setPivotX(page.getWidth()/2);
            page.setPivotY(page.getHeight());
            page.setRotationX(MAX_ROTATE * -position);
        }else if(position <= 1){
            page.setPivotX(page.getWidth()/2);
            page.setPivotY(0f);
            page.setRotationX(MAX_ROTATE * -position);
        }else {
            //不注掉,在LoopViewPager里面使用会出现显示异常
        }
    }
}
