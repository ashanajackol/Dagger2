package com.example.ashana.dagger;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.ashana.dagger.ui.auth.AuthActivity;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseAvtivity extends DaggerAppCompatActivity {

    private static final String TAG = "BaseAvtivity";

    @Inject
    public SessionManager sessionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getObservers();
    }

    public void getObservers(){
        sessionManager.getUser().observe(this, userNetworkResponseUtils -> {
            switch (userNetworkResponseUtils.status) {
                case SUCCESS:
                    break;
                case ERROR:
                    break;
                case LOGOUT:
                    logoutUser();
                    break;
                case LOADING:
                    break;
                case EXCEPTION:
                    break;
            }
        });
    }

    public void logoutUser(){
        Log.d(TAG, "logoutUser: ");
        startActivity(new Intent(this, AuthActivity.class));
        finish();
    }
}
