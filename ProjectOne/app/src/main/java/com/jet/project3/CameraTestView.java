package com.jet.project3;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.jet.project1.R;

/**
 * 作者:幻海流心
 * GitHub:https://github.com/HuanHaiLiuXin
 * 邮箱:wall0920@163.com
 * 2018/2/2 14:50
 */

public class CameraTestView extends View {
    private Camera camera;
    private Matrix matrix;
    private Paint paint;
    private Context context;

    public CameraTestView(Context context) {
        this(context,null,0);
    }

    public CameraTestView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CameraTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        setBackgroundColor(Color.GRAY);
        camera = new Camera();
        matrix = new Matrix();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(context.getResources().getColor(R.color.colorAccent));
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        /*paint.setColor(context.getResources().getColor(R.color.colorAccent));
        canvas.drawRect(0F,0F,240F,240F,paint);

        matrix.reset();
        camera.save();
        camera.translate(240F,-240F,-0F);
        camera.getMatrix(matrix);
        camera.restore();
        canvas.concat(matrix);

        paint.setColor(Color.BLUE);
        canvas.drawRect(0F,0F,240F,240F,paint);*/

        matrix.reset();
        camera.save();
//        camera.rotateX(60F);//1:
//        camera.rotateY(60F);//2:
        camera.rotateZ(60F);
        camera.getMatrix(matrix);
        camera.restore();
        matrix.preTranslate(-getWidth()/2,-getHeight()/2);
        matrix.postTranslate(getWidth()/2,getHeight()/2);

        BitmapFactory.Options option = new BitmapFactory.Options();
        option.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.mipmap.img7,option);
//        option.inSampleSize = calculateInSampleSize(option, getWidth()/2, getHeight()/2);
        option.inSampleSize = 4;
        option.inJustDecodeBounds = false;
        canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.img7,option), matrix, paint);
    }

    private int calculateInSampleSize(BitmapFactory.Options options,
                                      int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }
}
