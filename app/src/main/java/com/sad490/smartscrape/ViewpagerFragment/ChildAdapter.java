package com.sad490.smartscrape.ViewpagerFragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.sad490.smartscrape.FLAGS;
import com.sad490.smartscrape.Recommand.FollowingFragment;
import com.sad490.smartscrape.Recommand.RecommandFragment;
import com.sad490.smartscrape.Recommand.StarredFragment;
import com.sad490.smartscrape.UserData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sad490 on 2/18/18.
 */

public class ChildAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments = new ArrayList<>();

    private static Context context;
    private UserData userData;

    public ChildAdapter(FragmentManager fm, UserData _userData, Context _context) {
        super(fm);
        context = _context;
        userData = _userData;
        init();
    }

    private void init() {
        fragments.add(RecommandFragment.newInstance(context));
        fragments.add(StarredFragment.newInstance(context));
        fragments.add(FollowingFragment.newInstance(context));
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
//        if (position == 0) {
//            return fragments.get(position);
//        } else if (position == 1) {
//            return fragments.get(position);
//        } else {
//            return fragments.get(position);
//        }
        return fragments.get(position);
    }


    @Override
    public int getCount() {
        return 3;
    }
}
