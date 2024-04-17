package org.project.spring_mini_project.features.city;

import org.project.spring_mini_project.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CityRepository extends JpaRepository<City, Long> {
}