package com.film.moviecrudapi.database.model;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.film.moviecrudapi.common.enums.UserEnumRole.UserRoleEnums;

@Entity
@Table(name = "users")
@SQLDelete(sql = "UPDATE users SET deleted_at = NOW() WHERE id=?")
@Where(clause = "deleted_at IS NULL")
@DynamicUpdate(true)
@Schema(name = "User", description = "User Model For User Table")
public class User {
    public User() {
    }

    public User(String name, String email, UserRoleEnums role, String password) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.password = password;
    }

    @Id
    @Column(length = 36, nullable = false, updatable = false)
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    private String id;

    @Column
    private String name;

    @Column
    private String email;

    @JsonIgnore
    @Column
    private String password;

    @Enumerated(EnumType.STRING)
    @Column
    private UserRoleEnums role;

    @Column(name = "created_at", updatable = false)
    private Date created = new Date();

    @Column(name = "updated_at", nullable = true)
    private Date updated = null;

    @Column(name = "deleted_at", nullable = true)
    private Date deleted = null;

    @OneToMany(mappedBy = "user")
    private Set<HistoryUser> historyUser;

    @OneToMany(mappedBy = "user")
    private Set<Rating> rating;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UserRoleEnums getRole() {
        return role;
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

    // Setter
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(UserRoleEnums role) {
        this.role = role;
    }

    @PreUpdate
    public void setUpdate() {
        this.updated = new Date();
    }
}
