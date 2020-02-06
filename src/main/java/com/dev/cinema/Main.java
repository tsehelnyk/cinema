package com.dev.cinema;

import com.dev.cinema.exception.AuthenticationException;
import com.dev.cinema.lib.Injector;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.service.AuthenticationService;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.UserService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Main {
    private static Injector injector = Injector.getInstance("com.dev.cinema");

    public static void main(String[] args) throws AuthenticationException {
        Movie movie = new Movie();
        movie.setTitle("Fast and Furious");
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        movieService.add(movie);
        movieService.getAll().forEach(System.out::println);

        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(100);
        cinemaHall.setDescription("Blue");
        CinemaHallService cinemaHallService =
                (CinemaHallService) injector.getInstance(CinemaHallService.class);
        cinemaHall = cinemaHallService.add(cinemaHall);
        cinemaHallService.getAll().forEach(System.out::println);

        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(movie);
        movieSession.setCinemaHall(cinemaHall);
        LocalTime movieSessionTime = LocalTime.of(19,00,00);
        LocalDate movieSessionDate = LocalDate.of(2020, 02,05);
        movieSession.setShowTime(LocalDateTime.of(movieSessionDate, movieSessionTime));
        MovieSessionService movieSessionService =
                (MovieSessionService) injector.getInstance(MovieSessionService.class);
        movieSessionService.add(movieSession);

        System.out.println("There are movie sessions today: \n"
                + movieSessionService.findAvailableSessions(movie.getId(), LocalDate.now()));

        String userEmail = "qwerty@qwerty.com";
        String userPassword = "123";
        AuthenticationService authenticationService =
                (AuthenticationService) injector.getInstance(AuthenticationService.class);
        authenticationService.register(userEmail, userPassword);
        UserService userService = (UserService) injector.getInstance(UserService.class);
        System.out.println(userService.findByEmail(userEmail));
        try {
            System.out.println("User " + authenticationService.login(userEmail, userPassword)
                    + " successful logined!");
        } catch (AuthenticationException e) {
            throw new RuntimeException("Something was wrong: ", e);
        }
    }
}
