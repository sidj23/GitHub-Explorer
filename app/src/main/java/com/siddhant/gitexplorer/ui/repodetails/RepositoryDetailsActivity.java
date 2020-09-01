package com.siddhant.gitexplorer.ui.repodetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.siddhant.gitexplorer.R;
import com.siddhant.gitexplorer.databinding.ActivityRepositoryDetailsBinding;
import com.siddhant.gitexplorer.model.Owner;
import com.siddhant.gitexplorer.model.Repository;
import com.siddhant.gitexplorer.ui.contributorrepo.ContributorRepositoryActivity;
import com.siddhant.gitexplorer.ui.repodetails.contributorlist.ContributorItemAdapter;

import java.util.List;

public class RepositoryDetailsActivity extends AppCompatActivity implements RepositoryDetailsActivityListener, ContributorItemAdapter.ContributorItemAdapterListener {

    private static final String ARGS_PARAM_1 = "ARGS_PARAM_1";

    RepositoryDetailsViewModel repositoryDetailsViewModel;

    private ActivityRepositoryDetailsBinding binding;

    private Repository repository;

    private ContributorItemAdapter contributorItemAdapter;

    public static Intent newIntent(Context context, Repository repository) {
        Intent intent = new Intent(context, RepositoryDetailsActivity.class);
        Bundle args = new Bundle();
        args.putParcelable(ARGS_PARAM_1, repository);
        intent.putExtra("bundle", args);
        return intent;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        repositoryDetailsViewModel = new RepositoryDetailsViewModel(this);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_repository_details);
        binding.setLifecycleOwner(this);
        binding.setViewModel(repositoryDetailsViewModel);

        Bundle args = getIntent().getBundleExtra("bundle");

        this.repository = args.getParcelable(ARGS_PARAM_1);

        repositoryDetailsViewModel.initApiForContributors(repository);

        setUpView();

    }

    private void setUpView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.ardContriRv.setLayoutManager(gridLayoutManager);
    }

    @Override
    public void setOwnerList(List<Owner> ownerList) {
        contributorItemAdapter = new ContributorItemAdapter(ownerList, this);
        binding.ardContriRv.setAdapter(contributorItemAdapter);
        contributorItemAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(Owner owner) {
        startActivity(ContributorRepositoryActivity.newIntent(this, owner));
    }
}
