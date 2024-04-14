package org.project.spring_mini_project.features.category.dto;

import org.project.spring_mini_project.domain.Course;

public record CategoryRequest (
        String alias,
        String icon,
        Boolean is_deleted,
        String name,
        Course course,
        Integer parent_category_id
){
}
