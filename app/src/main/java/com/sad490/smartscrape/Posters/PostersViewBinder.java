package com.sad490.smartscrape.Posters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.sad490.smartscrape.Posters.dummy.DummyContent;
import com.sad490.smartscrape.R;
import com.sad490.smartscrape.Recommand.StarredFragment;

import javax.sql.DataSource;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by sad490 on 3/4/18.
 */
public class PostersViewBinder extends ItemViewBinder<Posters, PostersViewBinder.ViewHolder> {

    private static PostersFragment.OnPostersListener mListener = null;
    private Context context = null;

    public PostersViewBinder(PostersFragment.OnPostersListener listener, Context _context) {
        mListener = listener;
        context = _context;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.fragment_posters, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull final Posters posters) {
        holder.mIdView.setText(posters.getName());
        // holder.mContentView.setImageBitmap(posters.getHeader());
        Glide.with(context).load(posters.getImage_url()).into(holder.mContentView);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onPosterClick(posters);
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
