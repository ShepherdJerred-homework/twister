package com.shepherdjerred.twister.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.shepherdjerred.twister.R;
import com.shepherdjerred.twister.api.TwisterApi;
import com.shepherdjerred.twister.fragment.TwistListFragment;
import com.shepherdjerred.twister.fragment.UserDetailsFragment;
import com.shepherdjerred.twister.object.Twist;
import com.shepherdjerred.twister.object.User;

import java.util.ArrayList;

public class UserDetailsActivity extends AppCompatActivity implements TwistListFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        Intent intent = getIntent();
        Twist twist = null;

        if (intent.getParcelableExtra("twist") != null) {
            twist = intent.getParcelableExtra("twist");
        } else {
            throw new RuntimeException("No twist extra");
        }

        Toolbar myToolbar = findViewById(R.id.appbar_user_detail);
        setSupportActionBar(myToolbar);
        ActionBar actionBar = getSupportActionBar();

        actionBar.setTitle(twist.getUsername());
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.user_detail_fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            final TwisterApi twisterApi = new TwisterApi(getApplicationContext());
            twisterApi.getUser(twist.getUsername(), new TwisterApi.onUserRequestFinish() {
                @Override
                public void onSuccess(User user) {
                    Log.d("GetUser", user.toString());
                    // Create a new Fragment to be placed in the activity layout
                    UserDetailsFragment firstFragment = UserDetailsFragment.newInstance(user);

                    // Add the fragment to the 'fragment_container' FrameLayout
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.user_detail_fragment_container, firstFragment).commit();
                }

                @Override
                public void onError(String error) {

                }
            });

            twisterApi.getTwists(twist.getUsername(), new TwisterApi.onTwistRequestFinish() {
                @Override
                public void onSuccess(ArrayList<Twist> twists) {
                    // Create a new Fragment to be placed in the activity layout
                    TwistListFragment secondFragment = TwistListFragment.newInstance(twists, 1);

                    // Add the fragment to the 'fragment_container' FrameLayout
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.user_detail_twist_fragment_container, secondFragment).commit();
                }
            });

        }
    }

    @Override
    public void onListFragmentClick(Twist twist) {

    }
}
