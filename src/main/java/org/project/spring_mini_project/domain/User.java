package org.project.spring_mini_project.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address1;
    private String address2;
    private Integer city_id;
    private Integer country_id;
    private Date dob;

    @Column(unique = true,nullable = false)
    private String email;
    private String family_name;
    private String gender;
    private String given_name;
    private Boolean is_deleted;
    private Boolean is_verified;

    @Column(unique = true, nullable = false, length =30)
    private String national_id_card;
    private String password;
    private String phone_number;
    private String profile;

    @Column(unique = true,nullable = false)
    private String username;

    @Column(nullable = false)
    private UUID uuid;
    private String verification_code;

    //relationship student
    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Student student;

    //relationship instructor
    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Instructor instructor;

    //relationship roles
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;



    @PrePersist
    public void generateUuid() {
        if (uuid == null) {
            uuid = UUID.randomUUID();
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        // do not include roles in the hashCode calculation
        return result;
    }

}
