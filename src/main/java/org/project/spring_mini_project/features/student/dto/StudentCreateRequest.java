package org.project.spring_mini_project.features.student.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record StudentCreateRequest (
        String high_school,
        String university,
        @NotNull
        Long user_id

) {
}
