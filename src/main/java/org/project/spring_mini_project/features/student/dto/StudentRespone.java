package org.project.spring_mini_project.features.student.dto;

import org.project.spring_mini_project.domain.User;
import org.project.spring_mini_project.features.user.dto.UserDetailsResponse;

public record StudentRespone(
        Integer id,
        String high_school,
        Boolean is_blocked,
        String university,
        String username,
        Long user_id

) {
}
