package com.siddhant.gitexplorer.ui.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.siddhant.gitexplorer.R;
import com.siddhant.gitexplorer.databinding.ActivityMainBinding;
import com.siddhant.gitexplorer.model.Repository;
import com.siddhant.gitexplorer.ui.main.respolist.RepositoryItemAdapter;
import com.siddhant.gitexplorer.ui.repodetails.RepositoryDetailsActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainActivityListener, RepositoryItemAdapter.RepositoryItemAdapterListener {

    MainActivityViewModel mainActivityViewModel;

    private ActivityMainBinding binding;

    private RepositoryItemAdapter repositoryItemAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivityViewModel = new MainActivityViewModel(this);

        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        binding.setLifecycleOwner(this);
        binding.setViewModel(mainActivityViewModel);

        mainActivityViewModel.initApiForRepository();

        setUpView();
    }

    private void setUpView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.amRepositoryRv.setLayoutManager(layoutManager);
    }

    @Override
    public void setRepositoryList(List<Repository> repositoryList) {
        repositoryItemAdapter = new RepositoryItemAdapter(repositoryList, this);
        binding.amRepositoryRv.setAdapter(repositoryItemAdapter);
        repositoryItemAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(Repository repository) {
        startActivity(RepositoryDetailsActivity.newIntent(this,repository));
    }
}