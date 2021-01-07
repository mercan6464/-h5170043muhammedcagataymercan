package com.h5170043.muhammed_cagatay_mercan_final.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.h5170043.muhammed_cagatay_mercan_final.R;

public class GlideUtil {
    public static void setImageFromUrl(Context context, String url, ImageView view) {
        Glide.with(context)
                .load(url)
                .error(R.drawable.logo)
                .into(view);
    }
}
