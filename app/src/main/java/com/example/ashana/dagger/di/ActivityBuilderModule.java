package com.example.ashana.dagger.di;

import com.example.ashana.dagger.di.auth.AuthModule;
import com.example.ashana.dagger.di.auth.AuthViewModelModule;
import com.example.ashana.dagger.ui.auth.AuthActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector (modules = {
            AuthViewModelModule.class,
            AuthModule.class})
    abstract AuthActivity contributeAuthActivity();
}
