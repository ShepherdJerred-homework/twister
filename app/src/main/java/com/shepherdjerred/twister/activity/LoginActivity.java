package com.shepherdjerred.twister.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.shepherdjerred.twister.R;
import com.shepherdjerred.twister.fragment.LoginFragment;
import com.shepherdjerred.twister.object.User;

public class LoginActivity extends AppCompatActivity implements LoginFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getSharedPreferences(
                "com.shepherdjerred.twister", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username",null);
        if (username != null) {
            goToTwistList();
        }

        setContentView(R.layout.activity_login);
    }

    @Override
    public void onLogin(User user) {
        goToTwistList();
    }

    private void goToTwistList() {
        Intent intent = new Intent(this, TwistListActivity.class);
        startActivity(intent);
        finish();
    }
}
