package com.huanhailiuxin.coolviewpager.transformer;

import android.support.annotation.NonNull;
import android.view.View;

import com.huanhailiuxin.coolviewpager.CoolViewPager;

/**
 * 作者:幻海流心
 * GitHub:https://github.com/HuanHaiLiuXin
 * 邮箱:wall0920@163.com
 * 2018/1/16 11:31
 */

public class DefaultVerticalTransformer implements CoolViewPager.PageTransformer {
    @Override
    public void transformPage(@NonNull View page, float position) {
        page.setTranslationX(page.getWidth() * -position);
        page.setTranslationY(page.getHeight() * position);
    }
}
