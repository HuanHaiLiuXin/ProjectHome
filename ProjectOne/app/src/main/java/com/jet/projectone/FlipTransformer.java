package com.jet.projectone;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by 幻海流心 on 2018/1/2.
 */

public class FlipTransformer implements ViewPager.PageTransformer{
    private float MAX_ROATATE = 90F;

    @Override
    public void transformPage(View page, float position) {
        page.setCameraDistance(20000F);
        page.setPivotX(page.getWidth()/2);
        page.setPivotY(page.getHeight()/2);
        page.setTranslationX(page.getWidth() * -position);
        if(position < -1){
            page.setAlpha(0F);
            page.setRotationY(-MAX_ROATATE);
        }else if(position <= 0){
            page.setAlpha(1 - Math.abs(position));
            page.setRotationY(MAX_ROATATE * position);
        }else if(position <= 1){
            page.setAlpha(1 - Math.abs(position));
            page.setRotationY(MAX_ROATATE * position);
        }else{
            page.setAlpha(0F);
            page.setRotationY(MAX_ROATATE);
        }
    }
}
