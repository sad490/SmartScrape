package com.sad490.smartscrape.Recommand;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.FrameMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sad490.smartscrape.Posters.Posters;
import com.sad490.smartscrape.R;
import com.sad490.smartscrape.Recommand.dummy.DummyContent;

import java.util.ArrayList;
import java.util.List;

import co.lujun.androidtagview.TagContainerLayout;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by sad490 on 2/20/18.
 */

public class StarredFragment extends Fragment {
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private StarredFragment.OnStareedPageListener mListener;
    public static Context context_App;

    private static RecyclerView recyclerView = null;

    public static MultiTypeAdapter adapter;

    public static List<Posters> posters = new ArrayList<>();

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public StarredFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static StarredFragment newInstance( Context _context ) {
        // DummyContent.setContext(_context);
        StarredFragment fragment = new StarredFragment();
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
        adapter.register(Posters.class, new StarredViewBinder(mListener, getContext()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_starred, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        adapter.setItems(posters);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        Log.d("Adapter Init :", "finished");
        Context context = view.getContext();
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
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("OnStart", "Yes");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Resume", "yes");
        adapter.setItems(posters);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof StarredFragment.OnStareedPageListener) {
            mListener = (StarredFragment.OnStareedPageListener) context;
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
    public interface OnStareedPageListener {
        // TODO: Update argument type and name
        void onStarredClick(Posters item);
    }
}
