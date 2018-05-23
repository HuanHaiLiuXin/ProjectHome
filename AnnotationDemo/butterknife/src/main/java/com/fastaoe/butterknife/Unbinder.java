package com.fastaoe.butterknife;

import android.support.annotation.UiThread;

/**
 * 作者:幻海流心
 * GitHub:https://github.com/HuanHaiLiuXin
 * 邮箱:wall0920@163.com
 * 2018/5/23 13:57
 */
public interface Unbinder {
    @UiThread
    void unbind();
    Unbinder EMPTY = new Unbinder() {
        @Override
        public void unbind() {
        }
    };
}
