package com.jet.particle.copy;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.HuanHaiLiuXinUtils;
import com.jet.project1.R;

import java.util.ArrayList;

/**
 * 作者:幻海流心
 * GitHub:https://github.com/HuanHaiLiuXin
 * 邮箱:wall0920@163.com
 * 2018/2/28 16:04
 */

public class ExplosionField extends View {
    private Canvas mCanvas;
    private ArrayList<ExplosionAnimator> animators;

    public ExplosionField(Context context) {
        this(context,null,0);
    }
    public ExplosionField(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }
    public ExplosionField(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        attachToActivity();
        mCanvas = new Canvas();
        animators = new ArrayList<ExplosionAnimator>();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (ExplosionAnimator animator : animators){
            animator.draw(canvas);
        }
    }

    private void attachToActivity(){
        Activity activity = (Activity) getContext();
        ViewGroup viewGroup = activity.findViewById(Window.ID_ANDROID_CONTENT);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        viewGroup.addView(ExplosionField.this,params);
    }

    private void explode(final View view){
        Rect bound = new Rect();
        view.getGlobalVisibleRect(bound);
        if(!HuanHaiLiuXinUtils.isActivityFullScreen((Activity) getContext())){
            bound.offset(0,-HuanHaiLiuXinUtils.getStatusBarHeight());
        }
        Bitmap bitmap = createBitmapFromView(view);
        final ExplosionAnimator animator = new ExplosionAnimator(ExplosionField.this,bound,bitmap);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                animators.remove(animator);
                view.animate().setDuration(10).alpha(1.0F);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                view.animate().setDuration(0).alpha(0.0F);
            }
        });
        animators.add(animator);
        animator.start();
    }

    private Bitmap createBitmapFromView(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(),view.getHeight(), Bitmap.Config.ARGB_8888);
        if(bitmap != null){
            synchronized (mCanvas){
                mCanvas.setBitmap(bitmap);
                view.draw(mCanvas);
                mCanvas.setBitmap(null);
            }
        }
        return bitmap;
    }

    private OnClickListener onClickListener;
    public OnClickListener getOnClickListener(View view) {
        if(onClickListener == null){
            onClickListener = new OnClickListener() {
                @Override
                public void onClick(View v) {
                    explode(v);
                }
            };
        }
        return onClickListener;
    }
    public void addListener(View view){
        if(view instanceof ViewGroup){
            ViewGroup group = (ViewGroup) view;
            if(group.getChildCount() > 0){
                for (int i = 0; i< group.getChildCount(); i++){
                    addListener(group.getChildAt(i));
                }
            }
        }else{
            view.setClickable(true);
            view.setOnClickListener(getOnClickListener(view));
        }
    }
}
