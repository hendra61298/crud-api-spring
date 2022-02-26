package com.film.moviecrudapi.dto.movie;

import javax.validation.constraints.NotEmpty;

public class CreateMovieDto {

    @NotEmpty(message = "title is Required")
    public String title;
    @NotEmpty(message = "description is Required")
    public String description;
    @NotEmpty(message = "logoUrl is Required")
    public String logoUrl;

    public boolean highlight = false;
    @NotEmpty(message = "videoUrl is Required")
    public String videoUrl;
    @NotEmpty(message = "director is Required")
    public String director;
}
