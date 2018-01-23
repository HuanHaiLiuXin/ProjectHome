package com.huanhailiuxin.coolviewpager.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * 作者:幻海流心
 * GitHub:https://github.com/HuanHaiLiuXin
 * 邮箱:wall0920@163.com
 * 2018/1/4 14:28
 */

public class LoopPagerAdapterWrapper extends PagerAdapter {
    private PagerAdapter mAdapter;
    public LoopPagerAdapterWrapper(PagerAdapter adapter){
        this.mAdapter = adapter;
    }

    @Override
    public int getCount() {
        return mAdapter.getCount() + 2;
    }
    public int getRealCount(){
        return mAdapter.getCount();
    }
    public int toRealPosition(int position){
        int realCount = getRealCount();
        if(realCount == 0){
           return 0;
        }else{
            int realPosition = (position - 1)%realCount;
            if(realPosition < 0){
                realPosition += realCount;
            }
            return realPosition;
        }
    }
    public int toInnerPosition(int realPosition){
        int position = realPosition + 1;
        return position;
    }
    public int getRealFirstPosition(){
        return 1;
    }
    public int getRealLastPosition(){
        return getRealFirstPosition() + getRealCount() - 1;
    }
    public PagerAdapter getRealAdapter(){
        return mAdapter;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return mAdapter.isViewFromObject(view,object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        int realPosition = toRealPosition(position);
        return mAdapter.instantiateItem(container, realPosition);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        /*int realPosition = toRealPosition(position);
        mAdapter.destroyItem(container,realPosition,object);*/
    }

    /*
     * Delegate rest of methods directly to the inner adapter.
     */
    /*
    @Override
    public void finishUpdate(ViewGroup container) {
        mAdapter.finishUpdate(container);
    }
    @Override
    public void restoreState(Parcelable bundle, ClassLoader classLoader) {
        mAdapter.restoreState(bundle, classLoader);
    }
    @Override
    public Parcelable saveState() {
        return mAdapter.saveState();
    }
    @Override
    public void startUpdate(ViewGroup container) {
        mAdapter.startUpdate(container);
    }
    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        mAdapter.setPrimaryItem(container, position, object);
    }
    */
    /*
     * End delegation
     */
}
