package com.jet.project1;

import android.view.View;

import com.huanhailiuxin.coolviewpager.CoolViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者:幻海流心
 * GitHub:https://github.com/HuanHaiLiuXin
 * 邮箱:wall0920@163.com
 * 2018/1/24 16:10
 */

public class CoolVP3Activity extends BaseActivity {
    @Override
    public int getContentView() {
        return R.layout.activity_cool_view_pager3;
    }

    private CoolViewPager vp11,vp12,vp2;
    private ViewPagerAdapter adapter11;
    private ViewPagerAdapter adapter12;
    private ViewPagerAdapter adapter2;
    private List<View> items11 = new ArrayList<>();
    private List<View> items12 = new ArrayList<>();
    private List<View> items2 = new ArrayList<>();
    @Override
    public void initViews() {
        vp11 = findViewById(R.id.vp11);
        vp12 = findViewById(R.id.vp12);
        vp2 = findViewById(R.id.vp2);
        items11.add(createImageView(R.mipmap.img1));
        items11.add(createImageView(R.mipmap.img2));
        items11.add(createImageView(R.mipmap.img3));
        items12.add(createImageView(R.mipmap.img4));
        items12.add(createImageView(R.mipmap.img5));
        items12.add(createImageView(R.mipmap.img6));
        items2.add(createImageView(R.mipmap.img2));
        items2.add(createImageView(R.mipmap.img4));
        items2.add(createImageView(R.mipmap.img6));
        adapter11 = new ViewPagerAdapter(items11);
        adapter12 = new ViewPagerAdapter(items12);
        adapter2 = new ViewPagerAdapter(items2);
        vp11.setAdapter(adapter11);
        vp12.setAdapter(adapter12);
        vp2.setAdapter(adapter2);
    }

    public void toggleAutoScroll(View view) {
        vp11.toggleAutoScroll();
        vp12.toggleAutoScroll();
    }

    public void toggleAutoScrollDirection(View view) {
        vp2.toggleAutoScrollDirection();
    }
}
