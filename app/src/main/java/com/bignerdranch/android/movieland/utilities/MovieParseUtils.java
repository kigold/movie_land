package com.bignerdranch.android.movieland.utilities;

import android.content.Context;
import android.widget.Toast;

import com.bignerdranch.android.movieland.MovieDataType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;

/**
 * Created by Kigold on 4/14/2017.
 */

public class MovieParseUtils {

    public static ArrayList<MovieDataType> getMovieFromHttpRequest(Context context, String raw_movies)
        throws JSONException    {
        ArrayList<MovieDataType> movies = new ArrayList<>();

        JSONObject moviesJson = new JSONObject(raw_movies);

         //Is there an error? /
        if (moviesJson.has("results")) {
            JSONArray moviesArray = moviesJson.getJSONArray("results");

            if (moviesArray != null || moviesArray.length() > 0) {
                moviesArray = moviesJson.getJSONArray("results");

                for (int i = 0; i < moviesArray.length(); i++) {
                    JSONObject mv = moviesArray.getJSONObject(i);

                    MovieDataType movie = new MovieDataType(mv.getString("original_title")
                    ,mv.getString("overview")
                    ,mv.getDouble("vote_average")
                    ,mv.getString("release_date")
                    ,mv.getString("poster_path")
                    ,mv.getDouble("popularity"));

                    //add movie to List
                    movies.add(movie);
                }
            }
        }
        return movies;
    }
    private ArrayList<MovieDataType> movies  = null;
}
