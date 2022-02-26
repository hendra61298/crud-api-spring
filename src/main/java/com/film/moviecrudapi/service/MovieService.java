package com.film.moviecrudapi.service;

import java.util.ArrayList;
import java.util.List;

import com.film.moviecrudapi.database.model.HistoryUser;
import com.film.moviecrudapi.database.model.Movie;
import com.film.moviecrudapi.database.model.Rating;
import com.film.moviecrudapi.database.repository.HistoryRepository;
import com.film.moviecrudapi.database.repository.MovieRepository;
import com.film.moviecrudapi.database.repository.RatingRepository;
import com.film.moviecrudapi.dto.movie.CreateMovieDto;
import com.film.moviecrudapi.dto.movie.UpdateMovieDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private HistoryRepository historyUserRepository;

    @Autowired
    private RatingRepository ratingRepository;

    public Movie create(CreateMovieDto createMovieDto) {
        Movie movie = new Movie(createMovieDto.title, createMovieDto.description, createMovieDto.logoUrl,
                createMovieDto.highlight, createMovieDto.videoUrl, createMovieDto.director);
        return movieRepository.save(movie);
    }

    public Iterable<Movie> getAllMovie() {
        return movieRepository.findAll(PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "created"))).getContent();
    }

    public Movie deletedMovie(Movie movie) {
        movieRepository.delete(movie);
        return movie;
    }

    public Movie getById(String id) {
        return movieRepository.findById(id);
    }

    public List<Movie> getByHighlight(Boolean highlight) {
        return movieRepository.findByHighlight(highlight);
    }

    public Movie updateMovie(String id, UpdateMovieDto updateMovieDto) {
        Movie movie = movieRepository.findById(id);
        if (updateMovieDto.title != null)
            movie.setTitle(updateMovieDto.title);
        if (updateMovieDto.description != null)
            movie.setDescription(updateMovieDto.description);
        if (updateMovieDto.logoUrl != null)
            movie.setLogoUrl(updateMovieDto.logoUrl);
        if (updateMovieDto.highlight != movie.getHighlight())
            movie.setHighlight(updateMovieDto.highlight);
        return movieRepository.save(movie);

    }

    public void setWatchMovie(String userId, String movieId, int lastMinuteTime) {
        HistoryUser exist = historyUserRepository.findByFilmIdAndUserId(movieId, userId);
        if (exist == null) {
            HistoryUser history = new HistoryUser(userId, movieId, lastMinuteTime);
            historyUserRepository.save(history);
        } else {
            exist.setLastMinuteTime(lastMinuteTime);
            historyUserRepository.save(exist);
        }
    }

    public void setRatingMovie(String userId, String movieId, int score) {
        Rating exist = ratingRepository.findByFilmIdAndUserId(movieId, userId);
        if (exist == null) {
            Rating rating = new Rating(userId, movieId, score);
            ratingRepository.save(rating);
        } else {
            exist.setScore(score);
            ratingRepository.save(exist);
        }
    }

    public List<Movie> getHistoryMovie(String userId) {
        List<HistoryUser> history = historyUserRepository.findByUserIdOrderByUpdatedDesc(userId);
        List<Movie> historys = new ArrayList<>();

        for (HistoryUser historyUser : history) {
            Movie historyData = movieRepository.findById(historyUser.getId());
            historys.add(historyData);
        }

        return historys;
    }

    public List<Movie> getHighlightMovie() {
        return movieRepository.findByHighlight(true);
    }

    public float getRating(String movieId) {
        float ratingData = 0;
        List<Rating> ratings = ratingRepository.findByFilmId(movieId);
        for (Rating rating : ratings) {
            ratingData = ratingData + rating.getScore();
        }
        if (ratings.size() == 0)
            return 0;
        return ratingData / ratings.size();
    }

}
