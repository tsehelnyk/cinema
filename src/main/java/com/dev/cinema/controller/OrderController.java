package com.dev.cinema.controller;

import com.dev.cinema.dto.OrderDto;
import com.dev.cinema.dto.TicketDto;
import com.dev.cinema.dto.UserResponseDto;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.User;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/complete")
    public String completeOrder(@RequestBody UserResponseDto userResponseDto) {
        User user = userService.findByEmail(userResponseDto.getEmail());
        orderService.completeOrder(shoppingCartService.getByUser(user).getTickets(), user);
        return "order completed";
    }

    @PostMapping
    public List<OrderDto> getAllUserOrders(@RequestBody UserResponseDto userResponseDto) {
        User user = userService.findByEmail(userResponseDto.getEmail());
        return orderService.getOrderHistory(user).stream()
                .map(o -> new OrderDto(toTicketsDto(o.getTickets()), o.getOrderDate().toString()))
                .collect(Collectors.toList());
    }

    private List<TicketDto> toTicketsDto(List<Ticket> tickets) {
        return tickets.stream().map(t -> new TicketDto(t.getMovie().getTitle(),
                t.getCinemaHall().getDescription(), t.getShowTime().format(DATE_TIME_FORMATTER)))
                .collect(Collectors.toList());
    }
}
