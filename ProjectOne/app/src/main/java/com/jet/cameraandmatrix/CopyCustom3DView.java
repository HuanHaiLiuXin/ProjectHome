package com.jet.cameraandmatrix;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
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
 * 2018/2/23 10:42
 */

public class CopyCustom3DView extends ViewGroup {
    private Camera mCamera;
    private Matrix mMatrix;
    private Scroller mScroller;
    private VelocityTracker mTracker;
    private float mDownY = 0;
    private int standerSpeed = 2000;
    //    private float resistance = 1.6F;
    private float resistance = 0.2F;
    private int mHeight;
    private int mStartScreen = 1;
    private int mCurScreen = 1;
    private float angle = 90F;
    private int STATE = -1;
    private final int STATE_PRE = 0;
    private final int STATE_NEXT = 1;
    private final int STATE_NORMAL = 2;


    public CopyCustom3DView(Context context) {
        this(context, null, 0);
    }

    public CopyCustom3DView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CopyCustom3DView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mCamera = new Camera();
        mMatrix = new Matrix();
        mScroller = new Scroller(getContext(), new LinearInterpolator());
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        if (childCount > 0) {
            MarginLayoutParams params = (MarginLayoutParams) getLayoutParams();
            int childTop = 0;
            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);
                if (child.getVisibility() != View.GONE) {
                    if (i == 0) {
                        childTop += params.topMargin;
                    }
                    child.layout(params.leftMargin, childTop, params.leftMargin + child.getMeasuredWidth(), childTop + child.getMeasuredHeight());
                    childTop += child.getMeasuredHeight();
                }
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(measureWidth, measureHeight);
        //
        MarginLayoutParams params = (MarginLayoutParams) getLayoutParams();
        int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(measureWidth - (params.leftMargin + params.rightMargin), MeasureSpec.EXACTLY);
        int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(measureHeight - (params.topMargin + params.bottomMargin), MeasureSpec.EXACTLY);
        measureChildren(childWidthMeasureSpec, childHeightMeasureSpec);
        //
        mHeight = getMeasuredHeight();
        scrollTo(0, mHeight * mStartScreen);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        for (int i = 0; i < getChildCount(); i++) {
            drawScreen(canvas, i, getDrawingTime());
        }
    }

    private void drawScreen(Canvas canvas, int screen, long drawingTime) {
        int height = getHeight();
        int scrollHeight = height * screen;
        int scrollY = getScrollY();
        //子View在垂直方向的位置超过了显示范围,则不进行绘制
        if (scrollHeight + height < scrollY || scrollHeight > scrollY + height) {
            return;
        }
        View child = getChildAt(screen);
        float degree = 0.0F;
        degree = angle * (scrollY - scrollHeight) / height;
        if (degree > angle || degree < -angle) {
            return;
        }
        float centerX = getWidth() / 2;
        float centerY = scrollY > scrollHeight ? scrollHeight + height : scrollHeight;
        Camera camera = mCamera;
        Matrix matrix = mMatrix;

        //
        canvas.save();
        camera.save();
        camera.rotateX(degree);
        camera.getMatrix(matrix);
        camera.restore();
        matrix.preTranslate(-centerX, -centerY);
        matrix.postTranslate(centerX, centerY);
        canvas.concat(matrix);
        drawChild(canvas, child, drawingTime);
        canvas.restore();
        //
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mTracker == null) {
            mTracker = VelocityTracker.obtain();
        }
        mTracker.addMovement(event);
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_POINTER_DOWN:
            case MotionEvent.ACTION_DOWN:
                mDownY = y;
                if (!mScroller.isFinished()) {
                    mScroller.setFinalY(getScrollY());
                    mScroller.abortAnimation();
                    scrollTo(0, getScrollY());
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int disY = (int) (mDownY - y);
                mDownY = y;
                if (mScroller.isFinished()) {
                    recycleMove(disY);
                }
                break;
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_UP:
                mTracker.computeCurrentVelocity(1000);
                float velocitY = mTracker.getYVelocity();
                if (velocitY > standerSpeed || getScrollY() + mHeight / 2 < mHeight * mStartScreen) {
                    //手指向下滑动的速度>规定速度,或者上一页面的展示高度>1/2
                    STATE = STATE_PRE;
                } else if (velocitY < -standerSpeed || getScrollY() - mHeight / 2 > mHeight * mStartScreen) {
                    STATE = STATE_NEXT;
                } else {
                    STATE = STATE_NORMAL;
                }
                changeByState();
                if (mTracker != null) {
                    mTracker.recycle();
                    mTracker = null;
                }
                break;
        }
        return true;
    }

    private void changeByState() {
        switch (STATE) {
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

    private void toNormalAction() {
        STATE = STATE_NORMAL;
        int startY;
        int delta;
        int duration;
        startY = getScrollY();
        delta = mHeight * mStartScreen - startY;
        duration = (int) (Math.abs(delta) * 0.5F);
        mScroller.startScroll(0, startY, 0, delta, duration);
    }

    private void toPrePager() {
        STATE = STATE_PRE;
        int startY;
        int delta;
        int duration;
        setPre();
        startY = getScrollY() + mHeight;
        delta = mHeight * mStartScreen - startY;
        duration = (int) (Math.abs(delta) * 0.5F);
        mScroller.startScroll(0, startY, 0, delta, duration);
    }

    private void toNextPager() {
        STATE = STATE_NEXT;
        int startY;
        int delta;
        int duration;
        setNext();
        startY = getScrollY() - mHeight;
        delta = mHeight * mStartScreen - startY;
        duration = (int) (Math.abs(delta) * 0.5F);
        mScroller.startScroll(0, startY, 0, delta, duration);
    }

    private void setPre() {
        int childCount = getChildCount();
        mCurScreen = (mCurScreen - 1 + childCount) % childCount;
        View view = getChildAt(childCount - 1);
        removeViewAt(childCount - 1);
        addView(view, 0);
    }

    private void setNext() {
        int childCount = getChildCount();
        mCurScreen = (mCurScreen + 1) % childCount;
        View view = getChildAt(0);
        removeViewAt(0);
        addView(view, childCount - 1);
    }

    private void recycleMove(int delta) {
        delta = delta % mHeight;
        delta = (int) (delta / resistance);
        if (Math.abs(delta) > mHeight / 4) {
            return;
        }
        /*if(getScrollY() <= 0 && mCurScreen == 0 && delta <= 0){
            return;
        }
        if(getScrollY() >= mHeight * mCurScreen && mCurScreen == getChildCount() - 1 && delta >= 0){
            return;
        }*/
        scrollBy(0, delta);
        if (getScrollY() < 8) {
            setPre();
            scrollBy(0, mHeight);
        }
        else if (getScrollY() > mHeight * (getChildCount() - 1) - 8) {
            setNext();
            scrollBy(0, -mHeight);
        }
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }
}
