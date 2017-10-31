package com.shepherdjerred.twister.fragment;

import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.shepherdjerred.twister.R;
import com.shepherdjerred.twister.fragment.TwistListFragment.OnListFragmentInteractionListener;
import com.shepherdjerred.twister.object.Twist;

import java.util.List;

// https://stackoverflow.com/questions/31964737/glide-image-loading-with-application-context/32887693#32887693
public class TwistListFragmentRecyclerViewAdapter extends RecyclerView.Adapter<TwistListFragmentRecyclerViewAdapter.ViewHolder> {

    private final RequestManager glide;
    private final List<Twist> mTwists;
    private final OnListFragmentInteractionListener mListener;

    public TwistListFragmentRecyclerViewAdapter(List<Twist> twists, OnListFragmentInteractionListener listener, RequestManager glide) {
        mTwists = twists;
        mListener = listener;
        this.glide = glide;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_twist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mTwists.get(position);
        String avatarUrl = "http://cs.harding.edu/fmccown/twister/images/" + mTwists.get(position).getUsername() + ".jpg";
        glide.load(avatarUrl).into(holder.mAvatarView);
        holder.mUsernameView.setText(mTwists.get(position).getUsername());
        holder.mMessageView.setText(mTwists.get(position).getMessage());
        holder.mTimestampView.setText(DateUtils.getRelativeTimeSpanString(mTwists.get(position).getTimestamp().getTime()));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentClick(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTwists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mAvatarView;
        public final TextView mUsernameView;
        public final TextView mMessageView;
        public final TextView mTimestampView;
        public Twist mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mAvatarView = view.findViewById(R.id.avatar);
            mUsernameView = view.findViewById(R.id.username);
            mMessageView = view.findViewById(R.id.message);
            mTimestampView = view.findViewById(R.id.timestamp);
        }
    }
}
