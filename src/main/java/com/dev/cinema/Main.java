package com.dev.cinema;

import com.dev.cinema.exception.AuthenticationException;
import com.dev.cinema.lib.Injector;
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
import java.util.List;

public class Main {
    private static Injector injector = Injector.getInstance("com.dev.cinema");

    public static void main(String[] args) throws AuthenticationException {
        Movie movie = movieTest("Fast and Furious");
        CinemaHall cinemaHall = cinemaHallTest();
        MovieSession movieSession = movieSessionTest(movie, cinemaHall);
        User user = userTest();
        ShoppingCart shoppingCart = shoppingCartTest(user, movieSession);

        orderTest(user, shoppingCart);

        movie = movieTest("Slow and Pacific");
        movieSession = movieSessionTest(movie, cinemaHall);
        shoppingCart = shoppingCartTest(user, movieSession);

        orderTest(user, shoppingCart);
    }

    private static Movie movieTest(String movieTitle) {
        Movie movie = new Movie();
        movie.setTitle(movieTitle);
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        movieService.add(movie);
        movieService.getAll().forEach(System.out::println);
        return movie;
    }

    private static CinemaHall cinemaHallTest() {
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(100);
        cinemaHall.setDescription("Blue");
        CinemaHallService cinemaHallService =
                (CinemaHallService) injector.getInstance(CinemaHallService.class);
        cinemaHall = cinemaHallService.add(cinemaHall);
        cinemaHallService.getAll().forEach(System.out::println);
        return cinemaHall;
    }

    private static MovieSession movieSessionTest(Movie movie, CinemaHall cinemaHall) {
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
        return movieSession;
    }

    private static User userTest() {
        String userEmail = "qwerty@qwerty.com";
        String userPassword = "123";
        AuthenticationService authenticationService =
                (AuthenticationService) injector.getInstance(AuthenticationService.class);
        authenticationService.register(userEmail, userPassword);
        UserService userService = (UserService) injector.getInstance(UserService.class);
        System.out.println(userService.findByEmail(userEmail));
        try {
            User user = authenticationService.login(userEmail, userPassword);
            System.out.println("User " + user + " successful logined!");
            return user;
        } catch (AuthenticationException e) {
            throw new RuntimeException("Something was wrong: ", e);
        }
    }

    private static ShoppingCart shoppingCartTest(User user, MovieSession movieSession) {
        ShoppingCartService shoppingCartService =
                (ShoppingCartService) injector.getInstance(ShoppingCartService.class);
        shoppingCartService.addSession(movieSession, user);
        ShoppingCart shoppingCart = shoppingCartService.getByUser(user);
        System.out.println(shoppingCart);
        return shoppingCart;
    }

    private static List<Order> orderTest(User user, ShoppingCart shoppingCart) {
        OrderService orderService =
                (OrderService) injector.getInstance(OrderService.class);
        orderService.completeOrder(shoppingCart.getTickets(), user);
        List<Order> orders = orderService.getOrderHistory(user);
        System.out.println(orders);
        return orders;
    }

}
