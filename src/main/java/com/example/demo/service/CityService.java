package com.example.demo.service;

import com.example.demo.model.view.CityView;
import com.example.demo.repository.CityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CityService {
    private CityRepository cityRepository;
    private ModelMapper modelMapper;


    public CityService(CityRepository cityRepository, ModelMapper modelMapper) {
        this.cityRepository = cityRepository;
        this.modelMapper = modelMapper;
    }

    public Page<CityView> findAllByName(String name, Pageable pageable) {

        return cityRepository.findByNameContainingIgnoreCase(name, pageable)
                .map(c -> modelMapper.map(c, CityView.class));
    }
}
