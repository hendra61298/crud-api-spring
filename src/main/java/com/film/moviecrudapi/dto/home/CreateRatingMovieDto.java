package com.film.moviecrudapi.dto.home;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CreateRatingMovieDto {

    @NotEmpty(message = "movie_id is Required")
    public String movieId;

    @Min(value = 1, message = "score minimum 1")
    @Max(value = 10, message = "score maximum 10")
    @NotNull(message = "score is Required")
    public int score;
}
