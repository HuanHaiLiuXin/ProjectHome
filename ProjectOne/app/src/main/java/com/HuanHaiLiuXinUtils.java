package com;

import android.app.Activity;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * 作者:幻海流心
 * GitHub:https://github.com/HuanHaiLiuXin
 * 邮箱:wall0920@163.com
 * 2018/2/27 17:07
 */

public final class HuanHaiLiuXinUtils {
    private HuanHaiLiuXinUtils() {
        throw new UnsupportedOperationException("工具类禁止创建新实例!");
    }

    public static DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
    /**
     * 密度
     */
    public static final float DENSITY = displayMetrics.density;

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     *
     * @param dpValue 尺寸dip
     * @return 像素值
     */
    public static int dip2px(float dpValue) {
        final float scale = DENSITY;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     *
     * @param pxValue 尺寸像素
     * @return DIP值
     */
    public static int px2dip(float pxValue) {
        final float scale = DENSITY;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 sp
     *
     * @param pxValue 尺寸像素
     * @return SP值
     */
    public static int px2sp(float pxValue) {
        float fontScale = displayMetrics.scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 sp 的单位 转成为 px
     *
     * @param spValue SP值
     * @return 像素值
     */
    public static int sp2px(float spValue) {
        float fontScale = displayMetrics.scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 获取屏幕宽度
     *
     * @return 屏幕宽度
     */
    public static int getScreenW() {
        int w = displayMetrics.widthPixels;
        return w;
    }

    /**
     * 获取屏幕高度
     *
     * @return 屏幕高度
     */
    public static int getScreenH() {
        int h = displayMetrics.heightPixels;
        return h;
    }

    /**
     * Return the status bar's height.
     *
     * @return the status bar's height
     */
    public static int getStatusBarHeight() {
        Resources resources = Resources.getSystem();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        return resources.getDimensionPixelSize(resourceId);
    }

    public static boolean isActivityFullScreen(Activity activity){
        return (activity.getWindow().getAttributes().flags & WindowManager.LayoutParams.FLAG_FULLSCREEN)==WindowManager.LayoutParams.FLAG_FULLSCREEN;
    }
}
