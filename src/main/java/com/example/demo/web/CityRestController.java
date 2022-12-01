package com.example.demo.web;

import com.example.demo.service.CityService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityRestController {
    private CityService cityService;

    public CityRestController(CityService cityService) {
        this.cityService = cityService;
    }
}
