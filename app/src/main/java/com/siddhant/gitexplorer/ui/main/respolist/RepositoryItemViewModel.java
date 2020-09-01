package com.siddhant.gitexplorer.ui.main.respolist;

import androidx.databinding.ObservableField;

import com.siddhant.gitexplorer.model.Repository;

public class RepositoryItemViewModel {

    public ObservableField<String> textName;
    public ObservableField<String> textFullName;
    public ObservableField<String> imageProfile;

    private RepositoryItemViewModelListener listener;
    private Repository repository;

    public RepositoryItemViewModel(Repository repository, RepositoryItemViewModelListener listener) {
        this.repository = repository;
        this.listener = listener;

        if (repository != null) {
            textName = new ObservableField<>(repository.getName());
            textFullName = new ObservableField<>(repository.getFullName());
            imageProfile = new ObservableField<>(repository.getOwner().getAvatarUrl());
        }
    }

    public void onItemClick() {
        if (listener != null && repository != null)
            listener.onItemClick(repository);
    }

    public interface RepositoryItemViewModelListener {

        void onItemClick(Repository repository);
    }
}
