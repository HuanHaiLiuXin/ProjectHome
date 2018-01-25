package com.huanhailiuxin.coolviewpager.transformer;

import android.support.v4.view.ViewPager;
import android.view.View;

public class DepthPageTransformer implements ViewPager.PageTransformer {

	private static final float MIN_SCALE = 0.75f;

	@Override
	public void transformPage(View page, float position) {
        if(position < -1){
            //不注掉,在LoopViewPager里面使用会出现显示异常
        }else if (position <= 0f) {
            page.setAlpha(1.0F + position);
            page.setTranslationX(0f);
            page.setScaleX(1f);
            page.setScaleY(1f);
		} else if (position <= 1f) {
            page.setTranslationX(page.getWidth() * -position);
            page.setAlpha(1-position);
            final float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
            page.setPivotX(page.getWidth()/2);
            page.setPivotY(page.getHeight()/2);
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
		}else {
            //不注掉,在LoopViewPager里面使用会出现显示异常
        }
	}
}
