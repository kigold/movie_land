package com.bignerdranch.android.movieland;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Kigold on 4/14/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder>{

    //TODO get mMovies from API
    private ArrayList<MovieDataType> mMoviesData;

    public class MovieAdapterViewHolder extends RecyclerView.ViewHolder{

        public final ImageView mMoviePosterView;

        public MovieAdapterViewHolder(View view) {
            super(view);
            mMoviePosterView = (ImageView) view.findViewById(R.id.iv_movie_poster);
        }
    }

    @Override
    public MovieAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttactToParentImmediately = false;

        View view = inflater.inflate(R.layout.movie_list_item, viewGroup, shouldAttactToParentImmediately);
        return new MovieAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieAdapterViewHolder holder, int position) {
        MovieDataType daMovie = mMoviesData.get(position);
        holder.mMoviePosterView.setImageResource(daMovie.getPoster_image());

    }

    @Override
    public int getItemCount() {
        if (null != mMoviesData) {
            return mMoviesData.size();
        }
        return 0;
    }

    public void setData(ArrayList<MovieDataType> moviesData){
        mMoviesData = moviesData;
        notifyDataSetChanged();
    }
}
