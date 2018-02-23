package com.jet.project1.transformer;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;


/**
 * 实现Fragment翻转效果的PageTransformer
 * 参考:https://github.com/youmu178/scrollpager
 *
 * 作者:幻海流心
 * GitHub:https://github.com/HuanHaiLiuXin
 * 邮箱:wall0920@163.com
 * 2018/1/29 11:15
 */
public class FragmentRotateTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.65F;
    private float degree;
    private OnRotateListener onRotateListener;

    @Override
    public void transformPage(@NonNull View page, float position) {
        int width = page.getWidth();
        int height = page.getHeight();
        if(position > 1 || position < -1){
            page.setScaleX(MIN_SCALE);
            page.setScaleY(MIN_SCALE);
        }else{
            float currScale = MIN_SCALE + (1.0F - MIN_SCALE) * (1 - Math.abs(position));
            page.setScaleX(currScale);
            page.setScaleY(currScale);
        }
        if(position == 0F){
            return;
        }else{
            degree = 360.0F;
        }
        float rotation = 0F;
        position = gainCurrentPosition(position);
        if(position >= 0.25F && position <= 0.75F){
            //为防止字体颠倒，强制变换角度
            position += 0.50F;
            if(onRotateListener != null){
                onRotateListener.onRotateNotify();
            }
        }else{
            if(onRotateListener != null){
                onRotateListener.onRotateReset();
            }
        }
        rotation = degree * position;
        page.setRotationY(rotation);
        page.setPivotX(width * 0.5F);
        page.setPivotY(height * 0.5F);
    }

    private float gainCurrentPosition(float position){
        int intNum = (int)(Math.abs(position));
        if(position < 0){
            position += (intNum + 1);
        }else if(position > 0){
            position -= intNum;
        }
        return position;
    }

    public OnRotateListener getOnRotateListener() {
        return onRotateListener;
    }

    public void setOnRotateListener(OnRotateListener onRotateListener) {
        this.onRotateListener = onRotateListener;
    }
}
