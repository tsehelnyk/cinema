package com.dev.cinema.controller;

import com.dev.cinema.dto.MovieDto;
import com.dev.cinema.model.Movie;
import com.dev.cinema.service.MovieService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @PostMapping
    public String add(@RequestBody MovieDto movieDto) {
        Movie movie = new Movie();
        movie.setTitle(movieDto.getTitle());
        movie.setDescription(movieDto.getDescription());
        movieService.add(movie);
        return "movie added";
    }

    @GetMapping
    public List<MovieDto> getAll() {
        return movieService.getAll().stream()
                .map(m -> new MovieDto(m.getTitle(), m.getDescription()))
                .collect(Collectors.toList());
    }
}
