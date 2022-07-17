package com.example.ashana.dagger.ui.auth;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.constraintlayout.motion.utils.ViewState;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.RequestManager;
import com.example.ashana.dagger.R;
import com.example.ashana.dagger.model.User;
import com.example.ashana.dagger.ui.AuthResource;
import com.example.ashana.dagger.ui.main.MainActivity;
import com.example.ashana.dagger.util.NetworkResponseUtils;
import com.example.ashana.dagger.viewmodel.ViewModelProvidersFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import retrofit2.Retrofit;

public class AuthActivity extends DaggerAppCompatActivity implements View.OnClickListener {

    private static final String TAG = "AuthActivity";
    EditText etUserID;
    ProgressBar progressBar;

    private AuthViewModel ViewModel;

    @Inject
    Retrofit retrofit;

    @Inject
    ViewModelProvidersFactory providersFactory;

    @Inject
    Drawable logo;

    @Inject
    RequestManager requestManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        etUserID = findViewById(R.id.user_id_input);
        findViewById(R.id.login_button).setOnClickListener(this);
        progressBar = findViewById(R.id.progress_bar);

        ViewModel = new ViewModelProvider(this, providersFactory).get(AuthViewModel.class);
        setLogo();
        observingUserDetails();
    }

    private void observingUserDetails() {
        ViewModel.getUser().observe(this, new Observer<NetworkResponseUtils<User>>() {
            @Override
            public void onChanged(NetworkResponseUtils<User> userAuthResource) {
                if (userAuthResource != null) {
                    switch (userAuthResource.status) {
                        case SUCCESS:
                            updateProgressBar(true);
                            navigateToMainActivity();
                            break;
                        case EXCEPTION:
                            updateProgressBar(true);
                            Log.d(TAG, "onChanged: not authenticated");
                            break;
                        case ERROR:
                            updateProgressBar(true);
                            Log.d(TAG, "onChanged: error while loading data");
                            break;
                        case LOADING:
                            updateProgressBar(false);
                            break;
                    }
                }
            }
        });
    }

    private void navigateToMainActivity(){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void updateProgressBar(boolean isShowing) {
        if (isShowing)
            progressBar.setVisibility(View.GONE);
        else
            progressBar.setVisibility(View.VISIBLE);
    }

    private void setLogo() {
        requestManager.load(logo)
                .into((ImageView) findViewById(R.id.login_logo));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_button:{
                getUserDetails();
                break;
            }
        }
    }

    private void getUserDetails() {
        if (TextUtils.isEmpty(etUserID.getText().toString()))
            return;
        ViewModel.authenticateWithID(Integer.parseInt(etUserID.getText().toString()));
    }
}