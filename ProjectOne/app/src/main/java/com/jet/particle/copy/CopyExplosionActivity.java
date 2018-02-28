package com.jet.particle.copy;

import android.widget.LinearLayout;

import com.jet.project1.BaseActivity;
import com.jet.project1.R;

/**
 * 作者:幻海流心
 * GitHub:https://github.com/HuanHaiLiuXin
 * 邮箱:wall0920@163.com
 * 2018/2/28 16:29
 */

public class CopyExplosionActivity extends BaseActivity {
    private LinearLayout root;

    @Override
    public int getContentView() {
        return R.layout.activity_copy_explosion;
    }

    @Override
    public void initViews() {
        root = findViewById(R.id.root);
        ExplosionField explosionField = new ExplosionField(CopyExplosionActivity.this);
        explosionField.addListener(root);
    }
}
