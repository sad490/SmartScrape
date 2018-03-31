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
import com.sad490.smartscrape.Posters.PostersFragment;
import com.sad490.smartscrape.Posters.PostersViewBinder;
import com.sad490.smartscrape.R;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by hp on 2018/3/5.
 */

public class StarredViewBinder extends ItemViewBinder<Posters, StarredViewBinder.ViewHolder> {
    private static StarredFragment.OnStareedPageListener mListener = null;
    private Context context = null;

    public StarredViewBinder(StarredFragment.OnStareedPageListener listener, Context _context) {
        mListener = listener;
        context = _context;
    }

    @NonNull
    @Override
    protected StarredViewBinder.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.fragment_posters, parent, false);
        return new StarredViewBinder.ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull StarredViewBinder.ViewHolder holder, @NonNull final Posters posters) {
        holder.mIdView.setText(posters.getName());
        // holder.mContentView.setImageBitmap(posters.getHeader());
        Glide.with(context).load(posters.getImage_url()).into(holder.mContentView);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onStarredClick(posters);
            }
        });
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final ImageView mContentView;
        public Posters mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (ImageView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '"  + "'";
        }
    }
}
