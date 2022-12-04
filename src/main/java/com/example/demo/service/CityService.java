package com.example.demo.service;

import com.example.demo.model.dto.CityNameDTO;
import com.example.demo.model.dto.CityPhotoDTO;
import com.example.demo.model.entity.City;
import com.example.demo.model.view.CityView;
import com.example.demo.repository.CityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public CityNameDTO updateName(CityNameDTO cityDTO) {
        Optional<City> city = cityRepository.findById(cityDTO.getId());

        cityRepository.save(city.get().setName(cityDTO.getName()));

        return cityDTO;
    }


    public boolean updatePhoto(CityPhotoDTO cityDTO) {
        Optional<City> city = cityRepository.findById(cityDTO.getId());

        if (city.isPresent()) {
            cityRepository.save(city.get().setPhoto(cityDTO.getPhoto()));
            return true;
        }
        return false;
    }
}
