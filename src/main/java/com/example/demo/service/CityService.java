package com.example.demo.service;

import com.example.demo.repository.CityRepository;
import org.springframework.stereotype.Service;

@Service
public class CityService {
    private CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }
}
