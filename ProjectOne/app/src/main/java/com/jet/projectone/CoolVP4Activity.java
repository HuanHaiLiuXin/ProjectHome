package com.jet.projectone;

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
    int getContentView() {
        return R.layout.activity_cool_view_pager4;
    }

    @Override
    void initViews() {
        vp1 = findViewById(R.id.vp1);
        vp2 = findViewById(R.id.vp2);
        items1 = new ArrayList<>();
        items2 = new ArrayList<>();
        items1.add(createImageView(R.mipmap.img1));
        items1.add(createImageView(R.mipmap.img2));
        items1.add(createImageView(R.mipmap.img3));
        items2.add(createImageView(R.mipmap.img4));
        items2.add(createImageView(R.mipmap.img5));
        items2.add(createImageView(R.mipmap.img6));
        adapter1 = new ViewPagerAdapter(items1);
        adapter2 = new ViewPagerAdapter(items2);
        vp1.setAdapter(adapter1);
        vp2.setAdapter(adapter2);
    }

    private int currIndex = -1;
    private CoolViewPager.PageTransformer[] horizontals = new CoolViewPager.PageTransformer[]{

    };
    private CoolViewPager.PageTransformer[] verticals = new CoolViewPager.PageTransformer[]{

    };
    public void changePageTransformer(View view) {
        currIndex = (++currIndex)%horizontals.length;
        vp1.setPageTransformer(false,horizontals[currIndex]);
        vp2.setPageTransformer(false,verticals[currIndex]);
    }
}