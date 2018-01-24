package com.jet.projectone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CoolViewPagerActivity extends BaseActivity {

    @Override
    int getContentView() {
        return R.layout.activity_cool_view_pager;
    }

    @Override
    void initViews() {
    }

    public void goCVP1(View view) {
        jump(CoolVP1Activity.class);
    }

    public void goCVP2(View view) {
        jump(CoolVP2Activity.class);
    }
}
