package org.project.spring_mini_project.features.enrollment.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public record EnrollmentResponse(
        Long id,
        String code,
        Boolean is_deleted,
        Boolean is_certified,
        LocalDateTime enrolledAt,
        Integer progress,
        String courseTitle, // added
        String courseCategory, // added
        String studentName // changed from 'userUsername'
) {
}
