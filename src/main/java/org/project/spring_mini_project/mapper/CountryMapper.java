package org.project.spring_mini_project.mapper;

import org.mapstruct.Mapper;
import org.project.spring_mini_project.domain.Country;
import org.project.spring_mini_project.features.country.dto.CountryRespone;

@Mapper(componentModel = "spring")
public interface CountryMapper {
    CountryRespone mapToCountryRespone(Country country);

}
