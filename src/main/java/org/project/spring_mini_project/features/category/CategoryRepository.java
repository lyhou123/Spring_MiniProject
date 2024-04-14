package org.project.spring_mini_project.features.category;

import org.project.spring_mini_project.domain.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Categories,Long> {
}
