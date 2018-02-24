package com.jet.measurespec;

import android.view.View;

import com.jet.project1.BaseActivity;
import com.jet.project1.R;

/**
 * 作者:幻海流心
 * GitHub:https://github.com/HuanHaiLiuXin
 * 邮箱:wall0920@163.com
 * 2018/2/8 15:45

 Log打印记录:
 说明:
    ViewGroup宽高为具体值或者match_parent,则为    MeasureSpec.EXACTLY"
    ViewGroup宽高为wrap_content,则为 MeasureSpec.AT_MOST"
 <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
 android:orientation="vertical" android:layout_width="match_parent"
 android:layout_height="match_parent">
 <com.jet.project4.MeasureSpecViewGroup
 android:layout_width="match_parent"
 android:layout_height="match_parent">
 <TextView
 android:layout_width="match_parent"
 android:layout_height="wrap_content"
 android:text="MeasureSpec"
 />
 </com.jet.project4.MeasureSpecViewGroup>
 </LinearLayout>
 //////////////////////
 E/Jet: measureChildWithMargins:widthMode=MeasureSpec.EXACTLY
 E/Jet: measureChildWithMargins:heightMode=MeasureSpec.EXACTLY
 E/Jet: measureChildWithMargins:widthSize=1080
 E/Jet: measureChildWithMargins:heightSize=1860
 E/Jet: measureChildWithMargins:widthMode=MeasureSpec.EXACTLY
 E/Jet: measureChildWithMargins:heightMode=MeasureSpec.EXACTLY
 E/Jet: measureChildWithMargins:widthSize=1080
 E/Jet: measureChildWithMargins:heightSize=1920

 <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
 android:orientation="vertical" android:layout_width="match_parent"
 android:layout_height="match_parent">
 <com.jet.project4.MeasureSpecViewGroup
 android:layout_width="300dp"
 android:layout_height="300dp">
 <TextView
 android:layout_width="match_parent"
 android:layout_height="wrap_content"
 android:text="MeasureSpec"
 />
 </com.jet.project4.MeasureSpecViewGroup>
 </LinearLayout>
 ////////////////////////
 E/Jet: measureChildWithMargins:widthMode=MeasureSpec.EXACTLY
 E/Jet: measureChildWithMargins:heightMode=MeasureSpec.EXACTLY
 E/Jet: measureChildWithMargins:widthSize=900
 E/Jet: measureChildWithMargins:heightSize=900
 E/Jet: measureChildWithMargins:widthMode=MeasureSpec.EXACTLY
 E/Jet: measureChildWithMargins:heightMode=MeasureSpec.EXACTLY
 E/Jet: measureChildWithMargins:widthSize=900
 E/Jet: measureChildWithMargins:heightSize=900

 <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
 android:orientation="vertical" android:layout_width="match_parent"
 android:layout_height="match_parent">
 <com.jet.project4.MeasureSpecViewGroup
 android:layout_width="wrap_content"
 android:layout_height="wrap_content">
 <TextView
 android:layout_width="match_parent"
 android:layout_height="wrap_content"
 android:text="MeasureSpec"
 />
 </com.jet.project4.MeasureSpecViewGroup>
 </LinearLayout>
 /////////////////////////
 E/Jet: measureChildWithMargins:widthMode=MeasureSpec.AT_MOST
 E/Jet: measureChildWithMargins:heightMode=MeasureSpec.AT_MOST
 E/Jet: measureChildWithMargins:widthSize=1080
 E/Jet: measureChildWithMargins:heightSize=1860
 E/Jet: measureChildWithMargins:widthMode=MeasureSpec.AT_MOST
 E/Jet: measureChildWithMargins:heightMode=MeasureSpec.AT_MOST
 E/Jet: measureChildWithMargins:widthSize=1080
 E/Jet: measureChildWithMargins:heightSize=1920
 */

public class MeasureSpecActivity extends BaseActivity{
    @Override
    public int getContentView() {
        return R.layout.activity_measurespec;
    }

    @Override
    public void initViews() {

    }

    public void checkModeSize1(View view) {
        jump(MeasureSpec1Activity.class);
    }

    public void checkModeSize2(View view) {
        jump(MeasureSpec2Activity.class);
    }

    public void checkModeSize3(View view) {
        jump(MeasureSpec3Activity.class);
    }
}
