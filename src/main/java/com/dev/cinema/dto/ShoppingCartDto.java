package com.dev.cinema.dto;

import java.util.List;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ShoppingCartDto {

    @NotNull(message = "Shopping cart tickets could not be null")
    private List<TicketDto> tickets;
    @NotNull(message = "Shopping cart user id could not be null")
    @Min(1)
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
