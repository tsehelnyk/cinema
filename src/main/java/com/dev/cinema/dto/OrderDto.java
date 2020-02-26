package com.dev.cinema.dto;

import java.util.List;
import javax.validation.constraints.NotNull;

public class OrderDto {
    @NotNull(message = "Order tickets could not be null")
    private List<TicketDto> tickets;
    @NotNull(message = "Order date could not be null")
    private String orderDate;

    public OrderDto() {
    }

    public OrderDto(List<TicketDto> tickets, String orderDate) {
        this.tickets = tickets;
        this.orderDate = orderDate;
    }

    public List<TicketDto> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketDto> tickets) {
        this.tickets = tickets;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
}
