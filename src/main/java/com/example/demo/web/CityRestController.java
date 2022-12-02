package com.example.demo.web;

import com.example.demo.model.dto.CityNameDTO;
import com.example.demo.model.view.CityView;
import com.example.demo.service.CityService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/name")
    public ResponseEntity<CityNameDTO> updateName(@Valid @RequestBody CityNameDTO city, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        if (cityService.updateName(city)) {
            return ResponseEntity.ok(city);
        }
        return ResponseEntity.notFound().build();
    }

}
