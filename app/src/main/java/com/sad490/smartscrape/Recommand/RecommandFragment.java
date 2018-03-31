package com.sad490.smartscrape.Recommand;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sad490.smartscrape.NetWork.Tag;
import com.sad490.smartscrape.R;
import com.sad490.smartscrape.Recommand.dummy.DummyContent;
import com.sad490.smartscrape.Recommand.dummy.DummyContent.DummyItem;
import com.sad490.smartscrape.UserInfo.UserFragment;

import java.util.ArrayList;
import java.util.List;

import co.lujun.androidtagview.TagContainerLayout;
import co.lujun.androidtagview.TagView;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnRecommandPageListener}
 * interface.
 */
public class RecommandFragment extends Fragment  {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnRecommandPageListener mListener;
    public static Context context_App;

    public static MultiTypeAdapter adapter;
    private static TagContainerLayout mTagContainerLayout = null;
    private static List<String> Tags = new ArrayList<>();

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RecommandFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static RecommandFragment newInstance( Context _context ) {
        // DummyContent.setContext(_context);
        RecommandFragment fragment = new RecommandFragment();
        Bundle args = new Bundle();
        context_App = _context;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new MultiTypeAdapter();
        adapter.register(RecItem.class, new RecItemViewBinder(mListener));

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    // Note : Every time display (Not Replay) this fragment, this function will be called .
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
        recyclerView.addItemDecoration(new SpacesItemDecoration(8));
        Context context = view.getContext();
        // recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        // recyclerView.setAdapter(new MyItemRecyclerViewAdapter(DummyContent.ITEMS, mListener));

        recyclerView.setAdapter(adapter);

        mTagContainerLayout = (TagContainerLayout) view.findViewById(R.id.tagview);

        if (Tags.size() > 0) {
            mTagContainerLayout.setTags(Tags);
        }

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
        return view;
    }

    public static void setTags( List<String> tags ) {
        // Tags = tags;
        Tags.clear();
        for (String tag : tags) {
            Tags.add(tag);
        }
        mTagContainerLayout.setTags(Tags);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnRecommandPageListener) {
            mListener = (OnRecommandPageListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
    public interface OnRecommandPageListener {
        // TODO: Update argument type and name
        void onRecommandClick(RecItem item);
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
