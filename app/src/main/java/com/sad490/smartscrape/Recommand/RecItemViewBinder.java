package com.sad490.smartscrape.Recommand;

import android.support.annotation.NonNull;
import android.support.design.widget.TabItem;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sad490.smartscrape.R;
import com.sad490.smartscrape.Recommand.dummy.DummyContent;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by sad490 on 2/19/18.
 */
public class RecItemViewBinder extends ItemViewBinder<RecItem, RecItemViewBinder.ViewHolder> {

    private static RecommandFragment.OnRecommandPageListener mListener;

    public RecItemViewBinder( RecommandFragment.OnRecommandPageListener _mListener ) {
        mListener = _mListener;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull final ViewHolder holder, @NonNull final RecItem recItem) {
        holder.mIdView.setText(recItem.article.getTitle());
        holder.mContentView.setText(recItem.article.getPublisher());
        holder.mTime.setText(recItem.article.getDate());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onRecommandClick(recItem);
                }
            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final TextView mTime;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
            mTime = (TextView)view.findViewById(R.id.label);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mIdView.getText().toString() + "'";
        }
    }
}
