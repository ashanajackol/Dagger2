package com.example.ashana.dagger.di.main;

import com.example.ashana.dagger.network.main.MainApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MainModule {

    @Provides
    static MainApi getMainApi(Retrofit retrofit) {
        return retrofit.create(MainApi.class);
    }
}
