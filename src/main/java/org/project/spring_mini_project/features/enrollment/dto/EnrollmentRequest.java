package org.project.spring_mini_project.features.enrollment.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record EnrollmentRequest(
        @NotEmpty
        String code,
          @NotNull
        Long course_id,
        @NotNull
        Long student_id) {
}
