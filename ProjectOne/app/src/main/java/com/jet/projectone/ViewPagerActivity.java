package com.jet.projectone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ViewPagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager2);
    }

    private void jump(Class clazz){
        startActivity(new Intent(ViewPagerActivity.this,clazz));
    }
    public void goTransformer(View view) {
        jump(ViewPagerTransformerActivity.class);
    }

    public void goAdapter(View view) {
        jump(ViewPagerAdapterActivity.class);
    }

    public void goBackground(View view) {
        jump(ViewPagerBackgroundActivity.class);
    }
}
