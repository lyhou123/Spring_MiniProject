package org.project.spring_mini_project.domain;

import jakarta.persistence.Column;

import java.util.Date;

public class User {
    private long id;
    private String address1;
    private String address2;
    private Integer city_id;
    private Integer country_id;
    private Date dob;

    @Column( nullable = false)
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

    @Column(nullable = false)

    private String username;

    @Column(nullable = false)
    private String uuid;
    private String verification_code;
}
