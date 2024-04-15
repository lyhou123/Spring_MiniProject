package org.project.spring_mini_project.features.country;

import org.project.spring_mini_project.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
