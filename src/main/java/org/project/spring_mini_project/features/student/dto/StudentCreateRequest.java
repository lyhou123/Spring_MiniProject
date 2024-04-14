package org.project.spring_mini_project.features.student.dto;

public record StudentCreateRequest (
        String high_school,
        Boolean is_blocked,
        String university,
        Long user_id

) {
}
