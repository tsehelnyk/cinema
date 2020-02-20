package com.dev.cinema.dto;

import java.util.List;

public class ShoppingCartDto {

    private List<TicketDto> tickets;
    private Long userId;

    public ShoppingCartDto() {
    }

    public ShoppingCartDto(List<TicketDto> tickets, Long userId) {
        this.tickets = tickets;
        this.userId = userId;
    }

    public List<TicketDto> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketDto> tickets) {
        this.tickets = tickets;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
