package com.jet.projectone;

import android.app.ActionBar;
import android.content.Context;
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
    private Context context;
    private int[] imgs;

    public ViewPagerAdapter(Context mContext,int[] mImgs){
        this.context = mContext;
        this.imgs = mImgs;
    }

    @Override
    public int getCount() {
        if(imgs == null || imgs.length <= 0){
            return 0;
        }else{
            return imgs.length;
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        views.put(position,null);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View imageView = createImageView(position);
        container.addView(imageView);
        views.put(position,imageView);
        return imageView;
    }

    private View createImageView(int position){
        ImageView imageView = new ImageView(context);
        ViewPager.LayoutParams params = new ViewPager.LayoutParams();
        imageView.setLayoutParams(params);
        imageView.setImageResource(imgs[position]);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    private HashMap<Integer,View> views = new HashMap<>();
    public <T extends View> T getPage(int position){
        return (T) views.get(position);
    }
}
