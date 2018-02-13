package com.sad490.smartscrape;

import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by sad490 on 1/16/18.
 */


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {
    private String[] tab = {"Followed", "Good", "All"};
    public static FLAGS flag = FLAGS.HOME;

    public TabsPagerAdapter(FragmentManager fm, FLAGS _flag) {
        super(fm);
        flag = _flag;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        return ComFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tab[position];
    }
}

