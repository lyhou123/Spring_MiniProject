package org.project.spring_mini_project.features.country;

import lombok.RequiredArgsConstructor;
import org.project.spring_mini_project.features.country.dto.CountryRespone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/countries")
@RequiredArgsConstructor
public class CountyRestController {
    @Autowired
    private CountryService countryService;
    private String filter;

    @GetMapping("/countries")
    List<CountryRespone> getAllCountries(@RequestParam(required = false) String sort, @RequestParam(required = false) String filter) {
        return countryService.getAllCountries(sort, filter);
    }

    @GetMapping("/countries/{iso}/cities")
    List<CountryRespone> getCitiesInCountry(@PathVariable String iso) {
        return countryService.getAllCountries(iso, filter);
    }
}
