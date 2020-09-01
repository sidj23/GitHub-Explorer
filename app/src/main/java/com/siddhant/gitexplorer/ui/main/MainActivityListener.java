package com.siddhant.gitexplorer.ui.main;

import com.siddhant.gitexplorer.model.Repository;

import java.util.List;

public interface MainActivityListener {
    void setRepositoryList(List<Repository> repositoryList);
}
