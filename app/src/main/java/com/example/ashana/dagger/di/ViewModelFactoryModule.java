package com.example.ashana.dagger.di;

import androidx.lifecycle.ViewModelProvider;

import com.example.ashana.dagger.viewmodel.ViewModelProvidersFactory;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProvidersFactory factory);

    // this is same as above
//    @Provides
//    static ViewModelProvider.Factory providerFactory(ViewModelProvidersFactory factory) {
//        return factory;
//    }
}
