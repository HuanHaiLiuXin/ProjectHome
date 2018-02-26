package com.jet.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.jet.project1.BaseActivity;
import com.jet.project1.R;

/**
 * 作者:幻海流心
 * GitHub:https://github.com/HuanHaiLiuXin
 * 邮箱:wall0920@163.com
 * 2018/2/26 15:01
 */

public class BitmapSetPixelsActivity extends BaseActivity {
    private AppCompatImageView iv_show,iv;

    @Override
    public int getContentView() {
        return R.layout.activity_bitmap_setpixels;
    }

    private Bitmap bitmap;
    @Override
    public void initViews() {
        iv_show = findViewById(R.id.iv_show);
        iv = findViewById(R.id.iv);
        bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.img1);
        iv_show.setImageBitmap(bitmap);

    }

    /**
     * http://blog.csdn.net/qq_32353771/article/details/53913479
     * @param view
     */
    public void setPixels1(View view) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Bitmap result = Bitmap.createBitmap(w, h, Bitmap.Config.RGB_565);
        //垂直方向上的百叶窗
        boolean _direct = false;//horizontal: true,  vertical: false
        int _width = w / 10; //10个
        int _opacity = 100;
        int _color = 0x000000;

        int r, g, b, a, color;
        int[] oldPx = new int[w * h];
        int[] newPx = new int[w * h];

        bitmap.getPixels(oldPx, 0, w, 0, 0, w, h);

        for(int x = 0 ; x < (w - 1) ; x++){
            for(int y = 0 ; y < (h - 1) ; y++){
                color = oldPx[x * h + y];
                r = Color.red(color);
                g = Color.green(color);
                b = Color.blue(color);

                int  nMod = 0 ;
                if (_direct) // 水平方向
                    nMod = y % _width ;
                else if (_direct == false) // 垂直方向
                    nMod = x % _width ;

                double fDelta = 255.0 * (_opacity/100.0) / (_width-1.0);
                a = Function.FClamp0255(nMod * fDelta) ;
                int colorR = _color & 0xFF0000 >> 16;
                int colorG = _color & 0x00FF00 >> 8;
                int colorB = _color & 0x0000FF;
                if (_color == 0xFF)
                {
                    newPx[x * h + y] = Color.rgb(colorR, colorG, colorB);
                    continue ;
                }
                if (a == 0)
                    continue ;

                int t = 0xFF - a ;
                newPx[x * h + y] = Color.rgb((colorR * a + r * t) / 0xFF,
                        (colorG * a + g * t) / 0xFF, (colorB * a + b * t) / 0xFF);
            }
        }
        result.setPixels(newPx, 0, w, 0, 0, w, h);
        iv.setImageBitmap(result);
    }

    public void setPixels2(View view) {

    }

    /**
     * http://blog.csdn.net/qq_32353771/article/details/53913108
     */
    public static class Function {
        public static int FClamp(final int t, final int tLow, final int tHigh)
        {
            if (t < tHigh)
            {
                return ((t > tLow) ? t : tLow) ;
            }
            return tHigh ;
        }
        public static double FClampDouble(final double t, final double tLow, final double tHigh)
        {
            if (t < tHigh)
            {
                return ((t > tLow) ? t : tLow) ;
            }
            return tHigh ;
        }
        public static int FClamp0255(final double d)
        {
            return (int)(FClampDouble(d, 0.0, 255.0) + 0.5) ;
        }
    }
}
