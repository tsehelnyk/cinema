package com.dev.cinema.dto;

import javax.validation.constraints.NotNull;

public class TicketDto {
    @NotNull(message = "Movie id in ticket could not be null")
    private String movie;
    @NotNull(message = "Cinema hall id in ticket could not be null")
    private String cinemaHall;
    @NotNull(message = "Showtime in ticket could not be null")
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
