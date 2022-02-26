package com.film.moviecrudapi.dto.home;

import java.util.List;

import com.film.moviecrudapi.database.model.Movie;

public class ResponseHomeMovieDto {
    ResponseHomeMovieDto() {
    }

    public ResponseHomeMovieDto(List<Movie> highlightMovie, List<Movie> historyMovie, Iterable<Movie> lastestMovie) {
        this.highlightMovie = highlightMovie;
        this.historyMovie = historyMovie;
        this.lastestMovie = lastestMovie;
    }

    public List<Movie> highlightMovie;
    public List<Movie> historyMovie;
    public Iterable<Movie> lastestMovie;
}
