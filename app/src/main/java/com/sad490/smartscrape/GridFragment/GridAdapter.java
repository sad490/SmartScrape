package com.sad490.smartscrape.GridFragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sad490.smartscrape.R;
import com.sad490.smartscrape.Recommand.MyItemRecyclerViewAdapter;
import com.sad490.smartscrape.Recommand.dummy.DummyContent;

import java.util.List;

/**
 * Created by sad490 on 2/15/18.
 */

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {

    public List<Element.ElementItem> mValues;
    private GridFragment.OnGridItemClickListener mListener;

    public GridAdapter(List<Element.ElementItem> items, GridFragment.OnGridItemClickListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public GridAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_gridfragment_item, parent, false);
        return new GridAdapter.ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    @Override
    public void onBindViewHolder(final GridAdapter.ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        // holder.mContentView.setImageBitmap(mValues.get(position).content);
        holder.detailView.setText(mValues.get(position).details);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    // mListener.onGridItemClick(holder.mItem);
                }
            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView detailView;
        public Element.ElementItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.header);
            detailView = (TextView)view.findViewById(R.id.detail);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mIdView.getText().toString() + "'";
        }
    }
}
