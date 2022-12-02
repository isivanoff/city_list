package com.example.demo.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.URL;

public class CityPhotoDTO {
    @NotNull
    @Positive
    private Long id;

    @NotEmpty
    @URL
    private String photo;

    public CityPhotoDTO() {
    }

    public Long getId() {
        return id;
    }

    public CityPhotoDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getPhoto() {
        return photo;
    }

    public CityPhotoDTO setPhoto(String photo) {
        this.photo = photo;
        return this;
    }
}
