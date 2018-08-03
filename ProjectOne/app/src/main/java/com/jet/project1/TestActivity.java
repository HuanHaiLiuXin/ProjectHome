package com.jet.project1;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TestActivity extends AppCompatActivity {
    private TextView v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        v = findViewById(R.id.v);
    }

    public void test(View view) {
        Animator a1 = ObjectAnimator.ofInt(v,"scrollY",0,400).setDuration(400);
        Animator a2 = ObjectAnimator.ofInt(v,"scrollX",0,400).setDuration(400);
        a1.start();
        a2.start();

        ValueAnimator animator = ValueAnimator.ofInt(0,100).setDuration(1000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int currValue = (int) animation.getAnimatedValue();
                v.scrollTo(currValue,currValue);
            }
        });
        animator.start();
    }
}
