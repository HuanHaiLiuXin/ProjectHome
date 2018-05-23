package com.jet.annotationdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fastaoe.butterknife.ButterKnife;
import com.fastaoe.butterknife.Unbinder;
import com.huanhailiuxin.customannotation.annotations.BindView;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.bt1)
    TextView bt1;
    @BindView(R.id.bt2)
    TextView bt2;
    Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    public void execute(View view) {
        unbinder = ButterKnife.bind(this);
        bt1.setText("BT1 ButterKnife");
        bt2.setText("BT2 ButterKnife");
    }
}