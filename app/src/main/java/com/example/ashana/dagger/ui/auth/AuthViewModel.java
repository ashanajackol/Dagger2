package com.example.ashana.dagger.ui.auth;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ashana.dagger.model.User;
import com.example.ashana.dagger.network.auth.AuthApi;
import com.example.ashana.dagger.ui.AuthResource;
import com.example.ashana.dagger.util.NetworkResponseUtils;

import javax.inject.Inject;

import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {

    private static final String TAG = "AuthViewModel";
    private final AuthApi authApi;
    private final MediatorLiveData<NetworkResponseUtils<User>> userDetails = new MediatorLiveData<>();

    @Inject
    public AuthViewModel(AuthApi api) {
        this.authApi = api;
    }

    public void authenticateWithID(int userId) {

       userDetails.setValue(NetworkResponseUtils.getLoading(null));

       final LiveData<User> source = LiveDataReactiveStreams.fromPublisher(
               authApi.getUser(userId)
                       .subscribeOn(Schedulers.io())
       );

       userDetails.addSource(source, user -> {
           userDetails.setValue(NetworkResponseUtils.getSuccessResponse(user));
           userDetails.removeSource(source);
       });
    }

    public LiveData<NetworkResponseUtils<User>> getUser() {
        return userDetails;
    }
}
