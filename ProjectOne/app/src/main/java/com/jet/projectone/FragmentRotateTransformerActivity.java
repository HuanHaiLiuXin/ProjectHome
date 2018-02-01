package com.jet.projectone;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.jet.projectone.transformer.FragmentRotateTransformer;
import com.jet.projectone.transformer.OnRotateListener;

import java.util.ArrayList;

public class FragmentRotateTransformerActivity extends BaseActivity {
    private RelativeLayout rl;
    private ViewPager vp;
    private ArrayList<BaseFragment> fragments;
    private ArrayList<String> titles;
    private ViewPagerAdapter adapter;
    private FragmentRotateTransformer fragmentRotateTransformer;

    @Override
    public int getContentView() {
        return R.layout.activity_fragment_rotate_transformer;
    }

    @Override
    public void initViews() {
        rl = findViewById(R.id.rl);
        vp = findViewById(R.id.vp);
        vp.setOffscreenPageLimit(7);
        fragments = new ArrayList<>();
        fragments.add(new NotifyResetFragment().setResetNotify("12%","日账户"));
        fragments.add(new NotifyResetFragment().setResetNotify("14%","周账户"));
        fragments.add(new NotifyResetFragment().setResetNotify("16%","月账户"));
        fragments.add(new NotifyResetFragment().setResetNotify("18%","季账户"));
        fragments.add(new NotifyResetFragment().setResetNotify("20%","年账户"));
        fragments.add(new NotifyResetFragment().setResetNotify("22%","5年账户"));
        fragments.add(new NotifyResetFragment().setResetNotify("24%","10年账户"));
        titles = new ArrayList<>();
        titles.add("日账户");
        titles.add("周账户");
        titles.add("月账户");
        titles.add("季账户");
        titles.add("年账户");
        titles.add("5年账户");
        titles.add("10年账户");
        adapter = new ViewPagerAdapter(getSupportFragmentManager(),fragments,titles);
        vp.setAdapter(adapter);
        fragmentRotateTransformer = new FragmentRotateTransformer();
        fragmentRotateTransformer.setOnRotateListener(new OnRotateListener() {
            @Override
            public void onRotateNotify() {
                for (BaseFragment fragment:fragments){
                    fragment.onRotateNotify();
                }
            }
            @Override
            public void onRotateReset() {
                for (BaseFragment fragment:fragments){
                    fragment.onRotateReset();
                }
            }
        });
        vp.setPageTransformer(false,fragmentRotateTransformer);
        rl.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return vp.dispatchTouchEvent(event);
            }
        });
        vp.setPageTransformer(true,fragmentRotateTransformer);
    }

    class ViewPagerAdapter extends FragmentStatePagerAdapter{
        private ArrayList<BaseFragment> fragments;
        private ArrayList<String> titles;

        public ViewPagerAdapter(FragmentManager fm) {
            this(fm,null,null);
        }

        public ViewPagerAdapter(FragmentManager fm, ArrayList<BaseFragment> fragments, ArrayList<String> titles) {
            super(fm);
            this.fragments = fragments;
            this.titles = titles;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
