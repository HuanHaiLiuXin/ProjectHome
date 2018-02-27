package com.jet.particle;

import android.view.View;

import com.jet.project1.BaseActivity;
import com.jet.project1.R;

/**
 * 作者:幻海流心
 * GitHub:https://github.com/HuanHaiLiuXin
 * 邮箱:wall0920@163.com
 * 2018/2/27 17:37
 */

public class ParticleActivity extends BaseActivity {
    @Override
    public int getContentView() {
        return R.layout.activity_particle;
    }

    @Override
    public void initViews() {

    }

    public void goAZExplosion(View view) {
        jump(AZExplosionActivity.class);
    }
}
