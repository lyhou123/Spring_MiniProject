package org.project.spring_mini_project.features.country;

import lombok.RequiredArgsConstructor;
import org.project.spring_mini_project.domain.Country;
import org.project.spring_mini_project.features.country.dto.CountryRespone;
import org.project.spring_mini_project.mapper.CountryMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    @Override
    public void fetchAndSaveCountries() {

    }

    private final CountryRepository countryRepository;

    private final CountryMapper countryMapper;

    @Override
    public List<CountryRespone> getAllCountries(String sortBy, String filterByName) {

        List<Country> countries = fetchCountries();

        sortCountries(countries, sortBy);
        filterCountriesByName(countries, filterByName);

        return countries.stream()
                .map(countryMapper::mapToCountryRespone)
                .toList();
    }

    private List<Country> fetchCountries() {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://restcountries.com/v3.1/all";
        Country[] countries = restTemplate.getForObject(apiUrl, Country[].class);
        return List.of(countries);
    }

    private void sortCountries(List<Country> countries, String sortBy) {
        if ("name".equalsIgnoreCase(sortBy)) {
            countries.sort((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
        }
    }

    private void filterCountriesByName(List<Country> countries, String filterByName) {

        if (filterByName != null && !filterByName.isEmpty()) {
            String filterLowerCase = filterByName.toLowerCase();
            countries.removeIf(country -> !country.getName().toLowerCase().contains(filterLowerCase));
        }
    }


}
