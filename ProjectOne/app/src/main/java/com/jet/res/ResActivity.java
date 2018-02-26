package com.jet.res;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.jet.project1.BaseActivity;
import com.jet.project1.R;

/**
 * 作者:幻海流心
 * GitHub:https://github.com/HuanHaiLiuXin
 * 邮箱:wall0920@163.com
 * 2018/2/26 16:52
 *
 * res文件夹中的资源ID可以做加减运算,初见于:com.open.turnpage.TuruPageActivity
 * 根据查看R文件中的资源ID值的排序规则可知,只有资源名称前缀一致,且我们可以准确预知其ID值排序的同类型资源,才可以通过加减运算直接使用
 *
 见项目的R文件:
 按顺序添加后使用
 public static final int restest1=0x7f060035;
 public static final int restest2=0x7f060036;
 public static final int restest3=0x7f060037;
 public static final int restest4=0x7f060038;
 public static final int restest5=0x7f060039;
 public static final int restest6=0x7f06003a;
 public static final int restest7=0x7f06003b;
 public static final int restest8=0x7f06003c;

 继续添加2条后再次查看:
 //新添加的
 public static final int addResStr=0x7f06001f;
 public static final int addResStrStr=0x7f060020;
 //之前添加的变成:之前添加的String的ID值都+2
 public static final int restest1=0x7f060037;
 public static final int restest2=0x7f060038;
 public static final int restest3=0x7f060039;
 public static final int restest4=0x7f06003a;
 public static final int restest5=0x7f06003b;
 public static final int restest6=0x7f06003c;
 public static final int restest7=0x7f06003d;
 public static final int restest8=0x7f06003e;

 截取部分ID看规律:
 //1:首先 同类资源的资源名称先进行排序:ab==>add==>app==>btn,ID值顺序不一定
 public static final int abc_font_family_subhead_material=0x7f06001d;
 public static final int abc_font_family_title_material=0x7f06001e;
 //ID值顺序不一定:从0x7f06001d->0x7f06001e到0x7f060008,说明不同的资源名称前缀,其ID值不会按照名称排序递增
 public static final int abc_search_hint=0x7f060008;
 //2:资源名称前缀一致的,根据排序,其ID值依次+1
 public static final int abc_searchview_description_clear=0x7f060009;
 public static final int abc_searchview_description_query=0x7f06000a;
 public static final int abc_searchview_description_search=0x7f06000b;
 public static final int abc_searchview_description_submit=0x7f06000c;
 public static final int abc_searchview_description_voice=0x7f06000d;
 public static final int abc_shareactionprovider_share_with=0x7f06000e;
 public static final int abc_shareactionprovider_share_with_application=0x7f06000f;
 public static final int abc_toolbar_collapse_description=0x7f060010;
 public static final int addResStr=0x7f06001f;
 public static final int addResStrStr=0x7f060020;
 public static final int app_name=0x7f060021;
 public static final int btn_text_1=0x7f060022;
 public static final int btn_text_2=0x7f060023;
 public static final int btn_text_3=0x7f060024;
 */

public class ResActivity extends BaseActivity {
    private AppCompatImageView iv;
    private AppCompatTextView tv;
    private int resIvIndex = 0;
    private int resTvIndex = 0;

    @Override
    public int getContentView() {
        return R.layout.activity_res;
    }

    @Override
    public void initViews() {
        iv = findViewById(R.id.iv);
        tv = findViewById(R.id.tv);
    }

    public void testMipMapRes(View view) {
        //img1,---,img8
        int resId = R.mipmap.img1 + (resIvIndex++) % 8;
        iv.setImageResource(resId);
    }

    public void testStringRes(View view) {
        //restest3
        int resId = R.string.restest1 + (resTvIndex++) % 8;
        tv.setText(resId);
    }
}
