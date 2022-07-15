package com.example.ashana.dagger.ui.auth;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.ashana.dagger.model.User;
import com.example.ashana.dagger.network.auth.AuthApi;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {

    private static final String TAG = "AuthViewModel";
    private AuthApi authApi;
    private MediatorLiveData<User> _user = new MediatorLiveData<>();

    @Inject
    public AuthViewModel(AuthApi api) {
        this.authApi = api;

    }

    public void authenticateWithID(int userId) {
       final LiveData<User> source = LiveDataReactiveStreams.fromPublisher(
               authApi.getUser(userId)
                       .subscribeOn(Schedulers.io())
       );

       _user.addSource(source, user -> {
           _user.setValue(user);
           _user.removeSource(source);
       });
    }

    public LiveData<User> getUser() {
        return _user;
    }
}
