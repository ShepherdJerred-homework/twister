package com.shepherdjerred.twister.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.shepherdjerred.twister.R;
import com.shepherdjerred.twister.api.TwisterApi;
import com.shepherdjerred.twister.fragment.TwistListFragment;
import com.shepherdjerred.twister.object.Twist;

import java.util.ArrayList;

public class TwistListActivity extends AppCompatActivity implements TwistListFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.list_fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            final TwisterApi twisterApi = new TwisterApi(getApplicationContext());
            twisterApi.getTwists(new TwisterApi.onTwistLoad() {
                @Override
                public void run(ArrayList<Twist> twists) {
                    // Create a new Fragment to be placed in the activity layout
                    TwistListFragment firstFragment = TwistListFragment.newInstance(twists, 1);

                    // Add the fragment to the 'fragment_container' FrameLayout
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.list_fragment_container, firstFragment).commit();
                }
            });


        }
    }

    @Override
    public void onListFragmentClick(Twist twist) {
        Intent intent = new Intent(this, UserDetailsActivity.class);
        intent.putExtra("twist", twist);
        startActivity(intent);
    }
}
