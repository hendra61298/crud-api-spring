package com.film.moviecrudapi.dto.home;

import java.util.List;

import com.film.moviecrudapi.database.model.Movie;

public class ResponseDetailMovieDto {
    ResponseDetailMovieDto() {
    }

    public ResponseDetailMovieDto(Movie detailMovie, List<Movie> highlightMovie, float ratingMovie) {
        this.detailMovie = detailMovie;
        this.highlightMovie = highlightMovie;
        this.ratingMovie = ratingMovie;
    }

    public Movie detailMovie;
    public float ratingMovie;
    public List<Movie> highlightMovie;
}
