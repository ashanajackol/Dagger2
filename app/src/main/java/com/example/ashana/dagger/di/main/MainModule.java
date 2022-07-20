package com.example.ashana.dagger.di.main;

import com.example.ashana.dagger.di.scope.MainScope;
import com.example.ashana.dagger.network.main.MainApi;
import com.example.ashana.dagger.ui.fragment.post.PostsRecyclerAdapter;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MainModule {

    @MainScope
    @Provides
    static MainApi getMainApi(Retrofit retrofit) {
        return retrofit.create(MainApi.class);
    }

    @MainScope
    @Provides
    static PostsRecyclerAdapter providePostRecyclerAdapter() {
        return new PostsRecyclerAdapter();
    }
}
