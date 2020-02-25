package com.dev.cinema.controller;

import com.dev.cinema.dto.MovieSessionDto;
import com.dev.cinema.dto.ShoppingCartDto;
import com.dev.cinema.dto.TicketDto;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shoppingcarts")
public class ShoppingCartController {
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private UserService userService;
    @Autowired
    private MovieService movieService;
    @Autowired
    private CinemaHallService cinemaHallService;

    @RequestMapping(value = "/add-movie-session", method = RequestMethod.POST)
    public String add(@Valid Long userId, @RequestBody @Valid MovieSessionDto movieSessionDto) {
        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(movieService.get(movieSessionDto.getMovie()));
        movieSession.setCinemaHall(cinemaHallService.get(movieSessionDto.getCinemaHall()));
        movieSession.setShowTime(LocalDateTime.parse(movieSessionDto.getShowTime(),
                DATE_TIME_FORMATTER));
        shoppingCartService.addSession(movieSession, userService.get(userId));
        return "movie session added to shopping cart";
    }

    @RequestMapping(value = "/by-user", method = RequestMethod.GET)
    public ShoppingCartDto getByUserId(@Valid Long userId) {
        ShoppingCart shoppingCart = shoppingCartService.getByUser(userService.get(userId));
        return new ShoppingCartDto(shoppingCart.getTickets().stream()
                .map(t -> toTicketDto(t)).collect(Collectors.toList()),
                shoppingCart.getUser().getId());
    }

    private TicketDto toTicketDto(Ticket ticket) {
        return new TicketDto(ticket.getMovie().getTitle(),
                ticket.getCinemaHall().getDescription(),
                ticket.getShowTime().format(DATE_TIME_FORMATTER));
    }
}
