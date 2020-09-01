package com.siddhant.gitexplorer.ui.contributorrepo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.siddhant.gitexplorer.R;
import com.siddhant.gitexplorer.databinding.ActivityContibutorRepositoryBinding;
import com.siddhant.gitexplorer.model.Owner;
import com.siddhant.gitexplorer.model.Repository;
import com.siddhant.gitexplorer.ui.main.respolist.RepositoryItemAdapter;

import java.util.List;

public class ContributorRepositoryActivity extends AppCompatActivity implements ContributorRepositoryActivityListener, RepositoryItemAdapter.RepositoryItemAdapterListener {

    private static final String ARGS_PARAM_1 = "ARGS_PARAM_1";

    ContributorRepositoryViewModel viewModel;

    private ActivityContibutorRepositoryBinding binding;

    private RepositoryItemAdapter repositoryItemAdapter;

    private Owner owner;

    public static Intent newIntent(Context context, Owner owner) {
        Intent intent = new Intent(context, ContributorRepositoryActivity.class);
        Bundle args = new Bundle();
        args.putParcelable(ARGS_PARAM_1, owner);
        intent.putExtra("bundle", args);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ContributorRepositoryViewModel(this);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_contibutor_repository);
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);

        Bundle args = getIntent().getBundleExtra("bundle");

        this.owner = args.getParcelable(ARGS_PARAM_1);

        viewModel.initApiForRepository(owner);

        setUpView();
    }

    private void setUpView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.acrRepoRv.setLayoutManager(layoutManager);
    }

    @Override
    public void setRepositoryList(List<Repository> repositoryList) {
        repositoryItemAdapter = new RepositoryItemAdapter(repositoryList, this);
        binding.acrRepoRv.setAdapter(repositoryItemAdapter);
        repositoryItemAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(Repository repository) {

    }
}