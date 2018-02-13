package com.sad490.smartscrape;

import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sad490.smartscrape.NetWork.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sad490 on 1/17/18.
 */

public class ViewPagerFragment extends Fragment {

    static final int NUM_ITEMS = 3;

    private ViewPagerAdapter mAdapter;
    private ViewPager mPager;
    private TabsPagerAdapter adapter;

    private static List<Fragment> fragments = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_pager_fragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mAdapter = new ViewPagerAdapter(getFragmentManager());
        adapter = new TabsPagerAdapter(getFragmentManager(), FLAGS.HOME);

        mPager = (ViewPager) getView().findViewById(R.id.pager_2);
        mPager.setAdapter(adapter);
        init();
    }

    private static void init() {
        fragments.add(ComFragment.newInstance(0));
        fragments.add(ComFragment.newInstance(0));
        fragments.add(ComFragment.newInstance(0));
    }

    public static class ViewPagerAdapter extends FragmentPagerAdapter {
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int num) {
            Log.e("Num", "" +  num);
            return fragments.get(num);
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }
    }

}
