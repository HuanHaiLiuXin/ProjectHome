package com.jet.projectone;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.huanhailiuxin.coolviewpager.CoolViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者:幻海流心
 * GitHub:https://github.com/HuanHaiLiuXin
 * 邮箱:wall0920@163.com
 * 2018/1/25 15:04
 */

public class CoolVP4Activity extends BaseActivity {
    private CoolViewPager vp1,vp2;
    private ViewPagerAdapter adapter1,adapter2;
    private List<View> items1,items2;

    @Override
    public int getContentView() {
        return R.layout.activity_cool_view_pager4;
    }

    @Override
    public void initViews() {
        vp1 = findViewById(R.id.vp1);
        vp2 = findViewById(R.id.vp2);
        initData();
    }

    
    private void initData(){
        items1 = new ArrayList<>();
        items2 = new ArrayList<>();
        items1.add(createImageView(R.mipmap.img1));
        items1.add(createImageView(R.mipmap.img2));
        items1.add(createImageView(R.mipmap.img3));
        items1.add(createImageView(R.mipmap.img4));
        items1.add(createImageView(R.mipmap.img5));
        items1.add(createImageView(R.mipmap.img6));
        items2.add(createImageView(R.mipmap.img1));
        items2.add(createImageView(R.mipmap.img2));
        items2.add(createImageView(R.mipmap.img3));
        items2.add(createImageView(R.mipmap.img4));
        items2.add(createImageView(R.mipmap.img5));
        items2.add(createImageView(R.mipmap.img6));
        adapter1 = new ViewPagerAdapter(items1);
        adapter2 = new ViewPagerAdapter(items2);
        vp1.setAdapter(adapter1);
        vp2.setAdapter(adapter2);
    }

    private int currIndex = -1;
    private ViewPager.PageTransformer[] horizontals = new ViewPager.PageTransformer[]{
            new com.huanhailiuxin.coolviewpager.transformer.AccordionTransformer(),
            new com.huanhailiuxin.coolviewpager.transformer.RotateTransformer(),
            new com.huanhailiuxin.coolviewpager.transformer.DepthPageTransformer(),
            new com.huanhailiuxin.coolviewpager.transformer.RotateDownTransformer(),
            new com.huanhailiuxin.coolviewpager.transformer.ZoomInTransformer()
    };
    private ViewPager.PageTransformer[] verticals = new ViewPager.PageTransformer[]{
            new com.huanhailiuxin.coolviewpager.transformer.VerticalAccordionTransformer(),
            new com.huanhailiuxin.coolviewpager.transformer.VerticalRotateTransformer(),
            new com.huanhailiuxin.coolviewpager.transformer.VerticalDepthPageTransformer(),
            new com.huanhailiuxin.coolviewpager.transformer.VerticalRotateDownTransformer(),
            new com.huanhailiuxin.coolviewpager.transformer.VerticalZoomInTransformer()
    };
    public void changePageTransformer(View view) {
        initData();
        currIndex = (++currIndex)%horizontals.length;
        vp1.setPageTransformer(false,horizontals[currIndex]);
        vp2.setPageTransformer(false,verticals[currIndex]);
    }
}
