package com.siddhant.gitexplorer.ui.repodetails;

import com.siddhant.gitexplorer.model.Owner;

import java.util.List;

public interface RepositoryDetailsActivityListener {
    void setOwnerList(List<Owner> ownerList);
}
