package com.bignerdranch.android.movieland;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.android.movieland.utilities.MovieParseUtils;
import com.bignerdranch.android.movieland.utilities.NetworkUtils;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieAdapterOnClickHandler{

    private RecyclerView mRecyclerView;
    private MovieAdapter mMovieAdapter;
    private TextView mErrorMessage;
    private ProgressBar mProgressBar;
    private final int NUMBER_OF_ITEMS_IN_GRIDVIEW = 4;
    private final String MOVIE_DATA_FOR_INTENT = "MOVIE_DATA";
    private static final String MOVIES_API_KEY = BuildConfig.MOVIES_API_KEY;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mRecyclerView = (RecyclerView) findViewById(R.id.rv_movie_recycle_view);
        mErrorMessage = (TextView) findViewById(R.id.tv_errro_msg);

        GridLayoutManager layoutManager = new GridLayoutManager(this, NUMBER_OF_ITEMS_IN_GRIDVIEW);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setHasFixedSize(true);
        mMovieAdapter = new MovieAdapter(this);
        mRecyclerView.setAdapter(mMovieAdapter);

        //init progress bar
        mProgressBar = (ProgressBar) findViewById(R.id.pb_progress_bar);
        //load movies
        loadMovieData(null);
    }
    private void loadMovieData(String choice) {
        showMovieDataView();
        //TODO get menu sort item from persistent data source
        String sort_choice = "top_rated";
        if (choice != null) {
            sort_choice = choice;
        }
        // "popular" or "top_rated"
        new GetMoviesTask().execute(sort_choice);
    }

    @Override
    public void onClick(MovieDataType movie) {
        Context context = this;
        Intent i = new Intent();
        i.putExtra(MOVIE_DATA_FOR_INTENT, "MOVIE EXTRA DATA");
        i.setClass(this, DetailedView.class);
        startActivity(i);
        Toast.makeText(context, movie.getOriginal_title(), Toast.LENGTH_SHORT)
                .show();
    }

    private void showMovieDataView(){
        // hide error message view
        mErrorMessage.setVisibility(View.INVISIBLE);
        // show movie grid list
        mRecyclerView.setVisibility(View.VISIBLE);
    }
    private void showErro(){
        // show error message view
        mErrorMessage.setVisibility(View.VISIBLE);
        // hide movie grid list
        mRecyclerView.setVisibility(View.INVISIBLE);
    }

    public class GetMoviesTask extends AsyncTask<String, Void, ArrayList<MovieDataType>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected ArrayList<MovieDataType> doInBackground(String... params) {
            //TODO fetch movies from api
            //ArrayList<MovieDataType> movies = MovieDataType.get_seed_data();
            String sort_choice = params[0];
            URL movieReqestUrl = NetworkUtils.buildUrl(sort_choice);
            try {
                String jsonMovieResponse = NetworkUtils
                        .getResponseFromHttpUrl(movieReqestUrl);

                ArrayList<MovieDataType> movies = MovieParseUtils
                        .getMovieFromHttpRequest(getApplicationContext(), jsonMovieResponse);
                return movies;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<MovieDataType> moviesData) {
            mProgressBar.setVisibility(View.INVISIBLE);
            if (moviesData != null) {
                showMovieDataView();
                mMovieAdapter.setData(moviesData);
            }else{
                showErro();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate manue
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.movie, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
        // "popular" or "top_rated"
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_highest_rated:
                item.setChecked(true);
                Toast.makeText(getApplicationContext(), "Sort By Highest Rated", Toast.LENGTH_LONG).show();
                mMovieAdapter.setData(null);
                loadMovieData("top_rated");
                return true;
            case R.id.menu_most_popular:
                item.setChecked(true);
                Toast.makeText(getApplicationContext(), "Sort By Most Popular", Toast.LENGTH_LONG).show();
                mMovieAdapter.setData(null);
                loadMovieData("popular");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
