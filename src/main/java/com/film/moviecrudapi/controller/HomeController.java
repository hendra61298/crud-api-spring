package com.film.moviecrudapi.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.film.moviecrudapi.common.baserespons.BaseResponse;
import com.film.moviecrudapi.controller.interfaces.ErrorHandlerInterface;
import com.film.moviecrudapi.database.model.Movie;
import com.film.moviecrudapi.database.model.User;
import com.film.moviecrudapi.dto.home.CreateHistoryMovieDto;
import com.film.moviecrudapi.dto.home.CreateRatingMovieDto;
import com.film.moviecrudapi.dto.home.ResponseDetailMovieDto;
import com.film.moviecrudapi.dto.home.ResponseHomeMovieDto;
import com.film.moviecrudapi.service.MovieService;
import com.film.moviecrudapi.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "HomeController", description = "Controller for home API, From here we can get Movie list, detail movie, last time movie, and rating movie ")
@RestController
@RequestMapping("/api")
public class HomeController implements ErrorHandlerInterface {

    @Autowired
    private UserService userService;

    @Autowired
    private MovieService movieService;

    @GetMapping(path = "/movie/home")
    public ResponseEntity<HashMap<String, Object>> getHomeData(HttpServletRequest credential) {
        String id = (String) credential.getAttribute("id");
        User user = userService.findUserById(id);
        if (user == null)
            return new BaseResponse(false, null, "Forbidden", HttpStatus.UNAUTHORIZED).toResponse();

        // get highlight film
        List<Movie> highlightMovie = movieService.getHighlightMovie();

        // get highlight film
        List<Movie> historyMovie = movieService.getHistoryMovie(user.getId());

        // get new moview film
        Iterable<Movie> newMovie = movieService.getAllMovie();
        return new BaseResponse(true, new ResponseHomeMovieDto(highlightMovie, historyMovie, newMovie),
                "Success Get Data Home", HttpStatus.OK).toResponse();

    }

    @GetMapping(path = "/movie/detail/{movie_id}")
    public ResponseEntity<HashMap<String, Object>> getDetailPage(HttpServletRequest credential,
            @PathVariable("movie_id") String movieId) {
        String id = (String) credential.getAttribute("id");
        User user = userService.findUserById(id);
        if (user == null)
            return new BaseResponse(false, null, "Forbidden", HttpStatus.UNAUTHORIZED).toResponse();

        // get detail Movie
        Movie detailMovie = movieService.getById(movieId);

        // get highlight film
        List<Movie> highlightMovie = movieService.getHighlightMovie();

        // get new moview film
        float rating = movieService.getRating(movieId);
        System.out.println(rating);
        return new BaseResponse(true, new ResponseDetailMovieDto(detailMovie, highlightMovie, rating),
                "Success Get Data Detail Data", HttpStatus.OK).toResponse();

    }

    @PostMapping(path = "/movie/watch")
    public ResponseEntity<HashMap<String, Object>> setWatchMovie(
            @Valid @RequestBody CreateHistoryMovieDto createHistoryUserDto,
            HttpServletRequest credential) {
        String id = (String) credential.getAttribute("id");
        User user = userService.findUserById(id);
        if (user == null)
            return new BaseResponse(false, null, "Forbidden", HttpStatus.UNAUTHORIZED).toResponse();

        Movie movie = movieService.getById(createHistoryUserDto.movieId);
        if (movie == null)
            return new BaseResponse(false, null, "Movie not found", HttpStatus.NOT_FOUND).toResponse();

        movieService.setWatchMovie(user.getId(), createHistoryUserDto.movieId, createHistoryUserDto.lastMinuteTime);
        return new BaseResponse(true, null, "Success Save Watch Data", HttpStatus.OK).toResponse();

    }

    @PostMapping(path = "/movie/rating")
    public ResponseEntity<HashMap<String, Object>> setRating(
            @Valid @RequestBody CreateRatingMovieDto createRatingMovieDto,
            HttpServletRequest credential) {
        String id = (String) credential.getAttribute("id");
        User user = userService.findUserById(id);
        if (user == null)
            return new BaseResponse(false, null, "Forbidden", HttpStatus.UNAUTHORIZED).toResponse();

        Movie movie = movieService.getById(createRatingMovieDto.movieId);
        if (movie == null)
            return new BaseResponse(false, null, "Movie not found", HttpStatus.NOT_FOUND).toResponse();

        movieService.setRatingMovie(user.getId(), createRatingMovieDto.movieId, createRatingMovieDto.score);
        return new BaseResponse(true, null, "Success Save Rating Movie", HttpStatus.OK).toResponse();

    }

}
