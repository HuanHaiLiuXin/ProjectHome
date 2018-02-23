package com.jet.project1;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * http://blog.csdn.net/u011494050/article/details/41183061
 * http://www.jcodecraeer.com/a/anzhuokaifa/2015/0928/3525.html
 */
public class VpClipChildrenActivity extends AppCompatActivity {
    private LinearLayout ll;
    private ViewPager vp;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_vp_clip_children);
        getSupportActionBar().hide();
        initViews();
    }
    public View createImageView(int imgResId){
        ImageView imageView = new ImageView(this);
        imageView.setLayoutParams(new ViewPager.LayoutParams());
        imageView.setImageResource(imgResId);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }
    private void initViews() {
        ll = findViewById(R.id.ll);
        vp = findViewById(R.id.vp);
        List items = new ArrayList<>();
        items.add(createImageView(R.mipmap.img1));
        items.add(createImageView(R.mipmap.img2));
        items.add(createImageView(R.mipmap.img3));
        items.add(createImageView(R.mipmap.img4));
        items.add(createImageView(R.mipmap.img5));
        items.add(createImageView(R.mipmap.img6));
        adapter = new ViewPagerAdapter(items);
        vp.setAdapter(adapter);
        vp.setPageTransformer(false,new ScaleTransformer());
        vp.setOffscreenPageLimit(adapter.getCount());
        ll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return vp.dispatchTouchEvent(event);
            }
        });
    }
}
