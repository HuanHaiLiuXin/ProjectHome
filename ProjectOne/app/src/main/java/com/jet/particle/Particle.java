package com.jet.particle;

import android.graphics.Point;
import android.graphics.Rect;

import java.util.Random;

/**
 * 作者:幻海流心
 * GitHub:https://github.com/HuanHaiLiuXin
 * 邮箱:wall0920@163.com
 * 2018/2/27 10:03
 */

public class Particle {
    float cx;
    float cy;
    float radius;
    int color;
    float alpha;
    Rect mBound;
    //默认小球宽高
    public static final int PART_WH = 8;
    Random random = new Random();

    public static Particle generateParticle(int color, Rect bound, Point point){
        //行是y,列是x
        int row = point.y;
        int column = point.x;
        Particle particle = new Particle();
        particle.mBound = bound;
        particle.alpha = 1.0F;
        particle.color = color;
        particle.radius = PART_WH;
        particle.cx = bound.left + PART_WH * column + PART_WH / 2;
        particle.cy = bound.top + PART_WH * row + PART_WH / 2;
        return particle;
    }

    public void advance(float factor){
        cx = cx + factor * random.nextInt(mBound.width()) * (random.nextFloat() - 0.5F);
        cy = cy + factor * random.nextInt(mBound.height()) * 0.5F;
        radius = radius - random.nextInt(2) * factor;
        alpha = 1 - factor;
    }
}