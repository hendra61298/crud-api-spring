package com.film.moviecrudapi.controller.admin;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.film.moviecrudapi.common.baserespons.BaseResponse;
import com.film.moviecrudapi.controller.interfaces.AdminHandlerInterface;
import com.film.moviecrudapi.controller.interfaces.ErrorHandlerInterface;
import com.film.moviecrudapi.database.model.Movie;
import com.film.moviecrudapi.dto.movie.CreateMovieDto;
import com.film.moviecrudapi.dto.movie.UpdateMovieDto;
import com.film.moviecrudapi.service.MovieService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "MovieController", description = "MovieControllerAPI, Admin can create/upload movie, deleted movie, getall movie list, get detail movie , update and deleted movie")
@RestController
@RequestMapping("/api/admin/movie")
public class MovieController implements ErrorHandlerInterface, AdminHandlerInterface {

    @Autowired
    private MovieService movieService;

    @PostMapping(path = "/create")
    public ResponseEntity<HashMap<String, Object>> createMovie(@Valid @RequestBody CreateMovieDto createMovieDto,
            HttpServletRequest credential) {

        if (!validateRoleAdmin(credential))
            return new BaseResponse(false, null, "Forbidden", HttpStatus.UNAUTHORIZED).toResponse();

        Movie movie = movieService.create(createMovieDto);
        return new BaseResponse(true, movie, "Success Create Movie ", HttpStatus.OK)
                .toResponse();
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody ResponseEntity<HashMap<String, Object>> getMovieById(@PathVariable("id") String id,
            HttpServletRequest credential) {
        if (!validateRoleAdmin(credential))
            return new BaseResponse(false, null, "Forbidden", HttpStatus.UNAUTHORIZED).toResponse();

        Movie movie = movieService.getById(id);
        if (movie != null) {
            return new BaseResponse(true, movie, "Success Get Movie ",
                    HttpStatus.OK).toResponse();
        } else {
            return new BaseResponse(true, null, "Movie Not Found ",
                    HttpStatus.NOT_FOUND).toResponse();
        }
    }

    @GetMapping(path = "")
    public @ResponseBody ResponseEntity<HashMap<String, Object>> getAllUsers(HttpServletRequest credential) {
        if (!validateRoleAdmin(credential))
            return new BaseResponse(false, null, "Forbidden", HttpStatus.UNAUTHORIZED).toResponse();

        return new BaseResponse(true, movieService.getAllMovie(), "Success Get Movie List", HttpStatus.OK).toResponse();
    }

    @PutMapping(path = "/update/{id}")
    public @ResponseBody ResponseEntity<HashMap<String, Object>> udpateMovie(@PathVariable("id") String id,
            @Valid @RequestBody UpdateMovieDto updateMovieDto) {
        return new BaseResponse(true, movieService.updateMovie(id, updateMovieDto), "Succes Update Movie Detail",
                HttpStatus.OK)
                        .toResponse();
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody ResponseEntity<HashMap<String, Object>> deletUser(@PathVariable("id") String id,
            HttpServletRequest credential) {

        if (!validateRoleAdmin(credential))
            return new BaseResponse(false, null, "Forbidden", HttpStatus.UNAUTHORIZED).toResponse();

        Movie movie = movieService.getById(id);
        if (movie != null) {
            movieService.deletedMovie(movie);
            return new BaseResponse(true, movie, "Success Deleted Movie From List", HttpStatus.OK).toResponse();
        } else {
            return new BaseResponse(false, null, "Failed Deleted Movie", HttpStatus.NOT_FOUND).toResponse();
        }
    }

}
