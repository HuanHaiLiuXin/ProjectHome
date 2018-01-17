package com.jet.projectone;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.jet.projectone.verticaltransformer.DefaultTransformer;

/**
 * 作者:幻海流心
 * GitHub:https://github.com/HuanHaiLiuXin
 * 邮箱:wall0920@163.com
 * 2018/1/16 11:03
 */

public class VerticalViewPagerOri extends ViewPager {

    public VerticalViewPagerOri(@NonNull Context context) {
        this(context,null);
    }

    public VerticalViewPagerOri(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setPageTransformer(false,new DefaultTransformer());
    }

    /**
     * 将事件原始X,Y坐标进行置换
     * @param event
     * @return
     */
    private MotionEvent swapTouchEvent(MotionEvent event){
        float width = getWidth();
        float height = getHeight();
        float swappedX = (event.getY() / height) * width;
        float swappedY = (event.getX() / width) * height;
        event.setLocation(swappedX,swappedY);
        return event;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //因为我们是要进行垂直方向的滑动,所以先将原始事件的X,Y坐标进行置换,然后作为参数传入super.onInterceptTouchEvent
        boolean intercept = super.onInterceptTouchEvent(swapTouchEvent(ev));
        //如果intercept为true,事件将被拦截,由ViewPager实例的onTouchEvent进行处理;
        //如果intercept为false,事件不被拦截,由ViewPager当前的子View进行处理;
        //所以需要再次执行swapTouchEvent,将已经被置换过的MotionEvent实例重置,在intercept为false情况下,将正常X,Y坐标值的MotionEvent交由子View处理
        swapTouchEvent(ev);
        return intercept;
    }

    /**
     * 如果onInterceptTouchEvent返回值为true,则onInterceptTouchEvent中的MotionEvent被拦截,不会继续传递到子View,
     * 由onTouchEvent继续处理
     * @param ev
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        //onInterceptTouchEvent中,对MotionEvent进行了2次置换,参数中的MotionEvent的X,Y坐标和初始值一致;
        //因为我们是要进行垂直方向的滑动,所以需要再次置换
        return super.onTouchEvent(swapTouchEvent(ev));
    }
}
