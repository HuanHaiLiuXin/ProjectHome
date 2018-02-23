package com.jet.project1;

import android.view.View;

public class CoolViewPagerActivity extends BaseActivity {

    @Override
    public int getContentView() {
        return R.layout.activity_cool_view_pager;
    }

    @Override
    public void initViews() {
    }

    public void goCVP1(View view) {
        jump(CoolVP1Activity.class);
    }

    public void goCVP2(View view) {
        jump(CoolVP2Activity.class);
    }

    public void goCVP3(View view) {
        jump(CoolVP3Activity.class);
    }

    public void goCVP4(View view) {
        jump(CoolVP4Activity.class);
    }
}
