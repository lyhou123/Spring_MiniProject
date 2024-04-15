package org.project.spring_mini_project.init;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.project.spring_mini_project.domain.Authority;
import org.project.spring_mini_project.domain.Role;
import org.project.spring_mini_project.features.authority.AuthoritiesRepository;
import org.project.spring_mini_project.features.role.RoleRepository;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitialize {
    private final RoleRepository roleRepository;
    private final AuthoritiesRepository authoritiesRepository;


    @PostConstruct
    void init() {
        roleInit();
        authorityInit();
        roleAuthorityInit();
    }

    void roleInit(){
        List<String> roles = List.of("ADMIN", "INSTRUCTOR", "STUDENT","USER");
        if (roleRepository.count() == 0){
            roles.forEach(role -> {
                Role newRole = new Role();
                newRole.setName(role);
                roleRepository.save(newRole);
            });
        }
    }

    void authorityInit(){
        List<String> authorities = List.of("user:read", "user:write", "user:delete","user:update","progress:read","progress:write","elearning:read","elearning:write","elearning:udpate","elearning:delete");
        if (authoritiesRepository.count() == 0){
            authorities.forEach(authority ->{
                Authority newAuthority = new Authority();
                newAuthority.setName(authority);
                authoritiesRepository.save(newAuthority);
            });
        }
    }

    void roleAuthorityInit(){
        // Fetch all roles and authorities
        Set<Role> roles = new HashSet<>(roleRepository.findAll());
        Set<Authority> authorities = new HashSet<>(authoritiesRepository.findAll());

        // Assign authorities to roles
        for (Role role : roles) {
            try {
                switch (role.getName()) {
                    case "USER":
                        role.setAuthorities(filterAuthorities(authorities, "user:read", "user:write", "user:update","progress:read","elearning:read"));
                        break;
                    case "STUDENT":
                        role.setAuthorities(filterAuthorities(authorities, "progress:write"));
                        break;
                    case "INSTRUCTOR":
                        role.setAuthorities(filterAuthorities(authorities, "user:read", "user:write", "user:update","progress:read","elearning:read",  "elearning:read", "elearning:write", "elearning:delete", "elearning:update"));
                        break;
                    case "ADMIN":
                        role.setAuthorities(authorities); // Admin has all authorities
                        break;
                    default:
                        throw new IllegalArgumentException("Unexpected role: " + role.getName());
                }
                // Save the role back to the repository
                roleRepository.save(role);
            } catch (Exception e) {
                System.out.println("Error assigning authorities to role: " + role.getName());
                e.printStackTrace();
            }
        }
    }

    private Set<Authority> filterAuthorities(Set<Authority> authorities, String... names) {
        Set<Authority> filtered = new HashSet<>();
        for (Authority authority : authorities) {
            for (String name : names) {
                if (authority.getName().equals(name)) {
                    filtered.add(authority);
                }
            }
        }
        return filtered;
    }

}
