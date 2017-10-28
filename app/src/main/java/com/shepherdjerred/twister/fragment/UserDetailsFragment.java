package com.shepherdjerred.twister.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shepherdjerred.twister.R;
import com.shepherdjerred.twister.object.User;

public class UserDetailsFragment extends Fragment {

    private static final String BUNDLE_ARG_USER = "user";
    private User mUser;
    private OnFragmentInteractionListener mListener;

    public UserDetailsFragment() {
    }

    // TODO: Rename and change types and number of parameters
    public static UserDetailsFragment newInstance(User user) {
        UserDetailsFragment fragment = new UserDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(BUNDLE_ARG_USER, user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mUser = getArguments().getParcelable(BUNDLE_ARG_USER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_details, container, false);

        final ImageView avatarView = view.findViewById(R.id.avatar);
        final TextView usernameView = view.findViewById(R.id.username);
        final TextView aboutView = view.findViewById(R.id.about);

        // TODO set avatar
        //holder.avatarView.setImageURI();
        usernameView.setText(mUser.getUsername());
        aboutView.setText(mUser.getAbout());

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
