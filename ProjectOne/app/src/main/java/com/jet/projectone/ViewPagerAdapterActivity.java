package com.jet.projectone;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

/**
 * 1:自定义Adapter,主要解决ViewPager关联的Adapter实例调用notifyDataSetChanged(),ViewPager界面不刷新的问题
 * 见:http://www.07net01.com/program/642011.html
 *
 * 2:为已存在的Page页添加组件引用
 重设适配器固然简单，但在某些情况下代价过大。如果Page页过于复杂，重设适配器会有很明显的闪屏或卡顿。我们可以在PageAdaper的实现类里将设置的布局组件提取出来，更新的时候直接更新布局组件，而不再更换整个Page页（前提条件：在获取数据之前，需要提前知道Page页的个数，设置好空数据的Page页）
 限制:要更新数据的Page页当前必须存在,如果已经执行了destroyItem的情况下,该Page是null,是无法进行更新的,
 可以使用setOffscreenPageLimit设置较大的数量,让ViewPager在滑动过程中不销毁Page
 */
public class ViewPagerAdapterActivity extends AppCompatActivity {
    private ViewPager vp;
    private PagerAdapter adapter;
    private int[] imgs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_view_pager_adapter);
        getSupportActionBar().hide();
        initViews();
        initData();
    }

    private void initData() {
        imgs = new int[]{R.mipmap.img1,R.mipmap.img2,R.mipmap.img3};
        adapter = new ViewPagerAdapter(ViewPagerAdapterActivity.this,imgs);
        vp.setAdapter(adapter);
    }

    private void initViews() {
        vp = findViewById(R.id.vp);
//        vp.setOffscreenPageLimit(3);
    }
    private int oriIndex = 0;
    public void oriModifyItemNotify(View view) {
        if(adapter instanceof CustomPagerAdapter){
            imgs = new int[]{R.mipmap.img1,R.mipmap.img2,R.mipmap.img3};
            adapter = new ViewPagerAdapter(ViewPagerAdapterActivity.this,imgs);
            vp.setAdapter(adapter);
            oriIndex = 0;
        }else{
            if(oriIndex == 1){
                imgs = new int[]{R.mipmap.img1,R.mipmap.img2,R.mipmap.img3};
                adapter = new ViewPagerAdapter(ViewPagerAdapterActivity.this,imgs);
                vp.setAdapter(adapter);
            }else{
                imgs[0] = R.mipmap.img4;
                imgs[1] = R.mipmap.img5;
                imgs[2] = R.mipmap.img6;
                adapter.notifyDataSetChanged();
            }
            oriIndex = (++oriIndex)%2;
        }
    }
    private int modifyIndex = 0;
    public void customModifyItemNotify(View view) {
        if(adapter instanceof CustomPagerAdapter){
            if(modifyIndex == 1){
                imgs = new int[]{R.mipmap.img1,R.mipmap.img2,R.mipmap.img3};
                adapter = new CustomPagerAdapter(ViewPagerAdapterActivity.this,imgs);
                vp.setAdapter(adapter);
            }else{
                imgs[0] = R.mipmap.img4;
                imgs[1] = R.mipmap.img5;
                imgs[2] = R.mipmap.img6;
                adapter.notifyDataSetChanged();
            }
            modifyIndex = (++modifyIndex)%2;
        }else{
            imgs = new int[]{R.mipmap.img1,R.mipmap.img2,R.mipmap.img3};
            adapter = new CustomPagerAdapter(ViewPagerAdapterActivity.this,imgs);
            vp.setAdapter(adapter);
            modifyIndex = 0;
        }
    }

    public void modifyPageByAdapter(View view) {
        //
        ImageView i0 = ((ViewPagerAdapter)adapter).getPage(0);
        ImageView i1 = ((ViewPagerAdapter)adapter).getPage(1);
        ImageView i2 = ((ViewPagerAdapter)adapter).getPage(2);
        if(i0 != null){
            i0.setImageResource(R.mipmap.img2);
        }
        if(i1 != null){
            i1.setImageResource(R.mipmap.img4);
        }
        if(i2 != null){
            i2.setImageResource(R.mipmap.img6);
        }
    }

    class CustomPagerAdapter extends ViewPagerAdapter{
        public CustomPagerAdapter(Context mContext, int[] mImgs) {
            super(mContext, mImgs);
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
    }
}
