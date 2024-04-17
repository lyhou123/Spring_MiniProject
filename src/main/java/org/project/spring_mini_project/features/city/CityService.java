package org.project.spring_mini_project.features.city;

import org.project.spring_mini_project.domain.City;

import java.util.List;

public interface CityService {
   List<City> getAllCities(String sort, String filter);
}
