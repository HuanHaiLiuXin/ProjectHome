package com.jet.project1;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者:幻海流心
 * GitHub:https://github.com/HuanHaiLiuXin
 * 邮箱:wall0920@163.com
 * 2018/1/25 10:58
 *
 * 利用
 * {@link android.support.v4.view.ViewPager#setPageMargin(int)}
 * 和
 * {@link android.support.v4.view.PagerAdapter#getPageWidth(int)}
 * 实现一屏展示多页
 *
 */

public class VPMultiPageActivity extends BaseActivity {
    private ViewPager vp;
    private PagerAdapter adapter;
    private List<View> items1;
    private List<View> items2;

    @Override
    public int getContentView() {
        return R.layout.activity_vp_multipage;
    }

    @Override
    public void initViews() {
        vp = findViewById(R.id.vp);
        items1 = new ArrayList<>();
        items1.add(createMarginView(R.mipmap.img1));
        items1.add(createMarginView(R.mipmap.img2));
        items1.add(createMarginView(R.mipmap.img3));
        items1.add(createMarginView(R.mipmap.img4));
        items1.add(createMarginView(R.mipmap.img5));
        items1.add(createMarginView(R.mipmap.img6));
        items2 = new ArrayList<>();
        items2.add(createImageView(R.mipmap.img1));
        items2.add(createImageView(R.mipmap.img2));
        items2.add(createImageView(R.mipmap.img3));
        items2.add(createImageView(R.mipmap.img4));
        items2.add(createImageView(R.mipmap.img5));
        items2.add(createImageView(R.mipmap.img6));
    }

    public void setPageMargin(View view) {
        adapter = new ViewPagerAdapterEx(items1);
        vp.setAdapter(adapter);
        vp.setPageMargin(-360);
        vp.setOffscreenPageLimit(adapter.getCount());
    }

    public void getPageWidth(View view) {
        vp.setPageMargin(0);
        adapter = new CustomPagerAdapter(items2);
        vp.setAdapter(adapter);
        vp.setOffscreenPageLimit(adapter.getCount());
    }

    class ViewPagerAdapterEx extends ViewPagerAdapter{
        public ViewPagerAdapterEx(List<View> items) {
            super(items);
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            Object item = super.instantiateItem(container, position);
            View v = (View) item;
            if(position == 0){
                v.setPadding(v.getPaddingLeft(),v.getPaddingTop(),v.getPaddingRight(),v.getPaddingBottom());
            }else if(position == getCount() - 1){
                v.setPadding(v.getPaddingLeft(),v.getPaddingTop(),v.getPaddingRight(),v.getPaddingBottom());
            }
            return item;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            /*super.destroyItem(container, position, object);*/
        }
        /*@Override
        public float getPageWidth(int position) {
            return 0.90f;
        }*/
    }

    class CustomPagerAdapter extends ViewPagerAdapter{
        public CustomPagerAdapter(List<View> items) {
            super(items);
        }
        @Override
        public int getItemPosition(Object object) {
            int mChildCount = getCount();
            if(mChildCount > 0){
                mChildCount --;
                return POSITION_NONE;
            }
            return super.getItemPosition(object);
        }

        /**
         * Returns the proportional width of a given page as a percentage of the
         * ViewPager's measured width from (0.f-1.f]
         *
         * @param position The position of the page requested
         * @return Proportional width for the given page position
         */
        @Override
        public float getPageWidth(int position) {
            return 0.60f;
        }
    }
}
