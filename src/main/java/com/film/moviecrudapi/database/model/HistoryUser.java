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
@Table(name = "history")
@DynamicUpdate(true)
@Schema(name = "History", description = "History Model For History Table")
public class HistoryUser {
    public HistoryUser() {
    }

    public HistoryUser(String userId, String filmId, int lastMinuteTime) {
        this.userId = userId;
        this.filmId = filmId;
        this.lastMinuteTime = lastMinuteTime;
    }

    @Id
    @Column(length = 36, nullable = false, updatable = false)
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    private String id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "film_id")
    private String filmId;

    @Column(name = "last_minute_time")
    private int lastMinuteTime;

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

    public String getUserId() {
        return userId;
    }

    public String getFilmId() {
        return filmId;
    }

    public int getLastMinuteTime(int lastMinuteTime) {
        return lastMinuteTime;
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

    public void setLastMinuteTime(Integer lastMinuteTime) {
        this.lastMinuteTime = lastMinuteTime;
    }

    @PreUpdate
    public void setUpdate() {
        this.updated = new Date();
    }
}
