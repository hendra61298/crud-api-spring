package com.film.moviecrudapi.dto.home;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CreateHistoryMovieDto {

    @NotEmpty(message = "movie_id is Required")
    public String movieId;

    @NotNull(message = "last_minute_time is Required")
    public int lastMinuteTime;
}
