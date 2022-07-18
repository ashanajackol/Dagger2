package com.example.ashana.dagger.ui.fragment.post;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.ashana.dagger.SessionManager;
import com.example.ashana.dagger.network.main.MainApi;

import javax.inject.Inject;

public class PostViewModel extends ViewModel {

    private static final String TAG = "PostViewModel";
    public SessionManager sessionManager;
    private MainApi mainApi;

    @Inject
    public PostViewModel(SessionManager sessionManager, MainApi mainApi) {
        this.sessionManager = sessionManager;
        this.mainApi = mainApi;
        Log.d(TAG, "PostViewModel: View model is working");
    }
}
