package com.film.moviecrudapi.routeguard;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.film.moviecrudapi.Constans;

import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class RouteGuardJwt extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse htttpServletResponse = (HttpServletResponse) servletResponse;

        String authHeader = httpServletRequest.getHeader("Authorization");
        if (authHeader != null) {
            String[] authHeaderPath = authHeader.split("bearer ");
            if (authHeaderPath.length > 1 && authHeaderPath[1] != null) {
                String token = authHeaderPath[1];
                try {
                    Claims claims = Jwts.parser()
                            .setSigningKey(Constans.secret_KeyString)
                            .parseClaimsJws(token).getBody();

                    httpServletRequest.setAttribute("id", claims.get("id").toString());
                    httpServletRequest.setAttribute("role", claims.get("role"));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    htttpServletResponse.sendError(HttpStatus.FORBIDDEN.value(), "Invalid Token");
                    return;
                }

            } else {
                htttpServletResponse.sendError(HttpStatus.FORBIDDEN.value(), "Authorize token is must 12");
                return;
            }
        } else {
            htttpServletResponse.sendError(HttpStatus.FORBIDDEN.value(), "Authorize token is must");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
