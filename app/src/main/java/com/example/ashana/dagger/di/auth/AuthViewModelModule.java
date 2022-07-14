package com.example.ashana.dagger.di.auth;

import androidx.lifecycle.ViewModel;

import com.example.ashana.dagger.di.ViewModelKeys;
import com.example.ashana.dagger.ui.auth.AuthViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AuthViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKeys(values = AuthViewModel.class)
    public abstract ViewModel bindAuthViewModel(AuthViewModel viewModel);
}
