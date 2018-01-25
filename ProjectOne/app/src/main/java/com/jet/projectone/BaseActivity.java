package com.jet.projectone;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Outline;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.huanhailiuxin.coolviewpager.CoolViewPager;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN|WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(getContentView());
        getSupportActionBar().hide();
        initViews();
    }
    abstract int getContentView();
    abstract void initViews();
    public void jump(Class clazz){
        startActivity(new Intent(BaseActivity.this,clazz));
    }
    public View createImageView(int imgResId){
        ImageView imageView = new ImageView(this);
        imageView.setLayoutParams(new CoolViewPager.LayoutParams());
        imageView.setImageResource(imgResId);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }
    public View createMarginView(int imgResId){
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundColor(Color.TRANSPARENT);
        imageView.setLayoutParams(new ViewPager.LayoutParams());
        imageView.setImageResource(imgResId);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setPadding(200,16,200,16);
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            imageView.setOutlineProvider(viewOutlineProvider);
            imageView.setClipToOutline(true);
        }*/
        return imageView;
    }
    ViewOutlineProvider viewOutlineProvider = new ViewOutlineProvider() {
        @Override
        public void getOutline(View view, Outline outline) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                outline.setRoundRect(0,0,view.getWidth(),view.getHeight(),32);
            }
        }
    };
}
