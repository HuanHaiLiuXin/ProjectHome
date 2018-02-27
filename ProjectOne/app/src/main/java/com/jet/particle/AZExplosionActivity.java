package com.jet.particle;

import android.widget.LinearLayout;

import com.jet.project1.BaseActivity;
import com.jet.project1.R;

/**
 * 作者:幻海流心
 * GitHub:https://github.com/HuanHaiLiuXin
 * 邮箱:wall0920@163.com
 * 2018/2/27 17:45
 */

public class AZExplosionActivity extends BaseActivity {
    @Override
    public int getContentView() {
        return R.layout.azexplosion;
    }

    @Override
    public void initViews() {
        LinearLayout root = findViewById(R.id.root);
        ExplosionField explosionField = new ExplosionField(AZExplosionActivity.this);
        explosionField.addListener(root);
    }
}
