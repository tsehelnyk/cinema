package com.dev.cinema.controller;

import com.dev.cinema.exception.AuthenticationException;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;
import com.dev.cinema.service.AuthenticationService;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private StringBuilder stringBuilder = new StringBuilder();

    @Autowired
    private MovieService movieService;
    @Autowired
    private CinemaHallService cinemaHallService;
    @Autowired
    private MovieSessionService movieSessionService;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private OrderService orderService;

    @RequestMapping("/hello")
    public String inject() {
        Movie movie = injectMovie("Fast and Furious");
        CinemaHall cinemaHall = injectCinemaHall();
        MovieSession movieSession = injectMovieSession(movie, cinemaHall);
        User user = userService.findByEmail("joe@gmail.com");
        ShoppingCart shoppingCart = injectToShoppingCart(user, movieSession);

        injectOrder(user, shoppingCart);

        movie = injectMovie("Slow and Pacific");
        movieSession = injectMovieSession(movie, cinemaHall);
        shoppingCart = injectToShoppingCart(user, movieSession);

        injectOrder(user, shoppingCart);
        return stringBuilder.toString() + "\n data injected \n"
                + LocalDateTime.now().format(DATE_TIME_FORMATTER);
    }

    private Movie injectMovie(String movieTitle) {
        Movie movie = new Movie();
        movie.setTitle(movieTitle);
        movieService.add(movie);
        stringBuilder.append(movieService.getAll() + "\n");
        return movie;
    }

    private CinemaHall injectCinemaHall() {
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(100);
        cinemaHall.setDescription("Blue");
        cinemaHall = cinemaHallService.add(cinemaHall);
        cinemaHall.setCapacity(50);
        cinemaHall.setDescription("Green");
        cinemaHall = cinemaHallService.add(cinemaHall);
        stringBuilder.append(cinemaHallService.getAll() + "\n");
        return cinemaHall;
    }

    private MovieSession injectMovieSession(Movie movie, CinemaHall cinemaHall) {
        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(movie);
        movieSession.setCinemaHall(cinemaHall);
        LocalTime movieSessionTime = LocalTime.of(19,00,00);
        LocalDate movieSessionDate = LocalDate.of(2020, 02,20);
        movieSession.setShowTime(LocalDateTime.of(movieSessionDate, movieSessionTime));
        movieSessionService.add(movieSession);
        stringBuilder.append(movieSessionService.findAvailableSessions(movie.getId(),
                movieSessionDate) + "\n");
        return movieSession;
    }

    private User injectUsersAndLogin() {
        String userPassword = "123";
        authenticationService.register("Joe", "joe@gmail.com", userPassword);
        authenticationService.register("Bob","bob@gmail.com", userPassword);
        authenticationService.register("John","john@gmail.com", userPassword);
        authenticationService.register("Lenny","lenny@gmail.com", userPassword);
        stringBuilder.append(userService.get(1L) + " user injected\n");
        return userService.findByEmail("joe@gmail.com");
    }

    private ShoppingCart injectToShoppingCart(User user, MovieSession movieSession) {
        shoppingCartService.addSession(movieSession, user);
        ShoppingCart shoppingCart = shoppingCartService.getByUser(user);
        stringBuilder.append(shoppingCart + " movie session injected to shopping cart\n");
        return shoppingCart;
    }

    private List<Order> injectOrder(User user, ShoppingCart shoppingCart) {
        orderService.completeOrder(shoppingCart.getTickets(), user);
        List<Order> orders = orderService.getOrderHistory(user);
        stringBuilder.append(orders + " orders injected\n");
        return orders;
    }
}
