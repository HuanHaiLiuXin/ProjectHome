package com.jet.particle;

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

import java.util.ArrayList;

/**
 * 作者:幻海流心
 * GitHub:https://github.com/HuanHaiLiuXin
 * 邮箱:wall0920@163.com
 * 2018/2/27 13:58
 *
 * Copy的:https://github.com/Xieyupeng520/AZExplosion
 */

public class ExplosionField extends View{
    Canvas mCanvas = new Canvas();
    private ArrayList<ExplosionAnimator> explosionAnimators;
    private OnClickListener onClickListener;

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
        //初始化
        explosionAnimators = new ArrayList<ExplosionAnimator>();
        attach2Activity((Activity) getContext());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制粒子
        for (ExplosionAnimator animator : explosionAnimators){
            animator.draw(canvas);
        }
    }



    /**
     * 给一个View实例,将其快照通过另一个新的Canvas将其复制到一个Bitmap实例中
     * @param view
     * @return
     */
    private Bitmap createBitmapFromView(View view){
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(),view.getHeight(), Bitmap.Config.ARGB_8888);
        if(bitmap != null){
            synchronized (mCanvas){
                mCanvas.setBitmap(bitmap);
                view.draw(mCanvas);
                //清除引用
                mCanvas.setBitmap(null);
            }
        }
        return bitmap;
    }

    /**
     * 给Activity加上全屏覆盖的ExplosionField
     * @param activity
     */
    private void attach2Activity(Activity activity){
        ViewGroup rootView = activity.findViewById(Window.ID_ANDROID_CONTENT);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        rootView.addView(this,params);
    }

    /**
     * 对指定的View实例执行爆炸
     * @param view  要执行爆炸效果的View实例
     */
    private void explode(final View view){
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        if(!HuanHaiLiuXinUtils.isActivityFullScreen((Activity) getContext())){
            rect.offset(0,-HuanHaiLiuXinUtils.getStatusBarHeight());
        }
        final ExplosionAnimator animator = new ExplosionAnimator(ExplosionField.this,createBitmapFromView(view),rect);
        explosionAnimators.add(animator);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                view.animate().alpha(0.0F).setDuration(150).start();
            }
            @Override
            public void onAnimationEnd(Animator animation) {
                view.animate().alpha(1.0F).setDuration(150).start();
                //动画结束后,将其从List中移除
                explosionAnimators.remove(animator);
                animation = null;
            }
        });
        animator.start();
    }

    private OnClickListener getOnClickListener() {
        if(onClickListener == null){
            onClickListener = new OnClickListener() {
                @Override
                public void onClick(View v) {
                    ExplosionField.this.explode(v);
                }
            };
        }
        return onClickListener;
    }

    /**
     * 希望那些View可以有爆炸动画
     * @param view
     */
    public void addListener(View view){
        if(view instanceof ViewGroup){
            ViewGroup group = (ViewGroup) view;
            int count = group.getChildCount();
            if(count > 0){
                for (int i=0;i<count;i++){
                    addListener(group.getChildAt(i));
                }
            }
        }else{
            view.setClickable(true);
            view.setOnClickListener(getOnClickListener());
        }
    }
}
