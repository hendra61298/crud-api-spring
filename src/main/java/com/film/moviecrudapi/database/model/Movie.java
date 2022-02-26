package com.film.moviecrudapi.database.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "movie")
@SQLDelete(sql = "UPDATE movie SET deleted_at = NOW() WHERE id=?")
@Where(clause = "deleted_at IS NULL")
@DynamicUpdate(true)
@Schema(name = "Movie", description = "Movie Model For Movie Table")
public class Movie {

    public Movie() {
    }

    public Movie(String title, String description, String logoUrl, boolean highlight, String videoUrl,
            String director) {
        this.title = title;
        this.description = description;
        this.logoUrl = logoUrl;
        this.highlight = highlight;
        this.videoUrl = videoUrl;
        this.director = director;
    }

    @Id
    @Column(length = 36, nullable = false, updatable = false)
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    private String id;

    @Column
    private String title;

    @Column
    private String description;

    @Column(name = "logo_url")
    private String logoUrl;

    @Column(name = "is_highlight")
    private boolean highlight = false;

    @Column(name = "video_url")
    private String videoUrl;

    @Column(name = "director_name")
    private String director;

    @Column(name = "created_at", updatable = false)
    private Date created = new Date();

    @Column(name = "updated_at", nullable = true)
    private Date updated = null;

    @Column(name = "deleted_at", nullable = true)
    private Date deleted = null;

    @OneToMany(mappedBy = "movie")
    private Set<HistoryUser> historyUser;

    @OneToMany(mappedBy = "movie")
    private Set<Rating> rating;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public boolean getHighlight() {
        return highlight;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getDirector() {
        return director;
    }

    public Date getCreatedDate() {
        return created;
    }

    public Date getUpdateDate() {
        return updated;
    }

    public Date getDeleted() {
        return deleted;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public void setHighlight(Boolean highlight) {
        this.highlight = highlight;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @PreUpdate
    public void setUpdate() {
        this.updated = new Date();
    }

}
