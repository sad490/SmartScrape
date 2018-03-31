package com.sad490.smartscrape.ViewpagerFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sad490.smartscrape.R;
import com.sad490.smartscrape.UserData;

/**
 * Created by sad490 on 2/18/18.
 */

public class ViewPagerFragment extends Fragment {

    private ChildViewPager viewPager;
    private TabLayout tabs ;
    private TabItem tab1;
    private TabItem tab2;
    private TabItem tab3;

    private static Context context;

    private static ChildAdapter adapter;

    private static UserData userData;

    public ViewPagerFragment() {
    }

    public static ViewPagerFragment newInstance(Context _context, UserData _userData ) {
        ViewPagerFragment fragment = new ViewPagerFragment();
        context = _context;
        userData = _userData;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewpager, null);
        viewPager = (ChildViewPager) view.findViewById(R.id.viewpager);
        tabs = (TabLayout)view.findViewById(R.id.tabs);
//        tab1 = (TabItem)view.findViewById(R.id.tab1);
//        tab2 = (TabItem)view.findViewById(R.id.tab2);
//        tab3 = (TabItem)view.findViewById(R.id.tab3);

        adapter = new ChildAdapter( getChildFragmentManager(), userData, context );
        viewPager.setAdapter(adapter);
        tabs.setupWithViewPager(viewPager);
        tabs.getTabAt(0).setText("推荐");
        tabs.getTabAt(2).setText("历史");
        tabs.getTabAt(1).setText("已关注");

        return view;
    }
}
