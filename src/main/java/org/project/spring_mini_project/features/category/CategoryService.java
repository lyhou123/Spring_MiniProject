package org.project.spring_mini_project.features.category;

import org.project.spring_mini_project.features.category.dto.CategoryRespone;

import java.util.List;

public interface CategoryService {
    List<CategoryRespone> getAllCategories();
}
