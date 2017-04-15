package com.bignerdranch.android.movieland;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailedView extends AppCompatActivity {


    private final String MOVIE_DATA_FOR_INTENT = "MOVIE_DATA";

    MovieDataType mMovie;
    TextView mTitle, mSynopsis, mReleaseDate, mPopularity, mRating;
    ImageView mPoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_view);

        mTitle = (TextView) findViewById(R.id.tv_dv_title);
        mSynopsis = (TextView) findViewById(R.id.tv_dv_synopsis);
        mReleaseDate = (TextView) findViewById(R.id.tv_dv_release_date);
        mPopularity = (TextView) findViewById(R.id.tv_dv_popularity);
        mRating = (TextView) findViewById(R.id.tv_dv_rating);
        mPoster = (ImageView) findViewById(R.id.iv_dv_poster);


        Intent intentOrigin = getIntent();

        if (intentOrigin != null) {
            if (intentOrigin.hasExtra(MOVIE_DATA_FOR_INTENT)) {
                String movieAsJson = intentOrigin.getStringExtra(MOVIE_DATA_FOR_INTENT);
                mMovie = MovieDataType.getMovieFromJson(movieAsJson);
                mTitle.setText(mMovie.getOriginal_title());
                mSynopsis.setText(mMovie.getSynopsis());
                mReleaseDate.setText(mMovie.getRelease_date());
                //TODO cast popularit and rating to string
                mPopularity.setText("Testing");
                mReleaseDate.setText("Testing");
                Picasso.with(getApplicationContext())
                        .load(mMovie.getPoster_image())
                        .placeholder(R.mipmap.ic_launcher)
                        .error(R.mipmap.ic_launcher)
                        .into(mPoster);
            }
        }
    }
}
