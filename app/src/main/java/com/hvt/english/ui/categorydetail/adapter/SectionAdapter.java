package com.hvt.english.ui.categorydetail.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by doannh on 7/20/17.
 */

public class SectionAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments = new ArrayList<>();

    public SectionAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    public SectionAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setData(List<Fragment> fragments) {
        this.fragments = fragments;
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
