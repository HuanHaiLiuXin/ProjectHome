package com.jet.customanimation;

import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.view.animation.Animation;

import com.jet.project1.BaseActivity;
import com.jet.project1.R;

/**
 * 作者:幻海流心
 * GitHub:https://github.com/HuanHaiLiuXin
 * 邮箱:wall0920@163.com
 * 2018/2/24 10:43
 *
 * 所谓的"折叠效果",是上下两个View执行绕X轴旋转动画的叠加效果
 */

public class CopyFlipAnimationActivity extends BaseActivity {
    private View bg_1;
    private AppCompatTextView tv_1,tv_2;
    private CopyFlipCardAnimation animationBg,animation1,animation2;
    private String[] contents = new String[]{"八度空间","叶惠美","七里香"};
    private int contentIndex = 1;

    @Override
    public int getContentView() {
        return R.layout.activity_copyflipcards;
    }

    @Override
    public void initViews() {
        bg_1 = findViewById(R.id.bg_1);
        tv_1 = findViewById(R.id.tv_1);
        tv_2 = findViewById(R.id.tv_2);
        tv_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewStartAnimation(bg_1,null,animationBg,0F,-180F);
                viewStartAnimation(tv_1,tv_1,animation1,0F,180F);
            }
        });
        tv_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewStartAnimation(tv_2,tv_2,animation2,0F,-180F);
            }
        });
    }
    private void viewStartAnimation(View target, @Nullable final AppCompatTextView targetTv, @Nullable CopyFlipCardAnimation animation,float fromDegree, float toDegree){
        if(animation == null){
            animation = new CopyFlipCardAnimation(fromDegree,toDegree,target.getWidth()/2,target.getHeight()/2);
        }
        animation.setFillAfter(false);
        animation.setDuration(4000);
        animation.setRepeatCount(-1);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
                ((CopyFlipCardAnimation)animation).setContentShouldFlip();
            }
        });
        animation.setCardFlipListener(new CopyFlipCardAnimation.OnCardFlipListener() {
            @Override
            public void cardFlip() {
                if(targetTv != null){
                    targetTv.setText(contents[contentIndex]);
                    contentIndex = (++contentIndex)%contents.length;
                }
            }
        });
        target.startAnimation(animation);
    }
}
