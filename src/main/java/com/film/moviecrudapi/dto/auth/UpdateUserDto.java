package com.film.moviecrudapi.dto.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class UpdateUserDto {
    @NotEmpty(message = "Name is Required")
    public String name;

    @Email(message = "Email not valid")
    @NotEmpty(message = "Email is Required")
    public String email;

    public String password;

}