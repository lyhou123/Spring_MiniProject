package org.project.spring_mini_project.mapper;

import org.mapstruct.Mapper;
import org.project.spring_mini_project.domain.Categories;
import org.project.spring_mini_project.features.category.dto.CategoryRespone;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryRespone toCategoryResponse(Categories categories);
}
