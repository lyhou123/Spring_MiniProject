package org.project.spring_mini_project.features.user.dto;

import lombok.Builder;
import org.project.spring_mini_project.features.role.dto.RoleResponse;

import java.util.Set;
@Builder
public record UserDetailsResponse(Long id, String username, String email, Set<RoleResponse> roles) {
}
