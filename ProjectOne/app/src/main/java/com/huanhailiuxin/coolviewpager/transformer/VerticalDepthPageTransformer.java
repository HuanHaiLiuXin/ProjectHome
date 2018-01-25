package com.huanhailiuxin.coolviewpager.transformer;

import android.support.v4.view.ViewPager;
import android.view.View;

public class VerticalDepthPageTransformer implements ViewPager.PageTransformer {

	private static final float MIN_SCALE = 0.75f;

	@Override
	public void transformPage(View page, float position) {
		if (position <= 0f) {
            page.setTranslationX(0f);
            page.setScaleX(1f);
            page.setScaleY(1f);
		} else if (position <= 1f) {
			final float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
            page.setAlpha(1-position);
            page.setPivotY(0.5f * page.getHeight());
            page.setTranslationX(page.getWidth() * - position);
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
		}
	}
}
