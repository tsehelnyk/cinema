package com.dev.cinema.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class MovieSessionDto {

    @NotNull
    @Min(1)
    private Long movie;
    @NotNull
    @Min(1)
    private Long cinemaHall;
    @NotNull
    private String showTime;

    public MovieSessionDto() {
    }

    public MovieSessionDto(Long movie, Long cinemaHall, String showTime) {
        this.movie = movie;
        this.cinemaHall = cinemaHall;
        this.showTime = showTime;
    }

    public Long getMovie() {
        return movie;
    }

    public void setMovie(Long movie) {
        this.movie = movie;
    }

    public Long getCinemaHall() {
        return cinemaHall;
    }

    public void setCinemaHall(Long cinemaHall) {
        this.cinemaHall = cinemaHall;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }
}
