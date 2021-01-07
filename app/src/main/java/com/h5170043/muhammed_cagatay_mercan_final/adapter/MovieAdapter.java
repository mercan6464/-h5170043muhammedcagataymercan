package com.h5170043.muhammed_cagatay_mercan_final.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.h5170043.muhammed_cagatay_mercan_final.R;
import com.h5170043.muhammed_cagatay_mercan_final.model.Movie;
import com.h5170043.muhammed_cagatay_mercan_final.model.Movies;
import com.h5170043.muhammed_cagatay_mercan_final.util.GlideUtil;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private List<Movie> movieList;
    private final OnMovieClick listener;

    public MovieAdapter(OnMovieClick listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(view, movieList, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(position);
        holder.onClick(position);
    }

    @Override
    public int getItemCount() {
        return movieList == null ? 0 : movieList.size();
    }

    public void setMovies(Movies movies) {
        if (movies.data == null) {
            return;
        }
        this.movieList = movies.data;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final View view;
        private final List<Movie> movieList;
        private final OnMovieClick listener;

        public ViewHolder(@NonNull View itemView, List<Movie> movieList, OnMovieClick listener) {
            super(itemView);
            view = itemView;
            this.movieList = movieList;
            this.listener = listener;
        }

        public void onBind(int position) {
            TextView txtName = view.findViewById(R.id.txt_item_movie_name);
            TextView txtDate = view.findViewById(R.id.txt_item_movie_date);
            TextView txtDesc = view.findViewById(R.id.txt_item_movie_desc);
            ImageView imgLogo = view.findViewById(R.id.img_item_logo);

            txtName.setText(movieList.get(position).name);
            txtDate.setText(movieList.get(position).date);
            txtDesc.setText(movieList.get(position).description);
            String url = movieList.get(position).logoUrl;
            GlideUtil.setImageFromUrl(view.getContext(), url, imgLogo);
        }

        public void onClick(int position) {
            view.setOnClickListener(view ->
                    listener.onClick(movieList.get(position)));
        }
    }
}


