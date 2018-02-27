package com.jet.particle;

import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;

/**
 * 作者:幻海流心
 * GitHub:https://github.com/HuanHaiLiuXin
 * 邮箱:wall0920@163.com
 * 2018/2/27 13:58
 */

public class ExplosionAnimator extends ValueAnimator{
    public static final int DEFAULT_DURATION = 1500;
    private Particle[][] mParticles;
    private Paint mPaint;
    private View mContainer;

    public ExplosionAnimator(View view,Bitmap bitmap,Rect bound){
        setFloatValues(0.0F,1.0F);
        setDuration(DEFAULT_DURATION);
        mPaint = new Paint();
        mContainer = view;
        mParticles = generateParticles(bitmap,bound);
    }

    private Particle[][] generateParticles(Bitmap bitmap, Rect bound){
        int w = bound.width();
        int h = bound.height();
        //横线和纵向的粒子个数
        int partW_Count = w / Particle.PART_WH;
        int partH_Count = h / Particle.PART_WH;
        //单个粒子所对应的Bitmap中的宽度和高度
        int bitmap_part_w = bitmap.getWidth() / partW_Count;
        int bitmap_part_h = bitmap.getHeight() / partH_Count;
        //[行][列]
        Particle[][] particles = new Particle[partH_Count][partW_Count];
        Point point = null;
        //单行
        for (int row = 0;row<partH_Count;row++){
            //单列
            for(int column = 0;column<partW_Count;column++){
                //取得当前粒子所在位置的颜色
                int color = bitmap.getPixel(bitmap_part_w * column,bitmap_part_h * row);
                point = new Point(column,row);//列/x索引值,行/y索引值
                particles[row][column] = Particle.generateParticle(color,bound,point);
            }
        }
        return particles;
    }

    public void draw(Canvas canvas){
        if(!isStarted()){
            return;
        }
        for (Particle[] particles : mParticles){
            for (Particle particle : particles){
                particle.advance((Float) getAnimatedValue());
                mPaint.setColor(particle.color);
                //                mPaint.setAlpha((int) (255 * p.alpha)); //只是这样设置，透明色会显示为黑色
                mPaint.setAlpha((int) (Color.alpha(particle.color) * particle.alpha));
                canvas.drawCircle(particle.cx,particle.cy,particle.radius,mPaint);
            }
        }
        mContainer.invalidate();
    }

    @Override
    public void start() {
        super.start();
        mContainer.invalidate();
    }
}
