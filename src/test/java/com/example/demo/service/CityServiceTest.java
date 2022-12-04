package com.example.demo.service;

import com.example.demo.model.dto.CityNameDTO;
import com.example.demo.model.dto.CityPhotoDTO;
import com.example.demo.model.entity.City;
import com.example.demo.model.view.CityView;
import com.example.demo.repository.CityRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CityServiceTest {
    @Mock
    private CityRepository cityRepository;

    private CityService toTest;
    private City city;

    @BeforeEach
    void setUp() {
        toTest = new CityService(cityRepository, new ModelMapper());

        city = new City().setId(1L).setName("Sofia").setPhoto("https://freesofiatour.com/wp-content/uploads/2012/06/Sofia-vitosha-kempinski.jpg");
    }

    @Test
    void testFindAllByNameSuccessfully() {
        when(cityRepository.findByNameContainingIgnoreCase(any(), any())).thenReturn(new PageImpl<>(List.of(city)));
        Page<CityView> allCities = toTest.findAllByName(city.getName(), Pageable.ofSize(10));
        Assertions.assertEquals(city.getName(), allCities.getContent().get(0).getName());
        Assertions.assertEquals(city.getPhoto(), allCities.getContent().get(0).getPhoto());
    }

    @Test
    void testFindAllByNameNotSuccessfully() {
        when(cityRepository.findByNameContainingIgnoreCase(any(), any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        Page<CityView> allCities = toTest.findAllByName(city.getName(), Pageable.ofSize(10));
        Assertions.assertEquals(0, allCities.getContent().size());
    }

    @Test
    void testUpdateNameByValidId() {
        when(cityRepository.findById(city.getId())).thenReturn(Optional.of(city));
        Assertions.assertTrue(toTest.updateName(new CityNameDTO().setName("test").setId(1L)));
        verify(cityRepository).findById(1L);
    }

    @Test
    void testUpdateNameByInvalidID() {
        when(cityRepository.findById(any())).thenReturn(Optional.empty());
        Assertions.assertFalse(toTest.updateName(new CityNameDTO().setName("test").setId(1L)));
        verify(cityRepository).findById(1L);
    }

    @Test
    void testUpdatePhotoByValidId() {
        when(cityRepository.findById(city.getId())).thenReturn(Optional.of(city));
        Assertions.assertTrue(toTest.updatePhoto(new CityPhotoDTO().setPhoto("https://testphoto.com").setId(1L)));
        verify(cityRepository).findById(1L);
    }

    @Test
    void testUpdatePhotoByInvalidID() {
        when(cityRepository.findById(city.getId())).thenReturn(Optional.empty());
        Assertions.assertFalse(toTest.updatePhoto(new CityPhotoDTO().setPhoto("https://testphoto.com").setId(1L)));
        verify(cityRepository).findById(1L);
    }

}
