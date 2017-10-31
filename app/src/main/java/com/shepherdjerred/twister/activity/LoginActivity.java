package com.shepherdjerred.twister.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.shepherdjerred.twister.R;
import com.shepherdjerred.twister.fragment.LoginFragment;
import com.shepherdjerred.twister.object.User;

public class LoginActivity extends AppCompatActivity implements LoginFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void OnLogin(User user) {
        Intent intent = new Intent(this, TwistListActivity.class);
        startActivity(intent);
        finish();
    }
}
