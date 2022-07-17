package com.example.ashana.dagger.ui.fragment.profile;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.ashana.dagger.SessionManager;
import com.example.ashana.dagger.model.User;
import com.example.ashana.dagger.util.NetworkResponseUtils;

import javax.inject.Inject;

public class ProfileViewModel extends ViewModel {

    private static final String TAG = "ProfileViewModel";
    public SessionManager sessionManager;

    @Inject
    public ProfileViewModel(SessionManager manager) {
        this.sessionManager = manager;
    }

    public LiveData<NetworkResponseUtils<User>> getAuthUser() {
        return sessionManager.getUser();
    }
}
