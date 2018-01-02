package com.jet.projectone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void jump(Class clazz){
        startActivity(new Intent(MainActivity.this,clazz));
    }
    public void goViewPager(View view) {
        jump(ViewPagerActivity.class);
    }
}
