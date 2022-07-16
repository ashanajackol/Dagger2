package com.example.ashana.dagger;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.example.ashana.dagger.model.User;
import com.example.ashana.dagger.util.NetworkResponseUtils;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SessionManager {

    private static final String TAG = "SessionManager";
    private MediatorLiveData<NetworkResponseUtils<User>> cacheUser = new MediatorLiveData<>();

    @Inject
    public SessionManager(){}

    public void authinticateWithId(final LiveData<NetworkResponseUtils<User>> source) {
        if (cacheUser != null) {
            cacheUser.setValue(NetworkResponseUtils.getLoading(null));
            cacheUser.addSource(source, new Observer<NetworkResponseUtils<User>>() {
                @Override
                public void onChanged(NetworkResponseUtils<User> userNetworkResponseUtils) {
                    cacheUser.setValue(userNetworkResponseUtils);
                    cacheUser.removeSource(source);
                }
            });
        }
    }

    public void logout(){
        Log.d(TAG, "logout: ");
        cacheUser.setValue(NetworkResponseUtils.logout(null));
    }

    public LiveData<NetworkResponseUtils<User>> getUser() {
        return cacheUser;
    }

}
