package com.dev.cinema.dto;

public class MovieSessionDto {

    private Long movie;
    private Long cinemaHall;
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
