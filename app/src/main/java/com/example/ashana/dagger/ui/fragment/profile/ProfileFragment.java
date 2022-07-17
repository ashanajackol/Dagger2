package com.example.ashana.dagger.ui.fragment.profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.ashana.dagger.R;
import com.example.ashana.dagger.model.User;
import com.example.ashana.dagger.viewmodel.ViewModelProvidersFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class ProfileFragment extends DaggerFragment {

    private static final String TAG = "ProfileFragment";
    ProfileViewModel ViewModel;
    private View view;

    @Inject
    ViewModelProvidersFactory factory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        view = inflater.inflate(R.layout.fragment_profile,container,false);
        ViewModel = new ViewModelProvider(this, factory).get(ProfileViewModel.class);
        getUserDetails();
        return view;
    }

    private  void getUserDetails() {
        ViewModel.getAuthUser().removeObservers(getViewLifecycleOwner()); //must do
        ViewModel.getAuthUser().observe(getViewLifecycleOwner(), userNetworkResponseUtils -> {
            if (userNetworkResponseUtils != null) {
                switch (userNetworkResponseUtils.status) {
                    case SUCCESS:
                        updateUserDetails(userNetworkResponseUtils.object);
                        break;
                    case ERROR:
                        Toast.makeText(getContext(),"Something went wrong!", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    private void updateUserDetails(User user) {
        ((TextView)view.findViewById(R.id.username)).setText(user.getUsername());
        ((TextView)view.findViewById(R.id.email)).setText(user.getEmail());
        ((TextView)view.findViewById(R.id.website)).setText(user.getWebsite());
    }
}
