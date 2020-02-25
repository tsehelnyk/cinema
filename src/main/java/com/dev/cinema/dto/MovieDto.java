package com.dev.cinema.dto;

import javax.validation.constraints.NotNull;

public class MovieDto {
    @NotNull
    private String title;
    @NotNull
    private String description;

    public MovieDto() {
    }

    public MovieDto(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
