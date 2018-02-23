package com.jet.project1;

import android.view.View;
import android.widget.TextView;

/**
 * 作者:幻海流心
 * GitHub:https://github.com/HuanHaiLiuXin
 * 邮箱:wall0920@163.com
 * 2018/1/29 13:56
 */

public class NotifyResetFragment extends BaseFragment {
    private String notify;
    private String reset;
    private TextView textView;

    public NotifyResetFragment setResetNotify(String notify,String reset){
        this.notify = notify;
        this.reset = reset;
        return this;
    }

    @Override
    protected int onGetViewId() {
        return R.layout.item_pager;
    }

    @Override
    protected void onViewCreated(View view) {
        textView = view.findViewById(R.id.item_pager_tv);
    }

    @Override
    public void onRotateNotify() {
        textView.setText(notify);
    }

    @Override
    public void onRotateReset() {
        textView.setText(reset);
    }
}
