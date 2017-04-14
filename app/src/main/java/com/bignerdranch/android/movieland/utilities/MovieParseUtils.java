package com.bignerdranch.android.movieland.utilities;

import android.content.Context;
import android.widget.Toast;

import com.bignerdranch.android.movieland.MovieDataType;

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
        /*
         //Is there an error? /
        if (moviesJson.has()) {
            int errorCode = moviesJson.getInt(OWM_MESSAGE_CODE);

            switch (errorCode) {
                case HttpURLConnection.HTTP_OK:
                    break;
                case HttpURLConnection.HTTP_NOT_FOUND:
                    // Location invalid /
                    return null;
                default:
                    / Server probably down /
                    return null;
            }
        }*/
        Toast.makeText(context, "holla", Toast.LENGTH_LONG).show();
        return movies;
    }
    private ArrayList<MovieDataType> movies  = null;
}
