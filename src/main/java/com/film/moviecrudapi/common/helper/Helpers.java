package com.film.moviecrudapi.common.helper;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Helpers {
    public static String bycrptEncoderPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    public static Boolean bycrptMatchingPassword(String rawPassword, String encodedPassword) {
        return new BCryptPasswordEncoder().matches(rawPassword, encodedPassword);
    }
}
