package com.jet.cameraandmatrix;

import com.jet.project1.BaseActivity;
import com.jet.project1.R;

/**
 * 作者:幻海流心
 * GitHub:https://github.com/HuanHaiLiuXin
 * 邮箱:wall0920@163.com
 * 2018/2/22 18:16
 *
 * https://www.jianshu.com/p/34e0fe5f9e31
 * 实验发现:
 * 向上滑动过程中,在recycleMove中调用setNext,会出现界面展示向变为上一项的问题,仍不知道原因,简书中的源码也会出现这样的问题,
 * 暂时解决不了!!!
 */

public class Custom3DViewActivity extends BaseActivity {
    @Override
    public int getContentView() {
        return R.layout.activity_custom3dview;
    }

    @Override
    public void initViews() {

    }
}
