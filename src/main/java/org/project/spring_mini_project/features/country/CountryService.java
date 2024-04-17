package org.project.spring_mini_project.features.country;

import org.project.spring_mini_project.features.country.dto.CountryRespone;

import java.util.List;

public interface CountryService {
    void fetchAndSaveCountries();

    List<CountryRespone> getAllCountries(String sortBy, String filter);
}
