package com.jet.customanimation;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import com.jet.project1.BaseActivity;
import com.jet.project1.R;

/**
 * 作者:幻海流心
 * GitHub:https://github.com/HuanHaiLiuXin
 * 邮箱:wall0920@163.com
 * 2018/2/24 14:04
 *
 * 自定义Animation需要重写2个方法:
 *      initialize:在其中初始化一些参数信息,并设置动画示例的属性
 *      applyTransformation:通过Transformation获得Matrix示例,通过Matrix,或者Matrix和Camera结合设置具体变换规则
 *
 关于Math.sin:注意参数值是"弧度",度=弧度*(180/π)
 public static double sin(double a)返回角的三角正弦。
    参数：a - 以弧度表示的角。

 Log信息:
 E/Jet: Math.sin(Math.PI * 0.25F):0.7071067811865475
 E/Jet: Math.sin(Math.PI * 0.50F):1.0
 E/Jet: Math.sin(Math.PI * 0.75F):0.7071067811865476
 E/Jet: Math.sin(Math.PI * 1.00F):1.2246467991473532E-16
 E/Jet: Math.sin(Math.PI * 1.25F):-0.7071067811865475
 E/Jet: Math.sin(Math.PI * 1.50F):-1.0
 E/Jet: Math.sin(Math.PI * 1.75F):-0.7071067811865477
 E/Jet: Math.sin(Math.PI * 2.00F):-2.4492935982947064E-16
 E/Jet: Math.sin(Math.PI * 0.25F):0.7071067811865475
 E/Jet: Math.sin(Math.PI * 0.50F):1.0
 E/Jet: Math.sin(Math.PI * 0.75F):0.7071067811865476
 E/Jet: Math.sin(Math.PI * 1.00F):1.2246467991473532E-16
 E/Jet: Math.sin(Math.PI * 1.25F):-0.7071067811865475
 E/Jet: Math.sin(Math.PI * 1.50F):-1.0
 E/Jet: Math.sin(Math.PI * 1.75F):-0.7071067811865477
 E/Jet: Math.sin(Math.PI * 2.00F):-2.4492935982947064E-16
 */

public class MyCustomAnimationActivity extends BaseActivity {
    private AppCompatButton bt1,bt2,bt3;

    @Override
    public int getContentView() {
        return R.layout.activity_mycustomanimation;
    }

    @Override
    public void initViews() {
        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        bt3 = findViewById(R.id.bt3);
    }

    public void click1(View view) {
        bt1.startAnimation(new Animation1());
    }

    public void click2(View view) {
        bt2.startAnimation(new Animation2());
    }

    public void click3(View view) {
        Log.e("Jet","Math.sin(Math.PI * 0.25F):"+Math.sin(Math.PI * 0.25F));
        Log.e("Jet","Math.sin(Math.PI * 0.50F):"+Math.sin(Math.PI * 0.50F));
        Log.e("Jet","Math.sin(Math.PI * 0.75F):"+Math.sin(Math.PI * 0.75F));
        Log.e("Jet","Math.sin(Math.PI * 1.00F):"+Math.sin(Math.PI * 1.00F));
        Log.e("Jet","Math.sin(Math.PI * 1.25F):"+Math.sin(Math.PI * 1.25F));
        Log.e("Jet","Math.sin(Math.PI * 1.50F):"+Math.sin(Math.PI * 1.50F));
        Log.e("Jet","Math.sin(Math.PI * 1.75F):"+Math.sin(Math.PI * 1.75F));
        Log.e("Jet","Math.sin(Math.PI * 2.00F):"+Math.sin(Math.PI * 2.00F));
        bt3.startAnimation(new Animation3());
    }

    class Animation1 extends Animation{
        private float mCenterX;
        private float mCenterY;
        @Override
        public void initialize(int width, int height, int parentWidth, int parentHeight) {
            super.initialize(width, height, parentWidth, parentHeight);
            mCenterX = width/2;
            mCenterY = height/2;
            Animation1.this.setDuration(3000);
            Animation1.this.setFillAfter(false);
        }
        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            Matrix matrix = t.getMatrix();
            matrix.setScale(0.2F + Math.abs(interpolatedTime - 0.5F) * 2 * 0.8F,0.2F + Math.abs(interpolatedTime - 0.5F) * 2 * 0.8F);
            matrix.preTranslate(-mCenterX,-mCenterY);
            matrix.postTranslate(mCenterX,mCenterY);
        }
    }

    class Animation2 extends Animation{
        private float mCenterX;
        private float mCenterY;
        private Camera mCamera;
        @Override
        public void initialize(int width, int height, int parentWidth, int parentHeight) {
            super.initialize(width, height, parentWidth, parentHeight);
            mCenterX = width/2;
            mCenterY = height/2;
            mCamera = new Camera();
            setDuration(3000);
            setFillAfter(false);
        }
        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            Camera camera = mCamera;
            Matrix matrix = t.getMatrix();
            camera.save();
            camera.translate(200F*interpolatedTime,-300*interpolatedTime,100*interpolatedTime);
            camera.rotateX(360F*interpolatedTime);
            camera.rotateY(360F*interpolatedTime);
            camera.getMatrix(matrix);
            camera.restore();
            matrix.preTranslate(-mCenterX,-mCenterY);
            matrix.postTranslate(mCenterX,mCenterY);
        }
    }

    class Animation3 extends Animation{
        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            t.getMatrix().setTranslate((float) Math.sin(Math.PI * 10 * interpolatedTime)*40,0F);
        }

        @Override
        public void initialize(int width, int height, int parentWidth, int parentHeight) {
            super.initialize(width, height, parentWidth, parentHeight);
            setDuration(2000);
            setFillAfter(false);
        }
    }
}
