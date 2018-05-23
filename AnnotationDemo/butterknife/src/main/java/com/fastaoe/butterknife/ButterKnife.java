package com.fastaoe.butterknife;

import android.app.Activity;

import java.lang.reflect.Constructor;

/**
 * 作者:幻海流心
 * GitHub:https://github.com/HuanHaiLiuXin
 * 邮箱:wall0920@163.com
 * 2018/5/23 13:59
 */
public class ButterKnife {
    public static Unbinder bind(Activity activity){
        try {
            Class<? extends Unbinder> bindClazz = (Class<? extends Unbinder>) Class.forName(activity.getClass().getName() + "_ViewBinding");
            //构造函数
            Constructor<? extends Unbinder> bindConstructor = bindClazz.getDeclaredConstructor(activity.getClass());
            Unbinder unbinder = bindConstructor.newInstance(activity);
            return unbinder;
        }catch (Exception e){
            e.printStackTrace();
        }
        return Unbinder.EMPTY;
    }
}
