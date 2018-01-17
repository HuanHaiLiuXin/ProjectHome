package com.jet.projectone;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

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

    private void initView() {
        vp = findViewById(R.id.vp);
        ViewPagerAdapter adapter = new ViewPagerAdapter(VerticalViewPagerOriActivity.this,new int[]{R.mipmap.img1,R.mipmap.img2,R.mipmap.img3,R.mipmap.img4,R.mipmap.img5,R.mipmap.img6});
        vp.setAdapter(adapter);
    }
}
