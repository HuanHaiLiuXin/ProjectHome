package com.jet.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.jet.project1.R;

/**
 * 作者:幻海流心
 * GitHub:https://github.com/HuanHaiLiuXin
 * 邮箱:wall0920@163.com
 * 2018/2/27 11:17
 */

public class CanvasSetBitmapView extends View {
    private Paint paint;

    public CanvasSetBitmapView(Context context) {
        this(context,null,0);
    }
    public CanvasSetBitmapView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CanvasSetBitmapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(40);
        paint.setColor(Color.BLUE);
    }

    public Bitmap ori;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        ori = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.img_1));
        /*if(this.onSetBitmapListener != null){
            this.onSetBitmapListener.setBitmap(ori);
        }*/
        canvas.setBitmap(ori);
        canvas.drawText("荒烟蔓草的年头",20F,20F,paint);
        canvas.drawCircle(100,100,40,paint);
    }

    private OnSetBitmapListener onSetBitmapListener;
    public void setOnSetBitmapListener(OnSetBitmapListener onSetBitmapListener) {
        this.onSetBitmapListener = onSetBitmapListener;
    }
    interface OnSetBitmapListener{
       void setBitmap(Bitmap bitmap);
    }

}
