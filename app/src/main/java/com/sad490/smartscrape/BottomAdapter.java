package com.sad490.smartscrape;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.sad490.smartscrape.NetWork.User;
import com.sad490.smartscrape.UserInfo.UserFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sad490 on 1/16/18.
 */

public class BottomAdapter extends FragmentPagerAdapter {

    private TabsPagerAdapter adapter;
    private FLAGS flags;
    private List<Fragment> fragments = new ArrayList<>();
    private static UserData userData;

    public BottomAdapter(FragmentManager fm, FLAGS _flag, UserData _userData) {
        super(fm);
        flags = _flag;
        // adapter = new TabsPagerAdapter(fm, _flag);
        userData = _userData;
        init();
    }

    private void init() {
        Fragment fragment0 = new ViewPagerFragment();
        //TODO : This Fragment must use Adapter !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        fragments.add(fragment0);
        UserFragment fragment1 = UserFragment.newInstance("Hello", "Baby", userData);
        fragments.add(fragment1);
        Fragment fragment2 = StatisticFragment.newInstance();
        fragments.add(fragment2);
        Fragment fragment3 = new ViewPagerFragment();
        fragments.add(fragment3);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        if (position == 0) {
            return fragments.get(position);
        } else if (position == 1) {
            return fragments.get(position);
        } else if (position == 2) {
            return fragments.get(position);
        } else{
            return fragments.get(position);
        }
    }

    @Override
    public int getCount() {
        return 4;
    }


}
