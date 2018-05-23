package com.fastaoe.butterknife;

import android.app.Activity;
import android.view.View;

/**
 * 作者:幻海流心
 * GitHub:https://github.com/HuanHaiLiuXin
 * 邮箱:wall0920@163.com
 * 2018/5/23 13:54
 */
public class Utils {
    public static <T extends View> T findViewById(Activity activity,int resourceId){
        return (T) activity.findViewById(resourceId);
    }
}
