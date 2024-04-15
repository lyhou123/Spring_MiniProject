package org.project.spring_mini_project.features.enrollment.dto;

import org.project.spring_mini_project.domain.Course;
import org.project.spring_mini_project.domain.Student;

public record EnrollmentRespone(
        Long id,
        String code,
        long course_id,
        Integer student_id,
        Boolean is_deleted,
        Boolean is_certified,
        Integer progress,
        Course course_name

) {
}
