package com.jet.project2;

import android.view.View;
import android.widget.LinearLayout;

import com.jet.project1.BaseActivity;
import com.jet.project1.R;

/**
 * 作者:幻海流心
 * GitHub:https://github.com/HuanHaiLiuXin
 * 邮箱:wall0920@163.com
 * 2018/2/1 15:47
 */

public class SC1Activity extends BaseActivity {
    private LinearLayout ll;

    @Override
    public int getContentView() {
        return R.layout.activity_scroller1;
    }

    @Override
    public void initViews() {
        ll = findViewById(R.id.ll);
    }

    public void scrollTo(View view) {
        ll.scrollTo(-60,-100);
    }

    public void scrollBy(View view) {
        ll.scrollBy(-20,-30);
    }
}
