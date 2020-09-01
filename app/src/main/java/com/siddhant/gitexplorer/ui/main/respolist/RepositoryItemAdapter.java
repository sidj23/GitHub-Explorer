package com.siddhant.gitexplorer.ui.main.respolist;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.siddhant.gitexplorer.databinding.ItemRepositoryBinding;
import com.siddhant.gitexplorer.model.Repository;
import com.siddhant.gitexplorer.ui.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class RepositoryItemAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<Repository> repositoryList = new ArrayList<>();

    private RepositoryItemAdapterListener listener;

    public RepositoryItemAdapter(List<Repository> repositoryList, RepositoryItemAdapterListener listener) {
        this.repositoryList = repositoryList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRepositoryBinding itemRepositoryBinding = ItemRepositoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new RepositoryItemViewHolder(itemRepositoryBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return repositoryList.size();
    }


    public interface RepositoryItemAdapterListener {
        void onItemClick(Repository repository);
    }

    public class RepositoryItemViewHolder extends BaseViewHolder implements RepositoryItemViewModel.RepositoryItemViewModelListener {

        private ItemRepositoryBinding repositoryBinding;
        private RepositoryItemViewModel repositoryItemViewModel;

        public RepositoryItemViewHolder(@NonNull ItemRepositoryBinding repositoryBinding) {
            super(repositoryBinding.getRoot());
            this.repositoryBinding = repositoryBinding;
        }


        @Override
        public void onBind(int position) {
            if (repositoryList.get(position) != null) {
                repositoryItemViewModel = new RepositoryItemViewModel(repositoryList.get(position), this);
                repositoryBinding.setViewModel(repositoryItemViewModel);
                repositoryBinding.executePendingBindings();
            }
        }

        @Override
        public void onItemClick(Repository repository) {
            listener.onItemClick(repository);
        }
    }

}
