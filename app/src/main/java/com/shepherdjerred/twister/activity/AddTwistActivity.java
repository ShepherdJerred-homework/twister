package com.shepherdjerred.twister.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.shepherdjerred.twister.R;
import com.shepherdjerred.twister.fragment.AddTwistFragment;
import com.shepherdjerred.twister.object.Twist;

public class AddTwistActivity extends AppCompatActivity implements AddTwistFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_twist);

        Toolbar myToolbar = findViewById(R.id.appbar_user_detail);
        setSupportActionBar(myToolbar);
        ActionBar actionBar = getSupportActionBar();

        actionBar.setTitle("Add Twist");
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onAddTwist(Twist twist) {

    }
}
