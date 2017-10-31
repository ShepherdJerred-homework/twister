package com.shepherdjerred.twister.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.shepherdjerred.twister.R;
import com.shepherdjerred.twister.fragment.LoginFragment;

public class LoginActivity extends AppCompatActivity implements LoginFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void OnLoginButtonClick(String username) {
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }
}
