package com.sad490.smartscrape;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.sad490.smartscrape.NetWork.User;
import com.sad490.smartscrape.Posters.PostersFragment;
import com.sad490.smartscrape.Recommand.RecommandFragment;
import com.sad490.smartscrape.StaticFragment.StaticFragment;
import com.sad490.smartscrape.UserInfo.UserFragment;
import com.sad490.smartscrape.ViewpagerFragment.ViewPagerFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sad490 on 1/16/18.
 */

public class BottomAdapter extends FragmentPagerAdapter {

    private FLAGS flags;
    private List<Fragment> fragments = new ArrayList<>();
    private static UserData userData;
    private static Context context;

    public BottomAdapter(FragmentManager fm, FLAGS _flag, UserData _userData, Context _context) {
        super(fm);
        flags = _flag;
        context = _context;
        // adapter = new TabsPagerAdapter(fm, _flag);
        userData = _userData;
        init();
    }

    // TODO : Write a listener !!!
//    TabLayout.OnTabSelectedListener listener = new TabLayout.OnTabSelectedListener() {
//        @Override
//        public void onTabSelected(TabLayout.Tab tab) {
//
//        }
//
//        @Override
//        public void onTabUnselected(TabLayout.Tab tab) {
//
//        }
//
//        @Override
//        public void onTabReselected(TabLayout.Tab tab) {
//
//        }
//    }

    private void init() {
        // Fragment fragment0 = RecommandFragment.newInstance(context);
        Fragment fragment0 = ViewPagerFragment.newInstance( context, userData );
        //TODO : This Fragment must use Adapter !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        fragments.add(fragment0);
        Fragment fragment3 = PostersFragment.newInstance(3);
        fragments.add(fragment3);
        Fragment fragment2 = StaticFragment.newInstance(1);
        fragments.add(fragment2);
        UserFragment fragment1 = UserFragment.newInstance("Hello", "Baby", userData);
        fragments.add(fragment1);


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
