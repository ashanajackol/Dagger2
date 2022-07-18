package com.example.ashana.dagger.network.main;

import com.example.ashana.dagger.model.Post;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MainApi {

    //posts?userId=3
    @GET("posts")
    Flowable<List<Post>> getPostFromUse (
            @Query("userId") int id
    );
}
