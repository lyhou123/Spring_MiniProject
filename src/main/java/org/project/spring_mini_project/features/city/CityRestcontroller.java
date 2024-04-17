package org.project.spring_mini_project.features.city;

import org.project.spring_mini_project.domain.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cities")
public class CityRestcontroller {
    @Autowired
    private CityService cityService;

    // Endpoint to get all cities
    @GetMapping
    public List<City> getAllCities(@RequestParam(required = false) String sort, @RequestParam(required = false) String filter) {
        return cityService.getAllCities(sort, filter);
    }
}
