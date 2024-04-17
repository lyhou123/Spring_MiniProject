package org.project.spring_mini_project.features.city;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.project.spring_mini_project.domain.City;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CitySeviceImpl implements CityService{
    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<City> getAllCities(String sort, String filter) {
        // Implement your logic here
        return cityRepository.findAll();
    }

}
