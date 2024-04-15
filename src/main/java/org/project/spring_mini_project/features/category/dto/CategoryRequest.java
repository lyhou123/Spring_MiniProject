package org.project.spring_mini_project.features.category.dto;

import jakarta.annotation.Nullable;

public record CategoryRequest(String name, String alias, String icon, @Nullable Integer parentCategoryId) {
}