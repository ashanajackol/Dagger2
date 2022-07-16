package com.example.ashana.dagger;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.ashana.dagger.ui.auth.AuthActivity;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseAvtivity extends DaggerAppCompatActivity {

    @Inject
    SessionManager sessionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void getObservers(){
        sessionManager.getUser().observe(this, userNetworkResponseUtils -> {
            switch (userNetworkResponseUtils.status) {
                case SUCCESS:
                    break;
                case ERROR:
                    break;
                case LOGOUT:
                    navigateToLogin();
                    break;
                case LOADING:
                    break;
                case EXCEPTION:
                    break;
            }
        });
    }

    public void navigateToLogin(){
        startActivity(new Intent(this, AuthActivity.class));
        finish();
    }
}
