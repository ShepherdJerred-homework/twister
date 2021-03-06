package com.shepherdjerred.twister.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.shepherdjerred.twister.R;
import com.shepherdjerred.twister.object.Twist;

import java.util.ArrayList;

public class TwistListFragment extends Fragment {

    private static final String BUNDLE_ARG_COLUMN_COUNT = "column-count";
    private static final String BUNDLE_ARG_TWIST_ARRAY = "twists";
    private int mColumnCount = 1;
    private ArrayList<Twist> mTwists;
    private OnListFragmentInteractionListener mListener;

    public TwistListFragment() {
        mTwists = new ArrayList<>();
    }

    public static TwistListFragment newInstance(ArrayList<Twist> twists, int columnCount) {
        Log.d("Instance", twists.toString());
        TwistListFragment fragment = new TwistListFragment();
        Bundle args = new Bundle();
        args.putInt(BUNDLE_ARG_COLUMN_COUNT, columnCount);
        args.putParcelableArrayList(BUNDLE_ARG_TWIST_ARRAY, twists);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(BUNDLE_ARG_COLUMN_COUNT);
            mTwists = getArguments().getParcelableArrayList(BUNDLE_ARG_TWIST_ARRAY);
            Log.d("OnCreate", mTwists.toString());
        } else {
            Log.d("OnCreate", "Empty");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_twist_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            recyclerView.setAdapter(new TwistListFragmentRecyclerViewAdapter(mTwists, mListener, Glide.with(this)));
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentClick(Twist twist);
    }
}
