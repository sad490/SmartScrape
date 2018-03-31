package com.sad490.smartscrape.Recommand;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sad490.smartscrape.Posters.Posters;
import com.sad490.smartscrape.R;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by hp on 2018/3/6.
 */

public class FollowingViewBinder extends ItemViewBinder<RecItem, FollowingViewBinder.ViewHolder> {

    private static FollowingFragment.OnFollowingPageListener mListener = null;
    private Context context = null;

    public FollowingViewBinder(FollowingFragment.OnFollowingPageListener listener, Context _context) {
        mListener = listener;
        context = _context;
    }

    @NonNull
    @Override
    protected FollowingViewBinder.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.fragment_item, parent, false);
        return new FollowingViewBinder.ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull FollowingViewBinder.ViewHolder holder, @NonNull final RecItem recItem) {
        holder.mIdView.setText(recItem.article.getTitle());
        holder.mContentView.setText(recItem.article.getPublisher());
        holder.mTime.setText(recItem.article.getDate());
        // holder.mContentView.setImageBitmap(posters.getHeader());
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onFollowingClick(recItem);
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
