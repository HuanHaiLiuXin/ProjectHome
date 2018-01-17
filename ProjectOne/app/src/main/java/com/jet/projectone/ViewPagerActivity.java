package com.jet.projectone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.tmall.ultraviewpager.sample.UPVDemoActivity;

public class ViewPagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager2);
    }

    private void jump(Class clazz){
        startActivity(new Intent(ViewPagerActivity.this,clazz));
    }
    public void goTransformer(View view) {
        jump(ViewPagerTransformerActivity.class);
    }

    public void goAdapter(View view) {
        jump(ViewPagerAdapterActivity.class);
    }

    public void goBackground(View view) {
        jump(ViewPagerBackgroundActivity.class);
    }

    public void goLoop(View view) {
        jump(LoopViewPagerActivity.class);
    }

    public void goVertical(View view) {
        jump(VerticalViewPagerOriActivity.class);
    }

    public void goCustomViewPager(View view) {
        jump(CollViewPagerActivity.class);
    }

    public void goAlibabaVP(View view) {
        jump(UPVDemoActivity.class);
    }
}
