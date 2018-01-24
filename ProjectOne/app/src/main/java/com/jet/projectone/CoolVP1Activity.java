package com.jet.projectone;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.huanhailiuxin.coolviewpager.CoolViewPager;

import java.util.ArrayList;
import java.util.List;

public class CoolVP1Activity extends AppCompatActivity {
    private CoolViewPager vp1;
    private CoolViewPager vp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN|WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_cool_view_pager1);
        getSupportActionBar().hide();
        initView();
    }

    MyAdapter adapter11;
    MyAdapter adapter12;
    MyAdapter adapter2;
    private void initView() {
        vp1 = findViewById(R.id.vp1);
        vp2 = findViewById(R.id.vp2);
        List<View> items11 = new ArrayList<>();
        items11.add(createImageView(R.mipmap.img1));
        items11.add(createImageView(R.mipmap.img2));
        items11.add(createImageView(R.mipmap.img3));
        List<View> items12 = new ArrayList<>();
        items12.add(createImageView(R.mipmap.img4));
        items12.add(createImageView(R.mipmap.img5));
        items12.add(createImageView(R.mipmap.img6));
        List<View> items2 = new ArrayList<>();
        items2.add(createImageView(R.mipmap.img4));
        items2.add(createImageView(R.mipmap.img5));
        items2.add(createImageView(R.mipmap.img6));
        adapter11 = new MyAdapter(items11);
        adapter12 = new MyAdapter(items12);
        adapter2 = new MyAdapter(items2);
        setAdapter();
    }
    public View createImageView(int imgResId){
        ImageView imageView = new ImageView(this);
        imageView.setLayoutParams(new ViewPager.LayoutParams());
        imageView.setImageResource(imgResId);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    private void setAdapter(){
        vp2.setAdapter(adapter2);
    }

    public void setScrollHorizontal(View view) {
        vp1.setScrollMode(CoolViewPager.ScrollMode.HORIZONTAL);
        vp1.setAdapter(adapter11);
    }

    public void setScrollVertical(View view) {
        vp1.setScrollMode(CoolViewPager.ScrollMode.VERTICAL);
        vp1.setAdapter(adapter12);
    }

    private int colorIndex = 0;
    private int[] colors = new int[]{Color.GREEN,Color.RED,Color.BLUE};
    public void toggleEdgeEffectColor(View view) {
        colorIndex = (++colorIndex)%colors.length;
        vp2.setEdgeEffectColor(colors[colorIndex]);
    }

    public void toggleLoop(View view) {
        vp2.setInfiniteLoop(!vp2.isInfiniteLoop());
    }

    class MyAdapter extends PagerAdapter {
        private List<View> views;
        public MyAdapter(List<View> items){
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
    }
}
