package com.siddhant.gitexplorer.ui.repodetails.contributorlist;

import androidx.databinding.ObservableField;

import com.siddhant.gitexplorer.model.Owner;

public class ContributorItemViewModel {

    public ObservableField<String> textName;
    public ObservableField<String> imageProfile;

    private ContributorItemViewModelListener listener;
    private Owner owner;

    public ContributorItemViewModel(Owner owner, ContributorItemViewModelListener listener) {
        this.owner = owner;
        this.listener = listener;

        if (owner != null) {
            textName = new ObservableField<>(owner.getLogin());
            imageProfile = new ObservableField<>(owner.getAvatarUrl());
        }
    }

    public void onItemClick() {
        if (listener != null && owner != null)
            listener.onItemClick(owner);
    }

    public interface ContributorItemViewModelListener {

        void onItemClick(Owner owner);
    }
}
