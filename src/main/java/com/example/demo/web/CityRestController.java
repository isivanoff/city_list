package com.example.demo.web;

import com.example.demo.model.view.CityView;
import com.example.demo.service.CityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cities")
public class CityRestController {
    private CityService cityService;

    public CityRestController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/all")
    public ResponseEntity<Page<CityView>> getCities(@RequestParam(defaultValue = "") String name,
                                                    @PageableDefault(
                                                            sort = "name",
                                                            direction = Sort.Direction.ASC
                                                    ) Pageable pageable) {
        return ResponseEntity.ok(cityService.findAllByName(name, pageable));
    }


}
