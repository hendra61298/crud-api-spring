package com.film.moviecrudapi.database.repository;

import java.util.List;

import com.film.moviecrudapi.database.model.Movie;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    Movie findById(String id);

    List<Movie> findByHighlight(boolean highlight);

}
