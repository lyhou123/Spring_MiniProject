package org.project.spring_mini_project.features.user;

import lombok.RequiredArgsConstructor;
import org.project.spring_mini_project.domain.*;
import org.project.spring_mini_project.features.city.CityRepository;
import org.project.spring_mini_project.features.country.CountryRepository;
import org.project.spring_mini_project.features.instructor.InstructorRepository;
import org.project.spring_mini_project.features.role.RoleRepository;
import org.project.spring_mini_project.features.student.StudentRepository;
import org.project.spring_mini_project.features.user.dto.UserDetailsResponse;
import org.project.spring_mini_project.features.user.dto.UserRequest;
import org.project.spring_mini_project.mapper.UserMapper;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    private final StudentRepository studentRepository;
    private final InstructorRepository instructorRepository;

    @Override
    public List<UserDetailsResponse> findAllUsers(String username, String email, String national_id_card, String phone_number, String given_name, String gender, Set<String> roleNames) {
        Specification<User> spec = Specification
                .where(new UserSpecification("username", username))
                .and(new UserSpecification("email", email))
                .and(new UserSpecification("national_id_card", national_id_card))
                .and(new UserSpecification("phone_number", phone_number))
                .and(new UserSpecification("given_name", given_name))
                .and(new UserSpecification("gender", gender));

        if (roleNames != null) {
            for (String roleName : roleNames) {
                spec = spec.and(new UserSpecification("roles", roleName));
            }
        }

        List<User> users = userRepository.findAll(spec, Sort.by(Sort.Direction.ASC, "id"));
        return users.stream()
                .map(userMapper::userToUserDetailsResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserDetailsResponse createUser(UserRequest userRequest) {
        User user = userMapper.userRequestToUser(userRequest);

        // Set the default role as "USER"
        Role role = roleRepository.findByName("USER")
                .orElseThrow(() -> new NoSuchElementException("Role not found with name: USER"));
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);

        City city = cityRepository.findById(userRequest.city_id())
                .orElseThrow(() -> new NoSuchElementException("City not found !!! " ));
        user.setCity(city);

        Country country = countryRepository.findById(userRequest.country_id())
                .orElseThrow(() -> new NoSuchElementException("Country not found !!! " ));
        user.setCountry(country);

        User savedUser = userRepository.save(user);
        return userMapper.userToUserDetailsResponse(savedUser);
    }


    @Override
    public UserDetailsResponse findUserByUsername(String username) {
        User user = userRepository.findUserByUsername(username);
        if(user == null) {
            throw new NoSuchElementException("User not found");
        }
        return userMapper.userToUserDetailsResponse(user);
    }


    @Override
    public UserDetailsResponse disableUser(String username) {
        userRepository.disableUser(username);
        if (userRepository.findUserByUsername(username) == null) {
            throw new NoSuchElementException("User not found");
        }
        User user = userRepository.findUserByUsername(username);
        return userMapper.userToUserDetailsResponse(user);
    }

    @Override
    public UserDetailsResponse enableUser(String username) {
        userRepository.enableUser(username);
        if (userRepository.findUserByUsername(username) == null) {
            throw new NoSuchElementException("User not found");
        }
        User user = userRepository.findUserByUsername(username);
        return userMapper.userToUserDetailsResponse(user);
    }

    @Override
    public void deleteUser(String username) {

        User user = userRepository.findUserByUsername(username);

        if (user != null) {

            List<Student> students = studentRepository.findByUserId(user.getId());

            studentRepository.deleteAll(students);

            userRepository.delete(user);
        }

    }
}