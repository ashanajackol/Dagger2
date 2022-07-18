package com.example.ashana.dagger.di.main;

import androidx.lifecycle.ViewModel;

import com.example.ashana.dagger.di.ViewModelKeys;
import com.example.ashana.dagger.ui.fragment.post.PostViewModel;
import com.example.ashana.dagger.ui.fragment.profile.ProfileViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModleModule {

    @Binds
    @IntoMap
    @ViewModelKeys(values = ProfileViewModel.class)
    public abstract ViewModel bindProfileViewModel(ProfileViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKeys(values = PostViewModel.class)
    public abstract ViewModel bindPostViewModel(PostViewModel viewModel);
}
