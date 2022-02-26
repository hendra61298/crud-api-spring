
package com.film.moviecrudapi.database.repository;

import java.util.List;

import com.film.moviecrudapi.database.model.Rating;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    Rating findByFilmIdAndUserId(String filmId, String userId);

    List<Rating> findByUserId(String userId);

    List<Rating> findByFilmId(String filmId);
}
