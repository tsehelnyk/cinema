package com.dev.cinema.dto;

public class TicketDto {

    private String movie;
    private String cinemaHall;
    private String showTime;

    public TicketDto() {
    }

    public TicketDto(String movie, String cinemaHall, String showTime) {
        this.movie = movie;
        this.cinemaHall = cinemaHall;
        this.showTime = showTime;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getCinemaHall() {
        return cinemaHall;
    }

    public void setCinemaHall(String cinemaHall) {
        this.cinemaHall = cinemaHall;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }
}
