package com.shepherdjerred.twister.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.shepherdjerred.twister.R;
import com.shepherdjerred.twister.api.TwisterApi;
import com.shepherdjerred.twister.fragment.UserDetailsFragment;
import com.shepherdjerred.twister.object.Twist;
import com.shepherdjerred.twister.object.User;

public class UserDetailsActivity extends AppCompatActivity implements UserDetailsFragment.OnFragmentInteractionListener {

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
        if (findViewById(R.id.user_details_fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            final TwisterApi twisterApi = new TwisterApi(getApplicationContext());
            twisterApi.getUser(twist.getUsername(), new TwisterApi.onUserLoad() {
                @Override
                public void onSuccess(User user) {
                    Log.d("GetUser", user.toString());
                    // Create a new Fragment to be placed in the activity layout
                    UserDetailsFragment firstFragment = UserDetailsFragment.newInstance(user);

                    // Add the fragment to the 'fragment_container' FrameLayout
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.user_details_fragment_container, firstFragment).commit();
                }

                @Override
                public void onError(String error) {

                }
            });

        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
