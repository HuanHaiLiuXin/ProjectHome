package com.huanhailiuxin.coolviewpager;

import android.support.annotation.ColorInt;
import android.widget.EdgeEffect;

/**
 * 作者:幻海流心
 * GitHub:https://github.com/HuanHaiLiuXin
 * 邮箱:wall0920@163.com
 * 2018/1/17 20:52
 *
 * 为CoolViewPager提供自定义属性的设置方法
 */

interface ICoolViewPagerFeature {
    /**
     * 设置滑动方向
     *
     * @param scrollMode
     */
    void setScrollMode(CoolViewPager.ScrollMode scrollMode);

    /**
     * 设置EdgeEffect的颜色
     *
     * @param color
     */
    void setEdgeEffectColor(@ColorInt int color);

    /**
     * 设置是否绘制EdgeEffect
     *
     * @param drawEdgeEffect
     */
    void setDrawEdgeEffect(boolean drawEdgeEffect);
}
