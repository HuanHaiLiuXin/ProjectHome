package com.jet.project1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.jet.cameraandmatrix.CameraMatrixActivity;
import com.jet.customanimation.CustomAnimationActivity;
import com.jet.scroller.ScrollerActivity;
import com.jet.measurespec.MeasureSpecActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN|WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
    }

    private void jump(Class clazz){
        startActivity(new Intent(MainActivity.this,clazz));
    }
    public void goViewPager(View view) {
        jump(ViewPagerActivity.class);
    }

    public void goScroller(View view) {
        jump(ScrollerActivity.class);
    }

    public void goCameraMatrix(View view) {
        jump(CameraMatrixActivity.class);
    }

    public void goMeasureSpec(View view) {
        jump(MeasureSpecActivity.class);
    }

    public void goCustomAnimation(View view) {
        jump(CustomAnimationActivity.class);
    }
}
