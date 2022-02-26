package com.film.moviecrudapi;

import com.film.moviecrudapi.routeguard.RouteGuardJwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MovieCrudApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieCrudApiApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<RouteGuardJwt> filterRegistrationBean() {
		FilterRegistrationBean<RouteGuardJwt> registrationBean = new FilterRegistrationBean<>();
		RouteGuardJwt routeGuardJwt = new RouteGuardJwt();
		registrationBean.setFilter(routeGuardJwt);
		registrationBean.addUrlPatterns("/api/user/*");
		registrationBean.addUrlPatterns("/api/admin/*");
		registrationBean.addUrlPatterns("/api/movie/*");
		return registrationBean;
	}

}
