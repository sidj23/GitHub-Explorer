package com.siddhant.gitexplorer.ui.contributorrepo;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.siddhant.gitexplorer.api.ApiUtils;
import com.siddhant.gitexplorer.model.Owner;
import com.siddhant.gitexplorer.model.Repository;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContributorRepositoryViewModel extends ViewModel {

    public final ObservableField<String> textName;
    public final ObservableField<String> imageProfile;
    public final ObservableBoolean isDataAvailable;
    ContributorRepositoryActivityListener listener;
    private List<Repository> repositoryList = new ArrayList<>();

    public ContributorRepositoryViewModel(ContributorRepositoryActivityListener listener) {
        this.listener = listener;
        textName = new ObservableField<>("");
        imageProfile = new ObservableField<>("");
        isDataAvailable = new ObservableBoolean(false);

    }

    public void initApiForRepository(Owner owner) {
        if (owner != null) {
            textName.set(owner.getLogin());
            imageProfile.set(owner.getAvatarUrl());
        }

        ApiUtils.getApiCalls().contributorsRepo(owner.getLogin()).enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                if (response.isSuccessful()) {
                    repositoryList = response.body();
                    listener.setRepositoryList(repositoryList);
                    isDataAvailable.set(true);
                } else {
                    isDataAvailable.set(false);
                }
            }

            @Override
            public void onFailure(Call<List<Repository>> call, Throwable t) {
                isDataAvailable.set(false);
            }
        });
    }
}
