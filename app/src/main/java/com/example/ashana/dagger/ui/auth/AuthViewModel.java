package com.example.ashana.dagger.ui.auth;

import android.util.Log;
import androidx.lifecycle.ViewModel;

import com.example.ashana.dagger.network.auth.AuthApi;

import javax.inject.Inject;

public class AuthViewModel extends ViewModel {

    private AuthApi authApi;

    @Inject
    public AuthViewModel(AuthApi api) {
        this.authApi = api;
        Log.d("AuthViewModel", "View model is working");
    }
}
