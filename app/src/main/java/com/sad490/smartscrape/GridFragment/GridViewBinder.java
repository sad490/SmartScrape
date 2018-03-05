package com.sad490.smartscrape.GridFragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sad490.smartscrape.R;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by sad490 on 2/24/18.
 */
public class GridViewBinder extends ItemViewBinder<Grid, GridViewBinder.ViewHolder> {

    public static GridFragment.OnGridItemClickListener mListener;

    public GridViewBinder(GridFragment.OnGridItemClickListener _mListener) {
        mListener = _mListener;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.fragment_gridfragment_item, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull final Grid grid) {
        holder.mHeaderView.setText(grid.getArticle().getTitle());
        holder.mContentView.setText(grid.getArticle().getDate());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    Log.d("GridClick", "Clicked");
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onGridItemClick(grid);
                }
            }
        });
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mHeaderView;
        public final TextView mContentView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mHeaderView = (TextView) view.findViewById(R.id.header);
            mContentView = (TextView) view.findViewById(R.id.detail);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mHeaderView.getText().toString() + "'";
        }
    }
}
