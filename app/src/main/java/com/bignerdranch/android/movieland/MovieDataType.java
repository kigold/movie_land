package com.bignerdranch.android.movieland;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Kigold on 4/14/2017.
 */

public class MovieDataType {
    private String original_title, synopsis;
    private int user_rating;
    private Date release_date;
    private int poster_image;

    //constructor


    public MovieDataType(String original_title, String synopsis, int user_rating, int poster_image) {
        this.original_title = original_title;
        this.synopsis = synopsis;
        this.user_rating = user_rating;
        this.release_date = release_date;
        this.poster_image = poster_image;
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

    public int getUser_rating() {
        return user_rating;
    }

    public void setUser_rating(int user_rating) {
        this.user_rating = user_rating;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public int getPoster_image() {
        return poster_image;
    }

    public void setPoster_image(int poster_image) {
        this.poster_image = poster_image;
    }

    public static ArrayList<MovieDataType> get_seed_data(){
        ArrayList<MovieDataType> movies = new ArrayList<>();
        movies.add(new MovieDataType("Naruto",
                "About a young man who wanted to becomee hokage",
                5, R.mipmap.ic_launcher)
                );
        movies.add(new MovieDataType("Boruto",
                "About a young Boy who wanted to be like his fathe",
                5, R.mipmap.ic_launcher)
        );
        movies.add(new MovieDataType("Hinata",
                "About a a chick in love",
                5, R.mipmap.ic_launcher)
        );
        movies.add(new MovieDataType("Jiraya",
                "About a Pervy Sage",
                5, R.mipmap.ic_launcher)
        );
        return movies;
    }
}


