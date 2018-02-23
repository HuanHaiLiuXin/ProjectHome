package com.jet.project1;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * 垂直方向滑动的ViewPager
 */
public class VerticalViewPagerOriActivity extends AppCompatActivity {
    private VerticalViewPagerOri vp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN|WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_vertical_view_pager);
        getSupportActionBar().hide();
        initView();
    }

    public View createImageView(int imgResId){
        ImageView imageView = new ImageView(this);
        imageView.setLayoutParams(new ViewPager.LayoutParams());
        imageView.setImageResource(imgResId);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }
    private void initView() {
        vp = findViewById(R.id.vp);
        List<View> items = new ArrayList<>();
        items.add(createImageView(R.mipmap.img1));
        items.add(createImageView(R.mipmap.img2));
        items.add(createImageView(R.mipmap.img3));
        items.add(createImageView(R.mipmap.img4));
        items.add(createImageView(R.mipmap.img5));
        items.add(createImageView(R.mipmap.img6));
        ViewPagerAdapter adapter = new ViewPagerAdapter(items);
        vp.setAdapter(adapter);
    }
}
