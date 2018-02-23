package com.jet.project3;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * 作者:幻海流心
 * GitHub:https://github.com/HuanHaiLiuXin
 * 邮箱:wall0920@163.com
 * 2018/2/2 16:52
 */

public class Rotate3dAnimation extends Animation {
    private float mFromDegrees;
    private float mToDegrees;
    private float mCenterX;
    private float mCenterY;
    private float mDepthZ;
    private boolean mReverse;
    private Camera mCamera;

    public Rotate3dAnimation(float mFromDegrees, float mToDegrees, float mCenterX, float mCenterY, float mDepthZ, boolean mReverse) {
        this.mFromDegrees = mFromDegrees;
        this.mToDegrees = mToDegrees;
        this.mCenterX = mCenterX;
        this.mCenterY = mCenterY;
        this.mDepthZ = mDepthZ;
        this.mReverse = mReverse;
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        mCamera = new Camera();
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        final float fromDegrees = mFromDegrees;
        float degrees = fromDegrees + (mToDegrees - mFromDegrees) * interpolatedTime;
        float centerX = mCenterX;
        float centerY = mCenterY;
        Camera camera = mCamera;
        Matrix matrix = t.getMatrix();

        camera.save();
        if(mReverse){
            camera.translate(0.0F,0.0F,(1 - mDepthZ) * interpolatedTime);
        }else{
            camera.translate(0.0F,0.0F,mDepthZ * interpolatedTime);
        }
        camera.rotateY(degrees);
        camera.getMatrix(matrix);
        camera.restore();
        matrix.preTranslate(-centerX,-centerY);
        matrix.postTranslate(centerX,centerY);

    }
}
