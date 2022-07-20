package com.example.ashana.dagger.ui.fragment.post;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ashana.dagger.R;
import com.example.ashana.dagger.util.VerticalSpacingItemDecoration;
import com.example.ashana.dagger.viewmodel.ViewModelProvidersFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class PostsFragment extends DaggerFragment {

    private static final String TAG = "PostsFragment";
    PostViewModel ViewModel;
    RecyclerView recyclerView;
    ProgressBar progressBar;

    @Inject
    ViewModelProvidersFactory factory;

    @Inject
    PostsRecyclerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post,container, false);
        progressBar = view.findViewById(R.id.progress_bar);
        recyclerView = view.findViewById(R.id.recycler_view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ViewModel = new ViewModelProvider(this, factory).get(PostViewModel.class);
        initRecyclerView();
        subscribeToPosts();
    }

    public void subscribeToPosts() {
        ViewModel.getUserRelatedPosts().removeObservers(getViewLifecycleOwner());
        ViewModel.getUserRelatedPosts().observe(getViewLifecycleOwner(), listNetworkResponseUtils -> {
            if (listNetworkResponseUtils.object != null) {
                switch (listNetworkResponseUtils.status) {
                    case SUCCESS:
                        progressBar.setVisibility(View.GONE);
                        adapter.setPosts(listNetworkResponseUtils.object);
                        break;
                    case LOADING:
                        progressBar.setVisibility(View.VISIBLE);
                        break;
                    case ERROR:
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getContext(),"Something went wrong!", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        VerticalSpacingItemDecoration itemDecoration = new VerticalSpacingItemDecoration(15);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(adapter);
    }
}
