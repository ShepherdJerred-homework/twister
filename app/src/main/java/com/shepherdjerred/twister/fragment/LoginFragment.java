package com.shepherdjerred.twister.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.shepherdjerred.twister.R;
import com.shepherdjerred.twister.api.TwisterApi;
import com.shepherdjerred.twister.object.User;

public class LoginFragment extends Fragment {
    private OnFragmentInteractionListener mListener;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_login, container, false);

        final TextView errorTextView = view.findViewById(R.id.error);

        Button button = view.findViewById(R.id.login_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View buttonView) {

                TextInputEditText usernameText = view.findViewById(R.id.username_text);
                TextInputEditText passwordText = view.findViewById(R.id.password_text);
                String username = usernameText.getText().toString();
                String password = passwordText.getText().toString();

                if (username.equals("")) {
                    usernameText.setError("Please enter a username");
                    return;
                }
                usernameText.setError(null);

                if (password.equals("")) {
                    passwordText.setError("Please enter your password");
                    return;
                }
                passwordText.setError(null);

                if (mListener != null) {
                    TwisterApi twisterApi = new TwisterApi(getContext());

                    twisterApi.getUser(username, new TwisterApi.onUserRequestFinish() {
                        @Override
                        public void onSuccess(User user) {
                            if (user != null) {
                                SharedPreferences sharedPreferences = getContext().getSharedPreferences(
                                        "com.shepherdjerred.twister", Context.MODE_PRIVATE);
                                SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
                                sharedPreferencesEditor.putString("username", user.getUsername());
                                sharedPreferencesEditor.apply();
                                mListener.onLogin(user);
                            }
                        }

                        @Override
                        public void onError(String error) {
                            errorTextView.setText(error);
                        }
                    });
                }
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
        void onLogin(User user);
    }
}
