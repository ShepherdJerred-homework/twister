package com.shepherdjerred.twister.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.shepherdjerred.twister.R;
import com.shepherdjerred.twister.fragment.TwistFragment;
import com.shepherdjerred.twister.object.Twist;

public class ListActivity extends AppCompatActivity implements TwistFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
    }

    @Override
    public void onListFragmentInteraction(Twist twist) {

    }
}
