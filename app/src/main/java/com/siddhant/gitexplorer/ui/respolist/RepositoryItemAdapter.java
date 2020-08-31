package com.siddhant.gitexplorer.ui.respolist;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.siddhant.gitexplorer.ui.base.BaseViewHolder;

public class RepositoryItemAdapter extends RecyclerView.Adapter<BaseViewHolder> {



    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class RepositoryItemViewHolder extends BaseViewHolder {

        public RepositoryItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void onBind(int position) {

        }
    }

}
