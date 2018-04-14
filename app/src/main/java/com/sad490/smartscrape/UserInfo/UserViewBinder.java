package com.sad490.smartscrape.UserInfo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sad490.smartscrape.R;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by sad490 on 3/24/18.
 */
public class UserViewBinder extends ItemViewBinder<User, UserViewBinder.ViewHolder> {

    private static Context context = null ;
    private static UserFragment.OnUserFragmentListener mListener = null;

    public UserViewBinder( Context _context, UserFragment.OnUserFragmentListener _Listener ) {
        mListener = _Listener;
        context = _context;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.fragment_user_item, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull final User user) {
        holder.mIdView.setText(user.getName());
        // Glide.with(context).load(user.getHeader_url()).into(holder.mContentView);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onUserFragmentInteraction(user);
            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final ImageView mContentView;
        public User mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.name_for_user);
            mContentView = (ImageView) view.findViewById(R.id.header);
        }

        @Override
        public String toString() {
            return super.toString() + " '"  + "'";
        }
    }
}
