package com.dev.cinema.controller;

import com.dev.cinema.dto.MovieSessionDto;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
    Add moviesession - POST: /moviesessions
    Get all movie sessions - GET: /moviesessions/available?movieId=1&date=29.02.2020
*/

@RestController
@RequestMapping("/moviesessions")
public class MovieSessionController {
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    private MovieSessionService movieSessionService;
    @Autowired
    private MovieService movieService;
    @Autowired
    private CinemaHallService cinemaHallService;

    @PostMapping
    public String add(@RequestBody MovieSessionDto movieSessionDto) {
        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(movieService.get(movieSessionDto.getMovie()));
        movieSession.setCinemaHall(cinemaHallService.get(movieSessionDto.getCinemaHall()));
        movieSession.setShowTime(LocalDateTime.parse(movieSessionDto.getShowTime(),
                DATE_TIME_FORMATTER));
        movieSessionService.add(movieSession);
        return "movie session added";
    }

    @GetMapping("/available")
    public List<MovieSessionDto> getAvailable(@RequestParam("movieId") Long id,
                                              @RequestParam("date") String date) {
        return movieSessionService.findAvailableSessions(id, LocalDate.parse(date, DATE_FORMATTER))
                .stream().map(ms -> new MovieSessionDto(ms.getMovie().getId(),
                        ms.getCinemaHall().getId(), ms.getShowTime().format(DATE_TIME_FORMATTER)))
                .collect(Collectors.toList());
    }
}