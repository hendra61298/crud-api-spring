
package com.film.moviecrudapi.database.repository;

import java.util.List;

import com.film.moviecrudapi.database.model.HistoryUser;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<HistoryUser, Long> {
    HistoryUser findByFilmIdAndUserId(String filmId, String userId);

    List<HistoryUser> findByUserIdOrderByUpdatedDesc(String userId);

    List<HistoryUser> findByFilmIdOrderByUpdatedDesc(String filmId);
}
