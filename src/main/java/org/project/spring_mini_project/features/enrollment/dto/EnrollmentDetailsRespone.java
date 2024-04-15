package org.project.spring_mini_project.features.enrollment.dto;

public record EnrollmentDetailsRespone (
        Long id,
        String code,
        Long course_id,
        Long student_id,
        Boolean is_deleted,
        Boolean is_certified,
        Integer progress,
        String course_name,
        String student_name,
        String high_school,
        Boolean is_blocked,
        String university
){
}
