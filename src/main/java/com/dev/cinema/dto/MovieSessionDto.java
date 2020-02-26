package com.dev.cinema.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class MovieSessionDto {

    @NotNull(message = "Movie session id could not be null")
    @Min(1)
    private Long movie;
    @NotNull(message = "Cinema hall id in Movie session could not be null")
    @Min(1)
    private Long cinemaHall;
    @NotNull(message = "Movie session showtime could not be null")
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
