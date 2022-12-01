package com.example.demo.model.view;

public class CityView {
    private Long id;
    private String name;
    private String photo;

    public CityView() {
    }

    public CityView setId(Long id) {
        this.id = id;
        return this;
    }

    public CityView setName(String name) {
        this.name = name;
        return this;
    }

    public CityView setPhoto(String photo) {
        this.photo = photo;
        return this;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoto() {
        return photo;
    }
}
