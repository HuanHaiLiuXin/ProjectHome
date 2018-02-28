package com.jet.particle.copy;

import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

/**
 * 作者:幻海流心
 * GitHub:https://github.com/HuanHaiLiuXin
 * 邮箱:wall0920@163.com
 * 2018/2/28 15:08
 */

public class ExplosionAnimator extends ValueAnimator {
    //粒子Particle的二维数组:行*列
    private Particle[][] particles;
    //遍历绘制Particle的画笔
    private Paint paint;
    //当前ValueAnimator对应的ExplosionField实例
    private View containerView;

    private long DEFAULT_DURATION = 1500;

    public ExplosionAnimator(View containerView, Rect bound, Bitmap bitmap) {
        this.paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.containerView = containerView;
        this.particles = generateParticles(bound,bitmap);
        setFloatValues(0.0F,1.0F);
        setDuration(DEFAULT_DURATION);
    }

    private Particle[][] generateParticles(Rect bound,Bitmap bitmap){
        Particle[][] result = null;
        int columns = bound.width() / Particle.PART_WH;
        int rows = bound.height() / Particle.PART_WH;
        int bitmapPartWidth = bitmap.getWidth() / columns;
        int bitmapPartHeight = bitmap.getHeight() / rows;
        result = new Particle[rows][columns];
        for (int i=0;i<rows;i++){
            for (int j=0;j<columns;j++){
                result[i][j] = Particle.generateParticle(bound,j,i,bitmap,bitmapPartWidth,bitmapPartHeight);
            }
        }
        return result;
    }

    public void draw(Canvas canvas){
        if(!isStarted()){
            return;
        }
        for(Particle[] oneRow:particles){
            for (Particle particle:oneRow){
                float factor = (float) getAnimatedValue();
                particle.advance(factor);
                paint.setColor(particle.color);
//                paint.setAlpha((int) (255 * particle.alpha)); //只是这样设置，透明色会显示为黑色
                paint.setAlpha((int) (Color.alpha(particle.color) * particle.alpha));
                canvas.drawCircle(particle.cx,particle.cy,particle.radius,paint);
            }
        }
        containerView.invalidate();
    }

    @Override
    public void start() {
        super.start();
        containerView.invalidate();
    }
}