package com.example.ashana.dagger.network.auth;


import com.example.ashana.dagger.model.User;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AuthApi {

    @GET ("/users/{id}")
    Flowable<User> getUser (
            @Path("id") int id
    );
}
