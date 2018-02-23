package com.jet.project3;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Scroller;

/**
 * 作者:幻海流心
 * GitHub:https://github.com/HuanHaiLiuXin
 * 邮箱:wall0920@163.com
 * 2018/2/9 14:48
 */

public class Custom3DView extends ViewGroup {
    private Camera mCamera;
    private Matrix mMatrix;
    //开始时的item位置
    private int mStartScreen = 1;
    private float mDownY = 0;
    private static final int standerSpeed = 2000;
    //当前item的位置
    private int mCurScreen = 1;
    //控件的高
    private int mHeight = 0;
    private VelocityTracker mTracker;
    private Scroller mScroller;
    //旋转的角度
    private float angle = 90F;
    //三种状态
    private static final int STATE_PRE = 0;
    private static final int STATE_NEXT = 1;
    private static final int STATE_NORMAL = 2;
    private int STATE = -1;
    //滑动阻力
    private float resistance = 1.6F;

    public Custom3DView(Context context) {
        this(context,null,0);
    }
    public Custom3DView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }
    public Custom3DView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mCamera = new Camera();
        mMatrix = new Matrix();
        if(mScroller == null){
            mScroller = new Scroller(getContext(),new LinearInterpolator());
        }
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
    }
    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(measureWidth,measureHeight);
        //
        MarginLayoutParams params = (MarginLayoutParams) getLayoutParams();
        int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(measureWidth - (params.leftMargin + params.rightMargin), MeasureSpec.EXACTLY);
        int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(measureHeight - (params.topMargin + params.bottomMargin), MeasureSpec.EXACTLY);
        measureChildren(childWidthMeasureSpec,childHeightMeasureSpec);
        //
        mHeight = getMeasuredHeight();
        scrollTo(0,mStartScreen * mHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        MarginLayoutParams params = (MarginLayoutParams) getLayoutParams();
        int childTop = 0;
        for(int i=0;i<getChildCount();i++){
            View child = getChildAt(i);
            if(child.getVisibility() != View.GONE){
                if(i == 0){
                    childTop += params.topMargin;
                }
                child.layout(params.leftMargin,childTop,child.getMeasuredWidth() + params.leftMargin, childTop + child.getMeasuredHeight());
                childTop = childTop + child.getMeasuredHeight();
            }
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        for (int i = 0;i<getChildCount();i++){
            drawScreen(canvas,i,getDrawingTime());
        }
    }
    private void drawScreen(Canvas canvas, int screen, long drawingTime){
        //得到当前子View的高度
        int height = getHeight();
        int scrollHeight = screen * height;
        int scrollY = this.getScrollY();
        //子View垂直方向偏移量超过了显示范围,则不进行绘制
        if(scrollHeight > scrollY + height || scrollHeight + height <scrollY){
            return;
        }
        View child = getChildAt(screen);
        int faceIndex = screen;
        float currentDegree = getScrollY() * (angle / getMeasuredHeight());
        float faceDegree = currentDegree - faceIndex * angle;
        if (faceDegree > 90 || faceDegree < -90) {
            return;
        }
        float centerY = scrollHeight < scrollY? scrollHeight + height:scrollHeight;
        float centerX = getWidth()/2;
        Camera camera = mCamera;
        Matrix matrix = mMatrix;

        //
        canvas.save();
        camera.save();
        camera.rotateX(faceDegree);
        camera.getMatrix(matrix);
        camera.restore();
        //
        matrix.preTranslate(-centerX,-centerY);
        matrix.postTranslate(centerX,centerY);
        canvas.concat(matrix);
        drawChild(canvas,child,drawingTime);
        //
        canvas.restore();
        //
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(mTracker == null){
            mTracker = VelocityTracker.obtain();
        }
        mTracker.addMovement(event);
        float y = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(!mScroller.isFinished()){
                    mScroller.setFinalY(mScroller.getCurrY());
                    mScroller.abortAnimation();
                    scrollTo(0,getScrollY());
                }
                mDownY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int disY = (int) (mDownY - y);
                mDownY = y;
                if(mScroller.isFinished()){
                    recycleMove(disY);
                }
                break;
            case MotionEvent.ACTION_UP:
                mTracker.computeCurrentVelocity(1000);
                float velocitY = mTracker.getYVelocity();
                if(velocitY > standerSpeed || (getScrollY() + mHeight / 2) / mHeight < mStartScreen){
                    //滑动速度大于规定速度,或者向上滑动时,上一页页面展示的高度超过1/2,则设定状态为STATE_PRE
                    STATE = STATE_PRE;
                }else if(velocitY < -standerSpeed || (getScrollY() + mHeight / 2) / mHeight > mStartScreen){
                    //滑动的速度大于规定的速度,或者向下滑动时,下一页页面展示的高度超过1/2,则设定状态为STATE_NEXT
                    STATE = STATE_NEXT;
                }else {
                    STATE = STATE_NORMAL;
                }
                //根据STATE进行相应的变化
                changeByState();
                if(mTracker != null){
                    mTracker.recycle();
                    mTracker = null;
                }
                break;
        }
        //返回true,消耗点击事件
        return true;
    }

    private void changeByState() {
        Log.e("Jet","changeByState:mCurScreen="+mCurScreen);
        switch (STATE){
            case STATE_NORMAL:
                toNormalAction();
                break;
            case STATE_PRE:
                toPrePager();
                break;
            case STATE_NEXT:
                toNextPager();
                break;
        }
        invalidate();
    }

    /**
     * 当前页
     */
    private void toNormalAction() {
        int startY;
        int delta;
        int duration;
        STATE = STATE_NORMAL;
        startY = getScrollY();
        delta = mHeight * mStartScreen - getScrollY();
        duration = (int) ((Math.abs(delta)) * 0.5F);
        mScroller.startScroll(0,startY,0,delta,duration);
    }

    private void recycleMove(int delta) {
        Log.e("Jet","recycleMove");
        delta = delta % mHeight;
        delta = (int) (delta / resistance);
        if(Math.abs(delta) > mHeight / 4){
            return;
        }
        if(getScrollY() <= 0 && mCurScreen <= 0 && delta <= 0){
            return;
        }
        if(mHeight * mCurScreen <= getScrollY() && mCurScreen == getChildCount() - 1 && delta >= 0){
            return;
        }
        scrollBy(0,delta);
//        if(getScrollY() < 8 && mCurScreen != 0){
        if(getScrollY() < 8){
            setPre();
            scrollBy(0,mHeight);
        }else if(getScrollY() > (getChildCount() - 1) * mHeight - 8){
            setNext();
            scrollBy(0,-mHeight);
        }
    }

    private void setNext() {
        mCurScreen = (mCurScreen + 1)%getChildCount();
        Log.e("Jet","setNext:mCurScreen="+mCurScreen);
        int childCount = getChildCount();
        View view = getChildAt(0);
        removeViewAt(0);
        addView(view,childCount - 1);
    }

    private void setPre() {
        mCurScreen = ((mCurScreen - 1) + getChildCount()) % getChildCount();
        Log.e("Jet","setPre:mCurScreen="+mCurScreen);
        int childCount = getChildCount();
        View view = getChildAt(childCount - 1);
        removeViewAt(childCount - 1);
        addView(view,0);
    }
    /**
     * 上一页
     */
    private void toPrePager(){
        int startY;
        int delta;
        int duration;
        STATE = STATE_PRE;
        setPre();
        //mScroller开始的坐标
        startY = getScrollY() + mHeight;
        setScrollY(startY);
        //mScroller移动的距离
        delta = mStartScreen * mHeight - startY;
        duration = (int) ((Math.abs(delta)) * 0.5F);
        mScroller.startScroll(0,startY,0,delta,duration);
    }

    /**
     * 下一页
     */
    private void toNextPager(){
        int startY;
        int delta;
        int duration;
        STATE = STATE_NEXT;
        setNext();
        startY = getScrollY() - mHeight;
        setScrollY(startY);
        delta = mStartScreen * mHeight - startY;
        duration = (int) ((Math.abs(delta)) * 0.5F);
        mScroller.startScroll(0,startY,0,delta,duration);
    }

    @Override
    public void computeScroll() {
        if(mScroller.computeScrollOffset()){
            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            postInvalidate();
        }
    }
}