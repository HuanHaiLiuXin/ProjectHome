package com.jet.projectone;

import android.animation.ArgbEvaluator;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * ViewPager背景色渐变,其实仅仅只是ArgbEvaluator的使用方法.{@link ArgbEvaluator#evaluate(float, Object, Object)}
 * int evaluate = (int) evaluator.evaluate(positionOffset,0xFF01499B,0xFF42861F);
 */
public class ViewPagerBackgroundActivity extends AppCompatActivity {
    private ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_view_pager_background);
        getSupportActionBar().hide();
        initViews();
    }

    private void initViews() {
        vp = findViewById(R.id.vp);
        vp.setAdapter(new PagerAdapter() {
            private String[] items = new String[]{"1","2","3","4","5"};
            @Override
            public int getCount() {
                return items.length;
            }
            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }
            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                TextView tv = new TextView(ViewPagerBackgroundActivity.this);
                tv.setLayoutParams(new ViewPager.LayoutParams());
                tv.setGravity(Gravity.CENTER);
                tv.setTextSize(40);
                tv.setTextColor(Color.WHITE);
                tv.setBackgroundColor(Color.TRANSPARENT);
                tv.setText(items[position]);
                container.addView(tv);
                return tv;
            }
            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View) object);
            }
        });
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                ArgbEvaluator evaluator = new ArgbEvaluator();
                //初始颜色为透明白色
                int evaluate = 0x00FFFFFF;
                if(position == 0){
                    evaluate = (int) evaluator.evaluate(positionOffset,0xFF01499B,0xFF42861F);
                }else if(position == 1){
                    evaluate = (int) evaluator.evaluate(positionOffset,0xFF42861F,0xFFEABA6B);
                }else if(position == 2){
                    evaluate = (int) evaluator.evaluate(positionOffset,0xFFEABA6B,0xFFDB5347);
                }else if(position == 3){
                    evaluate = (int) evaluator.evaluate(positionOffset,0xFFDB5347,0xFFAE8CC0);
                }else{
                    evaluate = 0xFFAE8CC0;
                }
                vp.setBackgroundColor(evaluate);
            }
            @Override
            public void onPageSelected(int position) {
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
}
