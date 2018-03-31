package com.sad490.smartscrape.Recommand;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sad490.smartscrape.NetWork.Article;
import com.sad490.smartscrape.NetWork.GetRecommand;
import com.sad490.smartscrape.NetWork.User;
import com.sad490.smartscrape.R;
import com.sad490.smartscrape.Recommand.dummy.DummyContent;

import org.apache.http.impl.client.DefaultHttpClient;

import java.util.ArrayList;
import java.util.List;

import co.lujun.androidtagview.TagContainerLayout;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by sad490 on 2/20/18.
 */

public class FollowingFragment extends Fragment {
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private FollowingFragment.OnFollowingPageListener mListener;
    public static Context context_App;

    public static MultiTypeAdapter adapter;

    private static List<RecItem> rec_items = new ArrayList<>();

    private static final int Load_History_Data_finished = 4;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public FollowingFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static FollowingFragment newInstance( Context _context ) {
        // DummyContent.setContext(_context);
        FollowingFragment fragment = new FollowingFragment();
        Bundle args = new Bundle();
        context_App = _context;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
        adapter = new MultiTypeAdapter();
        adapter.register(RecItem.class, new FollowingViewBinder(mListener, getContext()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_following, container, false);
        Context context = view.getContext();
        RecyclerView recyclerView = view.findViewById(R.id.list);
        recyclerView.addItemDecoration(new RecommandFragment.SpacesItemDecoration(8));
        recyclerView.setAdapter(adapter);
        // recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        // recyclerView.setAdapter(new MyItemRecyclerViewAdapter(DummyContent.ITEMS, mListener));

//        List<String> tags = new ArrayList<>();
//        tags.add("C++");
//        tags.add("Java");
//        tags.add("Python");
//        TagContainerLayout mTagContainerLayout = (TagContainerLayout) view.findViewById(R.id.tagview);
//        mTagContainerLayout.setTags(tags);

        // Set the adapter
//        if (view instanceof RecyclerView) {
//            Context context_inside = view.getContext();
//            RecyclerView recyclerView_inside = (RecyclerView) view;
//            if (mColumnCount <= 1) {
//                recyclerView.setLayoutManager(new LinearLayoutManager(context));
//            } else {
//                recyclerView.setLayoutManager(new GridLayoutManager(context_inside, mColumnCount));
//            }
//            recyclerView_inside.setAdapter(new MyItemRecyclerViewAdapter(DummyContent.ITEMS, mListener));
//        }
        new Thread(Load_his).start();
        return view;
    }

    Runnable Load_his = new Runnable() {
        @Override
        public void run() {
            try {
                DefaultHttpClient client = null;
                synchronized ( this ){
                    client = User.getHttpclient();
                }
                // todo ; Repair it !!!!!!!!!!!!!!!
                rec_items = GetRecommand.getHistory( client );
                Message message = mHandler.obtainMessage();
                message.what = Load_History_Data_finished;
                mHandler.sendMessage(message);
            }catch (Exception e) {
                e.printStackTrace();
                try {
                    Thread.sleep(3500);
                }catch (Exception e1) {
                    e1.printStackTrace();
                }
                new Thread(Load_his).start();
            }
        }
    };

    @Override
    public void onResume(){
        super.onResume();
        new Thread(Load_his).start();
    }

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch( msg.what ) {
                case Load_History_Data_finished:
                    Log.d("Load Starred Finished", "Finished");
                    adapter.setItems(rec_items);
                    adapter.notifyDataSetChanged();
//                    StarredFragment.adapter.setItems(starred_posters);
//                    StarredFragment.adapter.notifyDataSetChanged();
                    break;
            }
        }
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FollowingFragment.OnFollowingPageListener) {
            mListener = (FollowingFragment.OnFollowingPageListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        // mListener = null;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFollowingPageListener {
        // TODO: Update argument type and name
        void onFollowingClick(RecItem item);
    }

    public static class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view,
                                   RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;

            // Add top margin only for the first item to avoid double space between items
            if (parent.getChildPosition(view) == 0)
                outRect.top = space;
        }
    }
}
