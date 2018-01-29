package com.jet.projectone;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jet.projectone.transformer.OnRotateListener;

/**
 * 作者:幻海流心
 * GitHub:https://github.com/HuanHaiLiuXin
 * 邮箱:wall0920@163.com
 * 2018/1/29 13:46
 */

public abstract class BaseFragment extends Fragment implements OnRotateListener {
    protected int onGetViewId() {
        return 0;
    }

    protected void onViewCreated(View view) {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int viewId = onGetViewId();
        if (viewId != 0) {
            View layout = inflater.inflate(viewId, null, false);
            onViewCreated(layout);
            return layout;
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}