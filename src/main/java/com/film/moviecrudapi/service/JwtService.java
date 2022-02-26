package com.film.moviecrudapi.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.film.moviecrudapi.Constans;
import com.film.moviecrudapi.database.model.User;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {

    public Map<String, String> createJWT(User user) {

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        Date expired = new Date(nowMillis + Constans.Token_validate);
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constans.secret_KeyString).setIssuedAt(now)
                .setExpiration(expired).claim("id", user.getId()).claim("email", user.getEmail())
                .claim("role", user.getRole().toString())
                .claim("name", user.getName()).compact();

        Map<String, String> maping = new HashMap<>();
        maping.put("token", token);
        maping.put("id", user.getId());
        maping.put("role", user.getRole().toString());
        return maping;
    }
}
