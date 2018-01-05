package com.jet.projectone;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;

/**
 * 作者:幻海流心
 * GitHub:https://github.com/HuanHaiLiuXin
 * 邮箱:wall0920@163.com
 * 2018/1/4 15:06
 */

public class LoopViewPager extends ViewPager {
    private LoopPagerAdapterWrapper mAdapter;

    public LoopViewPager(@NonNull Context context) {
        super(context);
        init();
    }
    public LoopViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    public void setAdapter(@Nullable PagerAdapter adapter) {
        mAdapter = new LoopPagerAdapterWrapper(adapter);
        super.setAdapter(mAdapter);
        setCurrentItem(0,false);
    }

    @Override
    public int getCurrentItem() {
        return mAdapter != null ? mAdapter.toRealPosition(super.getCurrentItem()) : 0;
    }
    public void setCurrentItem(int item, boolean smoothScroll){
        int realItem = mAdapter.toInnerPosition(item);
        super.setCurrentItem(realItem,smoothScroll);
    }
    @Override
    public void setCurrentItem(int item) {
        if(getCurrentItem() != item){
            setCurrentItem(item,true);
        }
    }

    @Nullable
    @Override
    public PagerAdapter getAdapter() {
        return mAdapter != null ? mAdapter.getRealAdapter() : mAdapter;
    }

    private void init() {
        super.setOnPageChangeListener(onPageChangeListener);
    }
    private OnPageChangeListener onPageChangeListener = new OnPageChangeListener() {
        //mPreviousOffset必须保留,mPreviousOffset==0是防止首尾页切换时候界面闪动的关键
        private float mPreviousOffset = -1;
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            int realPosition = position;
            if(mAdapter != null){
                if(positionOffset == 0 && mPreviousOffset == 0 && (position == 0 || position == mAdapter.getCount() - 1)){
                    Log.e("Jet","positionOffset:"+positionOffset+";mPreviousOffset:"+mPreviousOffset);
                    realPosition = mAdapter.toRealPosition(position);
                    setCurrentItem(realPosition,false);
                }
            }
            mPreviousOffset = positionOffset;
        }
        @Override
        public void onPageScrollStateChanged(int state) {
            if(mAdapter != null){
                int position = LoopViewPager.super.getCurrentItem();
                if(state == ViewPager.SCROLL_STATE_IDLE && (position == 0 || position == mAdapter.getCount() - 1)){
                    int realPosition = mAdapter.toRealPosition(position);
                    setCurrentItem(realPosition,false);
                }
            }
        }
        @Override
        public void onPageSelected(int position) {
        }
    };

}
