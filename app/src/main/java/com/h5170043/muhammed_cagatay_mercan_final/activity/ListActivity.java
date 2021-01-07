package com.h5170043.muhammed_cagatay_mercan_final.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.h5170043.muhammed_cagatay_mercan_final.R;
import com.h5170043.muhammed_cagatay_mercan_final.adapter.MovieAdapter;
import com.h5170043.muhammed_cagatay_mercan_final.adapter.OnMovieClick;
import com.h5170043.muhammed_cagatay_mercan_final.model.Movie;
import com.h5170043.muhammed_cagatay_mercan_final.model.Movies;
import com.h5170043.muhammed_cagatay_mercan_final.network.MovieService;
import com.h5170043.muhammed_cagatay_mercan_final.network.MovieServiceApi;
import com.h5170043.muhammed_cagatay_mercan_final.util.Constant;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ListActivity extends AppCompatActivity implements OnMovieClick {

    private MovieAdapter adapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        initProgressBar();
        initRecyclerView();
        getMovies();
    }

    private void initProgressBar() {
        progressBar = findViewById(R.id.progressBar_list_movie);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void getMovies() {
        MovieService movieService = MovieServiceApi.getMovieService();
        movieService.getMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Movies>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) { }

                    @Override
                    public void onNext(@NonNull Movies movies) {
                        handleResult(movies);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) { }

                    @Override
                    public void onComplete() { }
                });
    }

    private void handleResult(Movies movies) {
        progressBar.setVisibility(View.GONE);
        adapter.setMovies(movies);
    }

    private void initRecyclerView() {
        RecyclerView rvMovie = findViewById(R.id.rv_list_movie);
        adapter = new MovieAdapter(this);
        LinearLayoutManager llm =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvMovie.setLayoutManager(llm);
        rvMovie.setAdapter(adapter);
    }

    @Override
    public void onClick(Movie movie) {
        navigateToDetailActivity(movie);
    }

    private void navigateToDetailActivity(Movie movie) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(Constant.MOVIE_TITLE_KEY, movie.name);
        intent.putExtra(Constant.MOVIE_IMAGE_KEY, movie.imageUrl);
        intent.putExtra(Constant.MOVIE_HTML_KEY, movie.htmlData);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        createExitAlertDialog();
    }

    private void createExitAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false)
                .setPositiveButton(R.string.yes, (dialogInterface, i) ->
                        finish())
                .setNegativeButton(R.string.no, (dialogInterface, i) ->
                        dialogInterface.dismiss());
        AlertDialog dialog = builder.create();
        dialog.setTitle(R.string.exit_app);
        dialog.show();
    }
}