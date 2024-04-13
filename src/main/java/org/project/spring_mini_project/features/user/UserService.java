package org.project.spring_mini_project.features.user;

import org.project.spring_mini_project.features.user.dto.UserDetailsResponse;
import org.project.spring_mini_project.features.user.dto.UserRequest;

import java.util.List;

public interface UserService {
    List<UserDetailsResponse> findAllUsers();
    UserDetailsResponse createUser(UserRequest userRequest);
    UserDetailsResponse updateUser(Long id, UserRequest userRequest);
    void deleteUser(Long id);
}
