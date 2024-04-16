package org.project.spring_mini_project.features.enrollment.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record EnrollmentResponse(
        Long id,
        String code,
        Boolean is_deleted,
        Boolean is_certified,
        LocalDate enrolled_at,
        Integer progress,
        String courseTitle, // added
        String courseCategory, // added
        String studentName // changed from 'userUsername'
) {
}
