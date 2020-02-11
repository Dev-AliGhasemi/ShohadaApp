package com.ghasemi.golzarshohada.ViewPagerTop;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;

import com.ghasemi.golzarshohada.R;


public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    Fragment fragment;
    Context context;
    public ViewPagerAdapter(FragmentManager fm,Context context) {
        super(fm);
        this.context = context;
    }
    @Override
    public Fragment getItem(int position) {
        Log.e("In fragment" , position + "");
//        View view = LayoutInflater.from(context).inflate(R.layout.fragment_main,null);
//        progressBar = (ProgressBar) view.findViewById(R.id.act_2_progress);
        switch (position) {
            case 0:
                fragment = new Frag1();
                break;
            case 1:
                fragment = new Frag2();
                break;
            case 2:
                fragment = new Frag3();
                break;
            case 3:
                fragment = new Frag4();
                break;
            case 4:
                fragment = new Frag5();
                break;
        }
        Log.e("In fragment" , fragment.toString() + "");
        return fragment;
    }
    @Override
    public int getCount() {
        return 5;
    }
}
