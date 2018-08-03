package com.jet.project1;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * 作者:幻海流心
 * GitHub:https://github.com/HuanHaiLiuXin
 * 邮箱:wall0920@163.com
 * 2018/6/8 15:25
 */
public class ArtApp extends Application{
    /**
     * 功能汇总
     */
    public void funcAll(){
        //在Application中启动launchMode为standard的Activity
        Intent intent = new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

        //获取系统所能识别出的被认为是滑动的最小距离,以像素为单位
        ViewConfiguration.get(this).getScaledTouchSlop();

        //VelocityTracker获取X和Y方向,指定毫秒值内的移动距离(X,Y方向速度)
        VelocityTracker tracker = VelocityTracker.obtain();
        tracker.addMovement(null);
        tracker.computeCurrentVelocity(666);
        float xVelocity = tracker.getXVelocity();
        float yVelocity = tracker.getYVelocity();
        tracker.clear();
        tracker.recycle();

        //通过改变View的LayoutParams来实现View的滑动
        View v = null;
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
        params.leftMargin += 100;
        v.setLayoutParams(params);

        //通过动画实现View的弹性滑动
        Animator animator1 = ObjectAnimator.ofFloat(v,"translationX",0,100).setDuration(400);
        Animator animator2 = ObjectAnimator.ofFloat(v,"translationY",0,200).setDuration(400);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(animator1,animator2);
    }

    public class ArtView extends View{
        //自定义View中持有Scroller实例
        private Scroller scroller;

        public ArtView(Context context) {
            this(context,null,0);
        }
        public ArtView(Context context, @Nullable AttributeSet attrs) {
            this(context, attrs,0);
        }
        public ArtView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            init(context);
        }
        private void init(Context context){
            //初始化Scroller实例
            scroller = new Scroller(context);
        }

        /**
         * 实现在duration毫秒内,滑动View的内容区域,实现mScrollX=targetX,mScrollY=targetY
         * @param targetX
         * @param targetY
         * @param duration
         */
        public void smoothScroll(int targetX,int targetY,int duration){
            int currScrollX = this.getScrollX();
            int currScrollY = this.getScrollY();
            int deltaX = targetX - currScrollX;
            int deltaY = targetY - currScrollY;
            scroller.startScroll(currScrollX,currScrollY,deltaX,deltaY,duration);
            invalidate();
        }
        @Override
        public void computeScroll() {
            super.computeScroll();
            if(scroller.computeScrollOffset()){
                scrollTo(scroller.getCurrX(),scroller.getCurrY());
                postInvalidate();
            }
        }
    }
}