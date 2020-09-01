package com.siddhant.gitexplorer.util;

import android.content.Context;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.siddhant.gitexplorer.R;

public class BindingUtils {

    public BindingUtils() {
    }

    @BindingAdapter("fitGridImageUrl")
    public static void setGridFullImageUrl(ImageView imageView, String url) {
        Context context = imageView.getContext();
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.ic_user_profile)
                .error(R.drawable.ic_user_profile);
        Glide.with(context).load(url).apply(options).into(imageView);
    }
}
