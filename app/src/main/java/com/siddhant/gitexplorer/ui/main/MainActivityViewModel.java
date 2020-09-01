package com.siddhant.gitexplorer.ui.main;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.ViewModel;

import com.siddhant.gitexplorer.api.ApiUtils;
import com.siddhant.gitexplorer.model.Repository;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends ViewModel {

    public final ObservableBoolean isDataAvailable;
    private List<Repository> repositoryList = new ArrayList<>();
    private MainActivityListener listener;

    public MainActivityViewModel(MainActivityListener listener) {
        this.listener = listener;
        isDataAvailable = new ObservableBoolean(false);
    }

    public void initApiForRepository() {
        ApiUtils.getApiCalls().popularRepositories().enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                if (response.isSuccessful()) {
                    isDataAvailable.set(true);
                    repositoryList = response.body();
                    if (repositoryList.size() < 20)
                        listener.setRepositoryList(repositoryList);
                    else
                        listener.setRepositoryList(repositoryList.subList(0, 20));
                } else {
                    isDataAvailable.set(false);
                }
            }

            @Override
            public void onFailure(Call<List<Repository>> call, Throwable t) {
                isDataAvailable.set(false);
            }
        });
        // }
    }


}
