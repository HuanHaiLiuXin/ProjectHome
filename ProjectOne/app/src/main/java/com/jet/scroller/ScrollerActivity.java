package com.jet.scroller;

import android.view.View;

import com.jet.project1.BaseActivity;
import com.jet.project1.R;

/**
 * 作者:幻海流心
 * GitHub:https://github.com/HuanHaiLiuXin
 * 邮箱:wall0920@163.com
 * 2018/2/1 15:43
 */

public class ScrollerActivity extends BaseActivity {
    @Override
    public int getContentView() {
        return R.layout.activity_scroller;
    }

    @Override
    public void initViews() {

    }

    public void goBasic(View view) {
        jump(SC1Activity.class);
    }

    public void goScrollerViewPager(View view) {
        jump(ScrollerViewPagerActivity.class);
    }
}
