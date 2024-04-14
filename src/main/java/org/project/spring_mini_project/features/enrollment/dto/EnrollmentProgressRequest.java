package org.project.spring_mini_project.features.enrollment.dto;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.project.spring_mini_project.domain.Student;

import java.time.LocalDateTime;

public record EnrollmentProgressRequest(
        String code,
        long course_id,
        LocalDateTime enrolled_at,
        Boolean is_deleted,
        Boolean is_certified,
        Integer progress,
       long student_id

) {
}
