package com.sad490.smartscrape;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sad490.smartscrape.dummy.DummyContent;
import com.sad490.smartscrape.dummy.DummyContent.DummyItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class StatisticFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    Integer[] imageIDs = {
            R.drawable.com_facebook_auth_dialog_background,
            R.drawable.com_facebook_auth_dialog_header_background,
            R.drawable.com_facebook_button_icon_white,
            R.drawable.button_effect,
            R.drawable.com_facebook_button_like_background,
            R.drawable.com_facebook_button_like_icon_selected,
            R.drawable.com_facebook_button_send_icon_blue
    };

    String[] plants = new String[]{
            "Catalina ironwood",
            "Cabinet cherry",
            "Pale corydalis",
            "Pink corydalis",
            "Land cress",
            "Coast polypody",
            "Water fern"
    };
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public StatisticFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static StatisticFragment newInstance() {
        StatisticFragment fragment = new StatisticFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, 1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list2, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyItemRecyclerViewAdapter2(DummyContent.ITEMS, mListener));
        }
        else if ( view instanceof GridView) {
            Context context = view.getContext();
            GridView gridView = (GridView)view.findViewById(R.id.group);

            // Populate a List from Array elements
            final List<String> plantsList = new ArrayList<String>(Arrays.asList(plants));
            // Create a new ArrayAdapter
            final ArrayAdapter<String> gridViewArrayAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, plantsList);
            // gridView.setAdapter(gridViewArrayAdapter);
            gridView.setAdapter(new ImageAdapter(view.getContext()));
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
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
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }

    public class ImageAdapter extends BaseAdapter
    {
        private Context context;

        public ImageAdapter(Context c)
        {
            context = c;
        }

        //---returns the number of images---
        public int getCount() {
            return imageIDs.length;
        }

        //---returns the ID of an item---
        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        //---returns an ImageView view---
        public View getView(int position, View convertView, ViewGroup parent)
        {
            //TODO Here have Bug !!!
            ImageView imageView;
            TextView textView;
            LayoutInflater inflater = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View grid = new View(getContext());
            if (convertView == null) {
                grid = inflater.inflate(R.layout.grid_single, null);

                imageView = grid.findViewById(R.id.pic);
                textView = grid.findViewById(R.id.name);
                imageView.setLayoutParams(new LinearLayout.LayoutParams(185, 185));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(5, 5, 5, 5);

                imageView.setImageResource(imageIDs[position]);
                textView.setText(plants[position]);
            } else {
                grid = (View) convertView;
            }

            return grid;
        }
    }
}
