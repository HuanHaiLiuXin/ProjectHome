package com.jet.customanimation;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * 作者:幻海流心
 * GitHub:https://github.com/HuanHaiLiuXin
 * 邮箱:wall0920@163.com
 * 2018/2/24 10:30
 */

public class CopyFlipCardAnimation extends Animation {
    private Camera mCamera;
    private float mFromDegree;
    private float mToDegree;
    private float mCenterX;
    private float mCenterY;

    public CopyFlipCardAnimation(float mFromDegree, float mToDegree, float mCenterX, float mCenterY) {
        this.mFromDegree = mFromDegree;
        this.mToDegree = mToDegree;
        this.mCenterX = mCenterX;
        this.mCenterY = mCenterY;
        Log.e("Jet","mFromDegree:"+mFromDegree+";mToDegree:"+mToDegree+";mCenterX:"+mCenterX+";mCenterY:"+mCenterY);
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        mCamera = new Camera();
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        Camera camera = mCamera;
        Matrix matrix = t.getMatrix();
        float fromDegree = mFromDegree;
        float toDegree = mToDegree;
        float centerX = mCenterX;
        float centerY = mCenterY;
        float curDegree = fromDegree + ((toDegree - fromDegree) * interpolatedTime);
        if(curDegree > 90 || curDegree < -90){
            if(contentShouldFlip){
                if(this.cardFlipListener != null){
                    this.cardFlipListener.cardFlip();
                }
                contentShouldFlip = false;
            }
            curDegree += 180F;
        }
        camera.save();
        camera.rotateX(curDegree);
        camera.getMatrix(matrix);
        camera.restore();
        matrix.preTranslate(-centerX,-centerY);
        matrix.postTranslate(centerX,centerY);
    }

    private boolean contentShouldFlip = true;
    public void setContentShouldFlip() {
        this.contentShouldFlip = true;
    }
    private OnCardFlipListener cardFlipListener;
    public void setCardFlipListener(OnCardFlipListener cardFlipListener) {
        this.cardFlipListener = cardFlipListener;
    }
    public interface OnCardFlipListener{
        void cardFlip();
    }
}