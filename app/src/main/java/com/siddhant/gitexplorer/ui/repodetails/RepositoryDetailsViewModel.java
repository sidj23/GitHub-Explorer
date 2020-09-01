package com.siddhant.gitexplorer.ui.repodetails;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.siddhant.gitexplorer.model.Repository;

public class RepositoryDetailsViewModel extends ViewModel {


    public final ObservableField<String> imageProfile;
    public final ObservableField<String> textName;
    public final ObservableField<String> textFullName;
    public final ObservableField<String> textDescription;

    public RepositoryDetailsViewModel() {
        imageProfile = new ObservableField<>("");
        textName = new ObservableField<>("");
        textFullName = new ObservableField<>("");
        textDescription = new ObservableField<>("");
    }

    public void initApiForContributors(Repository repository) {
        if (repository != null) {
            textName.set(repository.getName());
            textFullName.set(repository.getFullName());
            imageProfile.set(repository.getOwner().getAvatarUrl());
            textDescription.set(repository.getDescription());
        }
    }
}
