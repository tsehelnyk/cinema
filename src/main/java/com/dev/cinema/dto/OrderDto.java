package com.dev.cinema.dto;

import java.util.List;

public class OrderDto {

    private List<TicketDto> tickets;
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
