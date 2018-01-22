package com.jet.projectone;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.huanhailiuxin.coolviewpager.CoolViewPager;

public class CollViewPagerActivity extends AppCompatActivity {
    private CoolViewPager vp;
    private ViewPager vp1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN|WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_coll_view_pager);
        getSupportActionBar().hide();
        initView();
    }

    ViewPagerAdapter adapter = null;
    private void initView() {
        vp = findViewById(R.id.vp);
        vp1 = findViewById(R.id.vp1);
        adapter = new ViewPagerAdapter(CollViewPagerActivity.this,new int[]{R.mipmap.img1,R.mipmap.img2,R.mipmap.img3,R.mipmap.img4,R.mipmap.img5,R.mipmap.img6});
        vp1.setAdapter(adapter);
        setAdapter();
    }

    private void setAdapter(){
        vp.setAdapter(adapter);
    }

    public void setScrollHorizontal(View view) {
        vp.setScrollMode(CoolViewPager.ScrollMode.HORIZONTAL);
        setAdapter();
    }

    public void setScrollVertical(View view) {
        vp.setScrollMode(CoolViewPager.ScrollMode.VERTICAL);
        setAdapter();
    }
}
