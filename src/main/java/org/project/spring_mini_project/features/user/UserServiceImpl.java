package org.project.spring_mini_project.features.user;

import lombok.RequiredArgsConstructor;
import org.project.spring_mini_project.domain.User;
import org.project.spring_mini_project.domain.Role;
import org.project.spring_mini_project.features.role.RoleRepository;
import org.project.spring_mini_project.features.user.dto.UserDetailsResponse;
import org.project.spring_mini_project.features.user.dto.UserRequest;
import org.project.spring_mini_project.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    @Override
    public List<UserDetailsResponse> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::userToUserDetailsResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserDetailsResponse createUser(UserRequest userRequest) {
        User user = userMapper.userRequestToUser(userRequest);

        Set<Role> roles = new HashSet<>();
        for (String roleName : userRequest.roleNames()) {
            Role role = roleRepository.findByName(roleName)
                    .orElseThrow(() -> new RuntimeException("Role not found with name: " + roleName));
            roles.add(role);
        }

        user.setRoles(roles);

        User savedUser = userRepository.save(user);

        return userMapper.userToUserDetailsResponse(savedUser);
    }

    @Override
    public UserDetailsResponse updateUser(Long id, UserRequest userRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        user.setUsername(userRequest.username());
        user.setEmail(userRequest.email());

        Set<Role> roles = new HashSet<>();
        for (String roleName : userRequest.roleNames()) {
            Role role = roleRepository.findByName(roleName)
                    .orElseThrow(() -> new RuntimeException("Role not found with name: " + roleName));
            roles.add(role);
        }
        user.setRoles(roles);

        User updatedUser = userRepository.save(user);

        return userMapper.userToUserDetailsResponse(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}