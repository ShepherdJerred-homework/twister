package com.shepherdjerred.twister.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.shepherdjerred.twister.R;
import com.shepherdjerred.twister.api.TwisterApi;
import com.shepherdjerred.twister.fragment.TwistListFragment;
import com.shepherdjerred.twister.object.Twist;

import java.util.ArrayList;

public class TwistListActivity extends AppCompatActivity implements TwistListFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twist_list);

        Toolbar myToolbar = findViewById(R.id.appbar);
        setSupportActionBar(myToolbar);

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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.twist_list, menu);
        return true;
    }

    // https://developer.android.com/training/appbar/actions.html
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_twist:
                Intent addTwistIntent = new Intent(this, AddTwistActivity.class);
                startActivity(addTwistIntent);
                return true;

            case R.id.search:
                // TODO
                return true;

            case R.id.logout:
                // TODO reset login preference
                Intent loginIntent = new Intent(this, LoginActivity.class);
                startActivity(loginIntent);
                return true;

            // https://developer.android.com/guide/topics/ui/dialogs.html#AlertDialog
            case R.id.about:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("App by Jerred Shepherd")
                        .setTitle("About Twister");
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onListFragmentClick(Twist twist) {
        Intent intent = new Intent(this, UserDetailsActivity.class);
        intent.putExtra("twist", twist);
        startActivity(intent);
    }
}
