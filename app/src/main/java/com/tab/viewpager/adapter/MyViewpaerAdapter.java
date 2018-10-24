package com.tab.viewpager.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by v_fanlulin on 2018/10/22.
 */

public class MyViewpaerAdapter extends FragmentPagerAdapter {
    private List<String> titleLists;
    private List<Fragment> fragments;

    public MyViewpaerAdapter(FragmentManager fm, List<String> titleLists, List<Fragment> fragments) {
        super(fm);
        this.titleLists = titleLists;
        this.fragments = fragments;
    }

    public MyViewpaerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleLists.get(position);
    }
}
