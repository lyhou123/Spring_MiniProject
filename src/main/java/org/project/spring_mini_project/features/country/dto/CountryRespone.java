package org.project.spring_mini_project.features.country.dto;

public record CountryRespone(
        String name,
        String code,
        String continent,
        String region,
        Integer surfaceArea,
        Integer population,
        String governmentForm,
        String headOfState,
        Integer capital,
        String code2
) {
}
