package com.example.demo.repository;

import com.example.demo.model.entity.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.DoubleStream;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    Page<City> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
