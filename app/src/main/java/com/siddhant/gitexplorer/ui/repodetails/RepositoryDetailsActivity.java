package com.siddhant.gitexplorer.ui.repodetails;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.siddhant.gitexplorer.R;
import com.siddhant.gitexplorer.databinding.ActivityRepositoryDetailsBinding;
import com.siddhant.gitexplorer.model.Repository;

public class RepositoryDetailsActivity extends AppCompatActivity {

    private static final String ARGS_PARAM_1 = "ARGS_PARAM_1";

    RepositoryDetailsViewModel repositoryDetailsViewModel;

    private ActivityRepositoryDetailsBinding binding;

    private Repository repository;

    public static Intent newIntent(Repository repository) {
        Intent intent = new Intent();
        Bundle args = new Bundle();
        args.putParcelable(ARGS_PARAM_1, repository);
        return intent;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        repositoryDetailsViewModel = new RepositoryDetailsViewModel();

        binding = DataBindingUtil.setContentView(this, R.layout.activity_repository_details);
        binding.setLifecycleOwner(this);
        binding.setViewModel(repositoryDetailsViewModel);

        this.repository = savedInstanceState.getParcelable(ARGS_PARAM_1);

        repositoryDetailsViewModel.initApiForContributors(repository);

        setUpView();

    }

    private void setUpView() {

    }
}
