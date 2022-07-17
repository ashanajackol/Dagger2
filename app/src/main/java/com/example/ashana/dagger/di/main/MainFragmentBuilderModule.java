package com.example.ashana.dagger.di.main;

import com.example.ashana.dagger.ui.fragment.profile.ProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBuilderModule {

    @ContributesAndroidInjector ()
    abstract ProfileFragment contributeProfileFragment();
}
