package com.jet.particle.copy;

import android.graphics.Bitmap;
import android.graphics.Rect;

import java.util.Random;

/**
 * 作者:幻海流心
 * GitHub:https://github.com/HuanHaiLiuXin
 * 邮箱:wall0920@163.com
 * 2018/2/28 14:48
 */

public class Particle {
    public static int PART_WH = 16;

    private Rect bound;
    public float cx;
    public float cy;
    public float radius = PART_WH / 2;
    public int color;
    public float alpha = 1.0F;

    private Random random = new Random();

    public static Particle generateParticle(Rect bound, int column, int row, Bitmap bitmap, int bitmapPartWidth, int bitmapPartHeight){
        int bitmapPixelX = bitmapPartWidth * column + bitmapPartWidth / 2;
        int bitmapPixelY = bitmapPartHeight * row + bitmapPartHeight / 2;
        //getPixel(int x, int y):Returns the {@link Color} at the specified location.
        int color = bitmap.getPixel(bitmapPixelX,bitmapPixelY);
        Particle particle = new Particle();
        particle.bound = bound;
        particle.cx = bound.left + PART_WH * column + PART_WH / 2;
        particle.cy = bound.top + PART_WH * row + PART_WH / 2;
        particle.color = color;
        return particle;
    }

    public void advance(float factor){
        cx += factor * random.nextInt(bound.width()) * (random.nextFloat() - 0.5F);
        cy += factor * random.nextInt(bound.height()) * 0.5F;
        alpha = 1 - factor;
        radius -= factor * radius * random.nextInt(2);
    }
}