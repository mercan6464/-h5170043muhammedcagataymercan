package com.h5170043.muhammed_cagatay_mercan_final.activity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.h5170043.muhammed_cagatay_mercan_final.R;
import com.h5170043.muhammed_cagatay_mercan_final.util.Constant;
import com.h5170043.muhammed_cagatay_mercan_final.util.GlideUtil;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    TextView txtTitle;
    ImageView imgMovie;
    TextView txtHtml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initViews();
        getArguments();
    }

    private void getArguments() {
        String title = getIntent().getStringExtra(Constant.MOVIE_TITLE_KEY);
        String html = getIntent().getStringExtra(Constant.MOVIE_HTML_KEY);
        String imgUrl = getIntent().getStringExtra(Constant.MOVIE_IMAGE_KEY);
        setViews(title, html, imgUrl);
    }

    private void setViews(String title, String html, String imgUrl) {
        txtTitle.setText(title);
        GlideUtil.setImageFromUrl(this, imgUrl, imgMovie);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            txtHtml.setText(Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT));
        } else {
            txtHtml.setText(Html.fromHtml(html));
        }
    }

    private void initViews() {
        txtTitle = findViewById(R.id.txt_detail_title);
        imgMovie = findViewById(R.id.img_detail_image);
        txtHtml = findViewById(R.id.txt_detail_html);
    }
}