package com.jet.project3;

import android.view.View;

import com.jet.project1.BaseActivity;
import com.jet.project1.R;

/**
 * 作者:幻海流心
 * GitHub:https://github.com/HuanHaiLiuXin
 * 邮箱:wall0920@163.com
 * 2018/2/2 14:58
 */

public class CameraMatrixActivity extends BaseActivity {
    @Override
    public int getContentView() {
        return R.layout.activity_camera_matrix;
    }

    @Override
    public void initViews() {

    }

    public void goCameraTestView(View view) {
        jump(CameraTestViewActivity.class);
    }

    public void goRotateAnimation(View view) {
        jump(Rotate3dAnimationActivity.class);
    }

    public void goCustom3D(View view) {
        jump(Custom3DViewActivity.class);
    }
}
