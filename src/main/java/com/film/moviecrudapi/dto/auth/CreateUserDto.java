package com.film.moviecrudapi.dto.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.film.moviecrudapi.common.enums.UserEnumRole.UserRoleEnums;

public class CreateUserDto {
    @NotEmpty(message = "Name is Required")
    public String name;

    @Email(message = "Email not valid")
    @NotEmpty(message = "Email is Required")
    public String email;

    public UserRoleEnums role;

    @NotEmpty(message = " password is Required")
    @Size(min = 6, message = "Please enter at least 6 digits")
    public String password;
}
