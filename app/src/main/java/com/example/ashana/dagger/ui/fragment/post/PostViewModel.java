package com.example.ashana.dagger.ui.fragment.post;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.ashana.dagger.SessionManager;
import com.example.ashana.dagger.model.Post;
import com.example.ashana.dagger.network.main.MainApi;
import com.example.ashana.dagger.util.NetworkResponseUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class PostViewModel extends ViewModel {

    private static final String TAG = "PostViewModel";
    public SessionManager sessionManager;
    private MainApi mainApi;
    private MediatorLiveData<NetworkResponseUtils<List<Post>>> posts;

    @Inject
    public PostViewModel(SessionManager sessionManager, MainApi mainApi) {
        this.sessionManager = sessionManager;
        this.mainApi = mainApi;
        Log.d(TAG, "PostViewModel: View model is working");
    }

    public LiveData<NetworkResponseUtils<List<Post>>> getUserRelatedPosts() {
        if (posts == null) {
            posts = new MediatorLiveData<>();
            posts.setValue(NetworkResponseUtils.getLoading(null));

            LiveData<NetworkResponseUtils<List<Post>>> source = LiveDataReactiveStreams.fromPublisher(
                    mainApi.getPostFromUse(Objects.requireNonNull(sessionManager.getUser().getValue()).object.getId())
                            .onErrorReturn(new Function<Throwable, List<Post>>() {
                                @Override
                                public List<Post> apply(Throwable throwable) throws Exception {
                                    Post errorPost = new Post();
                                    errorPost.setId(-1);
                                    ArrayList<Post> listPost = new ArrayList<>();
                                    listPost.add(errorPost);
                                    return listPost;
                                }
                            })
                            .map(new Function<List<Post>, NetworkResponseUtils<List<Post>>>() {
                                @Override
                                public NetworkResponseUtils<List<Post>> apply(List<Post> posts) throws Exception {
                                    if (posts.size() > 0) {
                                        if (posts.get(0).getId() == -1) {
                                            return NetworkResponseUtils.getErrorResponse(null);
                                        }
                                    }
                                    return NetworkResponseUtils.getSuccessResponse(posts);
                                }
                            })
                            .subscribeOn(Schedulers.io())
            );
            posts.addSource(source, new Observer<NetworkResponseUtils<List<Post>>>() {
                @Override
                public void onChanged(NetworkResponseUtils<List<Post>> listNetworkResponseUtils) {
                    posts.setValue(listNetworkResponseUtils);
                    posts.removeSource(source);
                }
            });
        }
        return posts;
    }

}
