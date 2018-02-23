package com.jet.project4;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * 作者:幻海流心
 * GitHub:https://github.com/HuanHaiLiuXin
 * 邮箱:wall0920@163.com
 * 2018/2/8 15:30
 */

public class MeasureSpecViewGroup extends LinearLayout {
    public MeasureSpecViewGroup(Context context) {
        this(context,null,0);
    }

    public MeasureSpecViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MeasureSpecViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed,l,t,r,b);
    }

    @Override
    protected void measureChild(View child, int parentWidthMeasureSpec, int parentHeightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(parentWidthMeasureSpec);
        int heightMode = MeasureSpec.getMode(parentHeightMeasureSpec);
        Log.e("Jet","measureChild:"+(widthMode==MeasureSpec.EXACTLY?"widthMode=MeasureSpec.EXACTLY":(widthMode==MeasureSpec.AT_MOST?"widthMode=MeasureSpec.AT_MOST":"widthMode=MeasureSpec.UNSPECIFIED")));
        Log.e("Jet","measureChild:"+(heightMode==MeasureSpec.EXACTLY?"heightMode=MeasureSpec.EXACTLY":(heightMode==MeasureSpec.AT_MOST?"heightMode=MeasureSpec.AT_MOST":"heightMode=MeasureSpec.UNSPECIFIED")));
        int widthSize = MeasureSpec.getSize(parentWidthMeasureSpec);
        int heightSize = MeasureSpec.getSize(parentHeightMeasureSpec);
        Log.e("Jet","measureChild:"+(widthSize>0?"widthSize=" + widthSize:(widthSize== ViewGroup.LayoutParams.MATCH_PARENT?"widthSize=ViewGroup.LayoutParams.MATCH_PARENT":(widthSize== ViewGroup.LayoutParams.WRAP_CONTENT?"widthSize=ViewGroup.LayoutParams.WRAP_CONTENT":"widthSize=待定"))));
        Log.e("Jet","measureChild:"+(heightSize>0?"heightSize=" + heightSize:(heightSize== ViewGroup.LayoutParams.MATCH_PARENT?"heightSize=ViewGroup.LayoutParams.MATCH_PARENT":(heightSize== ViewGroup.LayoutParams.WRAP_CONTENT?"heightSize=ViewGroup.LayoutParams.WRAP_CONTENT":"heightSize=待定"))));
        super.measureChild(child, parentWidthMeasureSpec, parentHeightMeasureSpec);
    }

    @Override
    protected void measureChildWithMargins(View child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
        int widthMode = MeasureSpec.getMode(parentWidthMeasureSpec);
        int heightMode = MeasureSpec.getMode(parentHeightMeasureSpec);
        Log.e("Jet","measureChildWithMargins:"+(widthMode==MeasureSpec.EXACTLY?"widthMode=MeasureSpec.EXACTLY":(widthMode==MeasureSpec.AT_MOST?"widthMode=MeasureSpec.AT_MOST":"widthMode=MeasureSpec.UNSPECIFIED")));
        Log.e("Jet","measureChildWithMargins:"+(heightMode==MeasureSpec.EXACTLY?"heightMode=MeasureSpec.EXACTLY":(heightMode==MeasureSpec.AT_MOST?"heightMode=MeasureSpec.AT_MOST":"heightMode=MeasureSpec.UNSPECIFIED")));
        int widthSize = MeasureSpec.getSize(parentWidthMeasureSpec);
        int heightSize = MeasureSpec.getSize(parentHeightMeasureSpec);
        Log.e("Jet","measureChildWithMargins:"+(widthSize>0?"widthSize=" + widthSize:(widthSize== ViewGroup.LayoutParams.MATCH_PARENT?"widthSize=ViewGroup.LayoutParams.MATCH_PARENT":(widthSize== ViewGroup.LayoutParams.WRAP_CONTENT?"widthSize=ViewGroup.LayoutParams.WRAP_CONTENT":"widthSize=待定"))));
        Log.e("Jet","measureChildWithMargins:"+(heightSize>0?"heightSize=" + heightSize:(heightSize== ViewGroup.LayoutParams.MATCH_PARENT?"heightSize=ViewGroup.LayoutParams.MATCH_PARENT":(heightSize== ViewGroup.LayoutParams.WRAP_CONTENT?"heightSize=ViewGroup.LayoutParams.WRAP_CONTENT":"heightSize=待定"))));
        super.measureChildWithMargins(child, parentWidthMeasureSpec, widthUsed, parentHeightMeasureSpec, heightUsed);
    }

}
