package com.example.demo.web;

import com.example.demo.model.entity.City;
import com.example.demo.repository.CityRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CityControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CityRepository cityRepository;

    private City city;

    @BeforeEach
    void setUp() {
        city = new City().setId(1L).setName("Sofia").setPhoto("https://freesofiatour.com/wp-content/uploads/2012/06/Sofia-vitosha-kempinski.jpg");
        cityRepository.save(city);
    }

    @AfterEach
    void after() {
        cityRepository.deleteAll();
    }

    @Test
    void testGetCities() throws Exception {
        mockMvc.perform(get("/cities"))
                .andExpect(status().isOk());
    }

    @Test
    void testEditValidName() throws Exception {
        mockMvc.perform(put("/cities/name")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"valid name\", \"id\": 1}")
        ).andExpect(status().isOk());
    }

    @Test
    void testEditInvalidName() throws Exception {
        mockMvc.perform(put("/cities/name")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"a\", \"id\": 1}")
        ).andExpect(status().isBadRequest());
    }


    @Test
    void testEditNotExistingName() throws Exception {
        mockMvc.perform(put("/cities/name")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"valid name\", \"id\": 432432}")
        ).andExpect(status().isNotFound());
    }

}
