package com.example.demo.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class CityNameDTO {

    @NotNull
    @Positive
    private Long id;

    @NotEmpty
    @Size(min = 2, max = 30)
    private String name;

    public CityNameDTO() {
    }

    public Long getId() {
        return id;
    }

    public CityNameDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CityNameDTO setName(String name) {
        this.name = name;
        return this;
    }
}
