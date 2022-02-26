package com.film.moviecrudapi.dto.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginUserDto {

    @Email(message = "Email not valid")
    @NotEmpty(message = "Email is Required")
    public String email;

    @NotEmpty(message = "password is Required")
    @NotNull(message = "password is Required")
    @Size(min = 6, message = "Please enter at least 6 digits")
    public String password;
}
