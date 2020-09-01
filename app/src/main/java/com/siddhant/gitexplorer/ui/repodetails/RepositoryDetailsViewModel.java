package com.siddhant.gitexplorer.ui.repodetails;

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

public class RepositoryDetailsViewModel extends ViewModel {


    public final ObservableField<String> imageProfile;
    public final ObservableField<String> textName;
    public final ObservableField<String> textFullName;
    public final ObservableField<String> textDescription;

    public final ObservableBoolean isDataAvailable;

    private RepositoryDetailsActivityListener listener;

    private List<Owner> ownerList = new ArrayList<>();

    public RepositoryDetailsViewModel(RepositoryDetailsActivityListener listener) {
        imageProfile = new ObservableField<>("");
        textName = new ObservableField<>("");
        textFullName = new ObservableField<>("");
        textDescription = new ObservableField<>("");
        isDataAvailable = new ObservableBoolean(false);
        this.listener = listener;
    }

    public void initApiForContributors(Repository repository) {
        if (repository != null) {
            textName.set(repository.getName());
            textFullName.set(repository.getFullName());
            imageProfile.set(repository.getOwner().getAvatarUrl());
            textDescription.set(repository.getDescription());
        }

        ApiUtils.getApiCalls().repoContributors(repository.getOwner().getLogin(), repository.getName()).enqueue(new Callback<List<Owner>>() {
            @Override
            public void onResponse(Call<List<Owner>> call, Response<List<Owner>> response) {
                if (response.isSuccessful()) {
                    ownerList = response.body();
                    listener.setOwnerList(ownerList);
                    isDataAvailable.set(true);
                } else {
                    isDataAvailable.set(false);
                }
            }

            @Override
            public void onFailure(Call<List<Owner>> call, Throwable t) {
                isDataAvailable.set(false);
            }
        });
    }
}
