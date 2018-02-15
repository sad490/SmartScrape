package com.sad490.smartscrape.GridFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sad490.smartscrape.Constant;
import com.sad490.smartscrape.R;
import com.sad490.smartscrape.Recommand.MyItemRecyclerViewAdapter;
import com.sad490.smartscrape.Recommand.RecommandFragment;
import com.sad490.smartscrape.Recommand.dummy.DummyContent;

/**
 * Created by sad490 on 2/14/18.
 */

public class GridFragment extends Fragment {

    private String item_Id = "";

    private static int mColumnCount = 2;

    private OnGridItemClickListener mListener;

    public void GridFragment( OnGridItemClickListener listener ) {
        mListener = listener;
    }

    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        if (getArguments() != null) {
            this.item_Id = getArguments().getString("Item_Id");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gridfragment, container, false);

        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            // TODO : You need adapter !!!
            recyclerView.setAdapter(new GridAdapter(Element.getData(this.item_Id), mListener));
        }

//        header.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(view.getContext(), "Click the header", Toast.LENGTH_SHORT).show();
//            }
//        });
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof GridFragment.OnGridItemClickListener) {
            mListener = (GridFragment.OnGridItemClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    public interface OnGridItemClickListener {
        // TODO: Update argument type and name
        void onGridItemClick(Element.ElementItem item);
    }
}
