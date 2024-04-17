package org.project.spring_mini_project.features.course.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

@Builder
public record CourseCreateRequest(@NotNull String title, String description, @NotNull String alias, String thumbnail, boolean is_free, @NotNull Long instructor_id, @NotNull Long Category_id) {
}