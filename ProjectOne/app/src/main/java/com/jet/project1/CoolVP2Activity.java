package com.jet.project1;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.huanhailiuxin.coolviewpager.CoolViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者:幻海流心
 * GitHub:https://github.com/HuanHaiLiuXin
 * 邮箱:wall0920@163.com
 * 2018/1/24 10:01
 */

public class CoolVP2Activity extends BaseActivity {
    private CoolViewPager vp1;
    private ViewPager vp2;
    private List<View> items1;
    private List<View> items2;
    private ViewPagerAdapter adapter1;
    private ViewPagerAdapter adapter2;

    @Override
    public int getContentView() {
        return R.layout.activity_cool_view_pager2;
    }

    @Override
    public void initViews() {
        vp1 = findViewById(R.id.vp1);
        vp2 = findViewById(R.id.vp2);
        items1 = new ArrayList<>();
        items2 = new ArrayList<>();
        items1.add(createImageView(R.mipmap.img1));
        items1.add(createImageView(R.mipmap.img2));
        items1.add(createImageView(R.mipmap.img3));
        items2.add(createImageView(R.mipmap.img1));
        items2.add(createImageView(R.mipmap.img2));
        items2.add(createImageView(R.mipmap.img3));
        adapter1 = new ViewPagerAdapter(items1);
        adapter2 = new ViewPagerAdapter(items2);
        vp1.setAdapter(adapter1);
        vp2.setAdapter(adapter2);
    }

    public void notifyDataSetChanged(View view) {
        items1.set(0,createImageView(R.mipmap.img4));
        items1.set(1,createImageView(R.mipmap.img5));
        items1.set(2,createImageView(R.mipmap.img6));
//        vp1.getAdapter().notifyDataSetChanged();
        vp1.notifyDataSetChanged();
    }

    public void notifyDataSetChangedOri(View view) {
        items2.set(0,createImageView(R.mipmap.img4));
        items2.set(1,createImageView(R.mipmap.img5));
        items2.set(2,createImageView(R.mipmap.img6));
        adapter2.notifyDataSetChanged();
    }
}
