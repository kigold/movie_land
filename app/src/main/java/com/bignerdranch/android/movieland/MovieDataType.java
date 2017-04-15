package com.bignerdranch.android.movieland;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Kigold on 4/14/2017.
 */

public class MovieDataType {
    private String original_title, synopsis;
    private double user_rating;
    private String release_date;
    private String poster_image;
    private double popularity;
    private String asJsonData;

    //constructor


    public MovieDataType(String original_title, String synopsis, double user_rating, String release_date, String poster_image, double popularity, String asJsonData) {
        this.original_title = original_title;
        this.synopsis = synopsis;
        this.user_rating = user_rating;
        this.release_date = release_date;
        this.poster_image = "http://image.tmdb.org/t/p/w185" + poster_image;
        this.popularity = popularity;
        this.asJsonData = asJsonData;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public double getUser_rating() {
        return user_rating;
    }

    public void setUser_rating(double user_rating) {
        this.user_rating = user_rating;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getPoster_image() {
        return poster_image;
    }

    public void setPoster_image(String poster_image) {
        this.poster_image = poster_image;
    }

    public String getAsJsonData() {
        return asJsonData;
    }

    public void setAsJsonData(String asJsonData) {
        this.asJsonData = asJsonData;
    }

    public static ArrayList<MovieDataType> get_seed_data(){
        ArrayList<MovieDataType> movies = new ArrayList<>();
        movies.add(new MovieDataType("Naruto",
                "About a young man who wanted to becomee hokage",
                5, "",  "", 23, "")
                );
        movies.add(new MovieDataType("Boruto",
                "About a young Boy who wanted to be like his fathe",
                5, "", "", 23, "")
        );
        movies.add(new MovieDataType("Hinata",
                "About a a chick in love",
                5, "","", 23, "")
        );
        movies.add(new MovieDataType("Jiraya",
                "About a Pervy Sage",
                5, "",  "", 23, "")
        );
        return movies;
    }

    public static MovieDataType getMovieFromJson(String jsonData) {
        try {
            JSONObject mv = new JSONObject(jsonData);
            MovieDataType movie = new MovieDataType(mv.getString("original_title")
                    ,mv.getString("overview")
                    ,mv.getDouble("vote_average")
                    ,mv.getString("release_date")
                    ,mv.getString("poster_path")
                    ,mv.getDouble("popularity")
                    ,mv.toString());
            return movie;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}


