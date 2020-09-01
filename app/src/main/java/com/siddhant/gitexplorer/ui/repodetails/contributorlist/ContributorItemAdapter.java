package com.siddhant.gitexplorer.ui.repodetails.contributorlist;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.siddhant.gitexplorer.databinding.ItemContributorBinding;
import com.siddhant.gitexplorer.model.Owner;
import com.siddhant.gitexplorer.ui.base.BaseViewHolder;

import java.util.List;

public class ContributorItemAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    List<Owner> ownerList;
    ContributorItemAdapterListener listener;

    public ContributorItemAdapter(List<Owner> ownerList, ContributorItemAdapterListener listener) {
        this.ownerList = ownerList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemContributorBinding binding = ItemContributorBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ContributorItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return ownerList.size();
    }

    public interface ContributorItemAdapterListener {

        void onItemClick(Owner owner);
    }

    private class ContributorItemViewHolder extends BaseViewHolder implements ContributorItemViewModel.ContributorItemViewModelListener {

        private ItemContributorBinding binding;

        private ContributorItemViewModel viewModel;


        public ContributorItemViewHolder(ItemContributorBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            if (ownerList.get(position) != null) {
                viewModel = new ContributorItemViewModel(ownerList.get(position), this);
                binding.setViewModel(viewModel);
                binding.executePendingBindings();
            }
        }

        @Override
        public void onItemClick(Owner owner) {
            listener.onItemClick(owner);
        }
    }
}
