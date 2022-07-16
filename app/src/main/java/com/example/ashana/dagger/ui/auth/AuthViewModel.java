package com.example.ashana.dagger.ui.auth;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ashana.dagger.SessionManager;
import com.example.ashana.dagger.model.User;
import com.example.ashana.dagger.network.auth.AuthApi;
import com.example.ashana.dagger.util.NetworkResponseUtils;

import javax.inject.Inject;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {

    private static final String TAG = "AuthViewModel";
    private final AuthApi authApi;
    private final MediatorLiveData<NetworkResponseUtils<User>> userDetails = new MediatorLiveData<>();
    //SessionManager sessionManager;

    @Inject
    SessionManager sessionManager; //field injection

    @Inject
    public AuthViewModel(AuthApi api) { //AuthApi, constructor injection
        this.authApi = api;
    }

    public void authenticateWithID(int userId) {
        sessionManager.authinticateWithId(quesryUserId(userId));
    }

    private LiveData<NetworkResponseUtils<User>> quesryUserId(int id) {
        return LiveDataReactiveStreams.fromPublisher(
                authApi.getUser(id)
                        .onErrorReturn(new Function<Throwable, User>() {
                            @Override
                            public User apply(Throwable throwable) throws Exception {
                                User errorUser = new User();
                                errorUser.setId(-1);
                                return errorUser;
                            }
                        })
                        .map(new Function<User, NetworkResponseUtils<User>>() {
                            @Override
                            public NetworkResponseUtils<User> apply(User user) throws Exception {
                                if (user.getId() == -1)
                                    return NetworkResponseUtils.getExceptionResponse(null);
                                else
                                    return NetworkResponseUtils.getSuccessResponse(user);
                            }
                        })
                        .subscribeOn(Schedulers.io())
        );
    }

    public LiveData<NetworkResponseUtils<User>> getUser() {
        return sessionManager.getUser();
    }
}
