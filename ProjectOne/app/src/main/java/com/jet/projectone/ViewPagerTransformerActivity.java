package com.jet.projectone;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * 主要是实验几种PageTransformer.注意RotateTransformer中的旋转支点,及View.setCameraDistance方法
 */
public class ViewPagerTransformerActivity extends AppCompatActivity {
    private ViewPager vp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN|WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_view_pager);
        getSupportActionBar().hide();
        initView();
    }

    private void initView() {
        vp = findViewById(R.id.vp);
        ViewPagerAdapter adapter = new ViewPagerAdapter(ViewPagerTransformerActivity.this,new int[]{R.mipmap.img1,R.mipmap.img2,R.mipmap.img3,R.mipmap.img4,R.mipmap.img5,R.mipmap.img6});
        vp.setAdapter(adapter);
        vp.setPageTransformer(false,transformers[transformIndex]);
    }

    private ViewPager.PageTransformer[] transformers = new ViewPager.PageTransformer[]{
            new RotateTransformer(),
            new FlipTransformer()
    };
    private int transformIndex = 0;
    public void toggleTransform(View view) {
        transformIndex = (++transformIndex)%2;
        vp.setPageTransformer(false,transformers[transformIndex]);
    }
}
