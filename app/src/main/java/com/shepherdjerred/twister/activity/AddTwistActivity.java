package com.shepherdjerred.twister.activity;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shepherdjerred.twister.R;
import com.shepherdjerred.twister.fragment.AddTwistFragment;

public class AddTwistActivity extends AppCompatActivity implements AddTwistFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_twist);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
