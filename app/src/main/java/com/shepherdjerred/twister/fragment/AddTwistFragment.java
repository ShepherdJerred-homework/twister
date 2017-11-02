package com.shepherdjerred.twister.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.shepherdjerred.twister.R;
import com.shepherdjerred.twister.api.TwisterApi;
import com.shepherdjerred.twister.object.Twist;

import java.util.Calendar;

public class AddTwistFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public AddTwistFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_twist, container, false);

        TextView characterCountTextView = view.findViewById(R.id.add_twist_char_count);
        final EditText twistMessageTextView = view.findViewById(R.id.add_twist_text);
        Button addTwistButton = view.findViewById(R.id.add_twist_button);

        addTwistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (twistMessageTextView.getText().length() <= 150) {
                    SharedPreferences sharedPreferences = getContext().getSharedPreferences(
                            "com.shepherdjerred.twister", Context.MODE_PRIVATE);

                    String username = sharedPreferences.getString("username", null);

                    // ID is hardcoded to 0, API would normally take care of this
                    new TwisterApi(getContext()).addTwist(new Twist(
                            0,
                            username,
                            twistMessageTextView.getText().toString(),
                            Calendar.getInstance().getTime()
                    ));
                    mListener.onAddTwist();
                }
            }
        });

        twistMessageTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                twistMessageTextView.setText(charSequence.length() + "/150");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

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
        void onAddTwist();
    }
}
