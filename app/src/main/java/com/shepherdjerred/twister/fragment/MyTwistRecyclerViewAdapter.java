package com.shepherdjerred.twister.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shepherdjerred.twister.R;
import com.shepherdjerred.twister.fragment.TwistFragment.OnListFragmentInteractionListener;
import com.shepherdjerred.twister.object.Twist;

import java.util.List;

public class MyTwistRecyclerViewAdapter extends RecyclerView.Adapter<MyTwistRecyclerViewAdapter.ViewHolder> {

    private final List<Twist> mTwists;
    private final OnListFragmentInteractionListener mListener;

    public MyTwistRecyclerViewAdapter(List<Twist> twists, OnListFragmentInteractionListener listener) {
        mTwists = twists;
        mListener = listener;
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
        // TODO set avatar
        //holder.mAvatarView.setImageURI();
        holder.mUsernameView.setText(mTwists.get(position).getUsername());
        holder.mMessageView.setText(mTwists.get(position).getMessage());
        holder.mTimestampView.setText(mTwists.get(position).getTimestamp().toString());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentInteraction(holder.mItem);
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
