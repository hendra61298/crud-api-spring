package com.film.moviecrudapi.database.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "rating")
@DynamicUpdate(true)
@Schema(name = "Rating", description = "Rating Model For Rating Table")
public class Rating {
    public Rating() {
    }

    public Rating(String userId, String filmId, int score) {
        this.userId = userId;
        this.filmId = filmId;
        this.score = score;
    }

    @Id
    @Column(length = 36, nullable = false, updatable = false)
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    private String id;

    @Column(name = "film_id")
    private String filmId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "score")
    private int score;

    @Column(name = "created_at", updatable = false)
    private Date created = new Date();

    @Column(name = "updated_at", nullable = true)
    private Date updated = null;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "film_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Movie movie;

    public String getId() {
        return id;
    }

    public String getFilmId() {
        return filmId;
    }

    public String getUserId() {
        return userId;
    }

    public int getScore() {
        return score;
    }

    public Date getCreatedDate() {
        return created;
    }

    public Date getUpdateDate() {
        return updated;
    }

    public void setFilmId(String filmId) {
        this.filmId = filmId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @PreUpdate
    public void setUpdate() {
        this.updated = new Date();
    }
}