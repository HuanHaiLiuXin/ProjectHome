package com.jet.projectone;

import android.app.ActionBar;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 幻海流心 on 2018/1/2.
 */

public class ViewPagerAdapter extends PagerAdapter{
    private List<View> views;
    public ViewPagerAdapter(List<View> items){
        this.views = items;
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        //必须要先判断,否则报错:java.lang.IllegalStateException: The specified child already has a parent
        //ViewGroup的addView（）方法不能添加一个已存在父控件的视图,所以在执行前需要判断要添加的View实例是不是存在父控件.
        //如果存在父控件,需要其父控件先将该View移除掉,再执行addView
        if(views.get(position).getParent() != null){
            ((ViewGroup)views.get(position).getParent()).removeView(views.get(position));
        }
        container.addView(views.get(position));
        return views.get(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    /**
     * 重写{@link PagerAdapter#getPageWidth(int)}可以实现一屏展示多页
     *
     * 通过重写PagerAdapter的getPageWidth方法，此方法返回的是ViewPager中每一页占实际ViewPager的宽度百分比，默认是1，即100%：
     * @param position
     * @return
     */
    @Override
    public float getPageWidth(int position) {
        return super.getPageWidth(position);
    }

    public <T extends View> T getPage(int position){
        return (T) views.get(position);
    }
}
