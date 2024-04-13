package org.project.spring_mini_project.features.user.dto;

import java.util.Set;

public record UserRequest(String address1, String address2, String family_name, String gender, String given_name, String national_id_card, String password, String username, String email, Set<String> roleNames) {
}
